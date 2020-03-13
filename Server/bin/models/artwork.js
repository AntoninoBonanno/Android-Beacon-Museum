const mongoose = require('mongoose');

//mongoimport --db progetto-iot --collection artworks --file C:\Users\Nino\Downloads\Artworks.json --jsonArray
//db.artworks.updateMany({},{'$set' : {'Beacon_id': "0"}});
//db.artworks.createIndex({"Beacon_id":1});
const artSchema = mongoose.Schema({
    Beacon_id: { type: String, required: true },
    Title: String,
    Artist: Array,
    ConstituentID: Array,
    ArtistBio: Array,
    Nationality: Array,
    BeginDate: Array,
    EndDate: Array,
    Gender: Array,
    Date: String,
    Medium: String,
    Dimensions: String,
    CreditLine: String,
    AccessionNumber: Number,
    Classification: String,
    Department: String,
    DateAcquired: Date,
    Cataloged: String,
    ObjectID: Number,
    URL: String,
    ThumbnailURL: String,
    "Height (cm)": Number,
    "Width (cm)": Number
});

const Artwork = mongoose.model('Artwork', artSchema, 'artworks');

module.exports = Artwork;