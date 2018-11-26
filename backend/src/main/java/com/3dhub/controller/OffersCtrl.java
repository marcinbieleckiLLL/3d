package com.greencrane.controller;

import com.greencrane.consts.OfferType;
import com.greencrane.entity.Offer;
import com.greencrane.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class OffersCtrl {

    @Autowired
    private OfferService offerService;

    @PostMapping(path = "/getOffers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:4200")
    public List<Offer> getOfers(@RequestBody OfferType type) {
        return offerService.findByType(type);
    }
}