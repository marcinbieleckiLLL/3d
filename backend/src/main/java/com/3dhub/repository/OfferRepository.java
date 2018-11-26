package com.greencrane.repository;

import com.greencrane.consts.OfferType;
import com.greencrane.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface OfferRepository extends JpaRepository<Offer, Integer> {
    List<Offer> findByType(OfferType offerType);
    Optional<Offer> findById(Integer id);
}
