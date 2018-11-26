package com.greencrane.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greencrane.utils.ListUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 23.09.2018.
 */
@Entity
@Table(name = "customer")
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name="customer_id"))
public class Customer extends AbstractEntity {

    private static final String EMPTY_OFFERS = "Prośba o ogólny kontakt (brak konkretnej oferty)";

    @Column(name = "name")
    @JsonProperty("name")
    private String name;
    @Column(name = "mob_phone")
    @JsonProperty("phoneNumber")
    private String mobPhone;
    @Column(name = "email")
    @JsonProperty("emailAddress")
    private String email;
    @Column(name = "skills")
    @JsonProperty("typeOfPermission")
    private String skills;
    @Column(name = "additional_info")
    @JsonProperty("infoFromUser")
    private String additionalInfo;

    @Column(name = "mod_date")
    @JsonIgnore
    private Date modDate;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Communication> communications;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "customer_offer",
            joinColumns = { @JoinColumn(name = "customer_id") },
            inverseJoinColumns = { @JoinColumn(name = "offer_id") }
    )
    @JsonIgnore
    private List<Offer> offers;

    @Transient
    @JsonProperty("idOfOffer")
    private int offerId;

    public void addOffer(Offer offer) {
        if(this.offers == null) this.offers = new ArrayList<>();
        if(offer != null) this.offers.add(offer);
    }

    @Override
    public String toString() {
        return new StringBuilder().append("Prośba o kontakt od: ").append(name).append(", ").append('\n')
                .append("numer telfonu: ").append(mobPhone).append(", ").append('\n')
                .append("email: ").append(email).append(", ").append('\n')
                .append("wprowadzone umiejętności: ").append(skills).append(", ").append('\n')
                .append("dodatkowe informacje: ").append(additionalInfo).append(", ").append('\n')
                .append("lista ofert: ").append(getOffersAsString()).append(".").toString();
    }

    private String getOffersAsString() {
        if(ListUtils.isEmpty(this.offers)) return EMPTY_OFFERS;
        return Offer.getOffersAsString(this.offers);
    }
}
