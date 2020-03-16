# Progetto IOT

### Setup MONGODB

`> mongoimport --db progetto-iot --collection artworks --file Artworks.json --jsonArray` (for upload json)

Download Json: https://github.com/MuseumofModernArt/collection/blob/master/Artworks.json

*  MONGO shell

	`> use progetto-iot`

	`> db.artworks.updateMany({},{'$set' : {'Beacon_id': "0"}});`

	`> db.artworks.createIndex({"Beacon_id":1});`

*  Association of beacons with artwork

	`> db.artworks.update({'_id': id_artworks }, {'$set' : {'Beacon_id': beacon_id }});`

	beacon_id_1 = -1496743469

	beacon_id_2 = 443575690

	NOTE: beacon_id is the integer hash of the beacon and not UUID because we noticed that the two beacons had the same UUID but different 'major' and 'minor'.

### Setup Server

 * Inside the Server folder

	`> npm install`

	`> npm start`

### Setup App Android

* Change the server URL inside the file BeaconResultsActivity.java
* Build and run the app

