const express = require('express');
const router = express.Router();

const Artwork = require('../models/artwork');

router.get('/:id', function (req, res) {
    Artwork.find({ 'Beacon_id': req.params.id }).exec((err, artwork) => {
        if (err) return res.status(500).json({ error: err });
        if (!artwork) return res.status(404).json({ message: 'Artworks not found' });
        res.json(artwork);
    });
});
module.exports = router;