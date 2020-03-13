package com.biusobonnanno.progetto_iot;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class BeaconResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beacon_results);
        String beaconUuid = getIntent().getStringExtra("BeaconUuid");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(beaconUuid);
        }

        final View progressBar2 = findViewById(R.id.progressBar2);
        new AsyncHttpClient().get("http://192.168.1.186:4000/0", new JsonHttpResponseHandler() { //cambiare ip -> usare properties?
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray responseBody) {
                progressBar2.setVisibility(View.GONE);
                showResults(responseBody);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                progressBar2.setVisibility(View.GONE);
                Toast.makeText(BeaconResultsActivity.this, "Error to load info", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showResults(JSONArray results) {
        Toast.makeText(BeaconResultsActivity.this, results.length() + " results", Toast.LENGTH_LONG).show();
        if(results.length() <= 0) return;

        LinearLayout dynamicContent = findViewById(R.id.dynamic_artwork);
        dynamicContent.removeAllViews();

        for (int i = 0; i < results.length(); i++) {
            try {
                JSONObject artwork = results.getJSONObject(i);
                View newArtworkView = getLayoutInflater().inflate(R.layout.item_artwork, dynamicContent, false);
                ((TextView)newArtworkView.findViewById(R.id.title_artwork)).setText(artwork.getString("Title"));

                JSONArray artist = artwork.getJSONArray("Artist");
                String a = "";
                for (int j = 0; j < artist.length(); j++) a += artist.getString(j) + ((j == artist.length()-1) ? "" : ", ");
                ((TextView)newArtworkView.findViewById(R.id.artist_artwork)).setText(a);

                ((TextView)newArtworkView.findViewById(R.id.data_artwork)).setText(artwork.getString("Date"));
                dynamicContent.addView(newArtworkView);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}
