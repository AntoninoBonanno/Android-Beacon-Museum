const mongoose = require('mongoose');

//mongoimport --db beacon-museum --collection artwork --file Artworks.json --jsonArray
const artSchema = mongoose.Schema({
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

const Artwork = mongoose.model('artwork', artSchema);

module.exports = Artwork;