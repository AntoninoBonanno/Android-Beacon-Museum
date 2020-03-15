# Progetto IOT

### Setup MONGODB

	`> mongoimport --db progetto-iot --collection artworks --file Artworks.json --jsonArray ` (for upload json)

*  MONGO shell

	`> use progetto-iot`

	`> db.artworks.updateMany({},{'$set' : {'Beacon_id': "0"}});`

	`> db.artworks.createIndex({"Beacon_id":1});`

*  Association of beacons with artwork

	`> db.artworks.update({'_id': id_artworks }, {'$set' : {'Beacon_id': beacon_id }});`

	Beacon 1 = -1496743469

	Beacon 2 = 443575690

### Setup Server

 * Dentro la cartella Server

	`> npm install`
	
	`> npm start`


