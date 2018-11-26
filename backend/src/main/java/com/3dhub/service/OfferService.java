package com.greencrane.service;

import com.greencrane.consts.OfferType;
import com.greencrane.entity.Offer;
import com.greencrane.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface OfferService {
    List<Offer> findByType(OfferType offerType);
}

@Service
class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public List<Offer> findByType(OfferType offerType) {
        if(offerType.equals(OfferType.ALL)) return getAllTypes();
        return offerRepository.findByType(offerType);
    }

    private List<Offer> getAllTypes() {
        return Arrays
                .stream(OfferType.values())
                .map(type -> offerRepository.findByType(type))
                .collect(Collectors.toList())
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
