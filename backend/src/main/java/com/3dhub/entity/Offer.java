package com.greencrane.entity;

import com.greencrane.consts.OfferType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offer")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name="offer_id"))
public class Offer extends AbstractEntity {

    @Column(name = "text")
    private String text;
    @Column(name = "title")
    private String title;
    @Column(name = "offer_type")
    private OfferType type;
    @Column(name = "image_path")
    private String imagePath;

    @ManyToMany(mappedBy = "offers")
    private List<Customer> customers;

    public void addCustomer(Customer customer) {
        if(this.customers == null) this.customers = new ArrayList<>();
        this.customers.add(customer);
    }

    @Override
    public String toString() {
        return getOfferAsString(this);
    }

    public static String getOffersAsString(List<Offer> offers) {
       return new StringBuilder().append(mapOffersAsString(offers)).append(", ").append('\n').toString();
    }

    private static String mapOffersAsString(List<Offer> offers) {
        return offers.stream().map(o -> new StringBuilder().append(getOfferAsString(o)).toString())
                .reduce(", \n", String::concat);
    }

    private static String getOfferAsString(Offer offer) {
        return new StringBuilder().append('\n').append("Oferta numer: ").append(offer.getId()).append(", ").append('\n')
                .append("text: ").append(offer.getText()).append(", ").append('\n')
                .append("tytul: ").append(offer.getTitle()).append(", ").append('\n')
                .append("typ: ").append(offer.getType().name).toString();
    }
}
