# Android Beacon Museum
This is a simple project for Android *(Java)* that simulates moving a user inside a museum.
Each Beacon detected represents a room in a museum, where there are several paintings inside.

The Android application detects nearby beacons and collects information about the beacons by contacting a server.
A notification will be sent when the application is paused and detects a new beacon.

The server is written in Node.js and we have used MongoDB for the database.

The android application has been tested with iBeacon type but has been configured to support all types of beacons (*iBeacon, AltBeacon, Eddystone or RuuviTag*).

* The [AltBeacon Android](https://altbeacon.github.io/android-beacon-library/index.html) library was used to detect the beacons.
* We took inspiration from the [Android Beacon Scanner](https://github.com/Bridouille/android-beacon-scanner) project *(written in Kotlin)*.


## Installation 

### Setup MONGODB

`> mongoimport --db beacon-museum --collection artworks --file Artworks.json --jsonArray` (for upload json)

[Download Artworks.json](https://github.com/MuseumofModernArt/collection/blob/master/Artworks.json) 

*  MONGO shell

	`> use beacon-museum `

	`> db.artworks.updateMany({},{'$set' : {'Beacon_id': "0"}});`

	`> db.artworks.createIndex({"Beacon_id":1});`

*  Association of beacons with artwork

	`> db.artworks.update({'_id': id_artworks }, {'$set' : {'Beacon_id': "beacon_id" }});`

	Our beacons: **beacon_id_1** = -1496743469 - **beacon_id_2** = 443575690

	NOTE: beacon_id is the integer hash of the beacon and not UUID because we noticed that the two beacons had the same UUID but different 'major' and 'minor'.

### Setup Server

 * Inside the Server folder

	`> npm install`

	`> npm start`

### Setup App Android

* Change the server URL inside the file BeaconResultsActivity.java
* Build and run the app

## Authors

[Bonanno Antonino](https://github.com/AntoninoBonanno), [Biuso Mario](https://github.com/Mariobiuso)
