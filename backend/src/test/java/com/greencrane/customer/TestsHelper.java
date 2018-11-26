package com.greencrane.customer;

import com.greencrane.consts.OfferType;
import com.greencrane.entity.Customer;
import com.greencrane.entity.Offer;

/**
 * Created by Marcin on 26.09.2018.
 */
public abstract class TestsHelper {

    protected Offer createOffer() {
        return new OfferHelper().createOffer();
    }

    protected Customer createCustomer() {
        return new CustomerHelper().createCustomer();
    }

    private static class OfferHelper {
        private static final String OFFER_TEXT = "text";
        private static final String OFFER_TITLE = "title";

        private Offer createOffer() {
            Offer offer = new Offer();
            offer.setText(OFFER_TEXT);
            offer.setTitle(OFFER_TITLE);
            offer.setType(OfferType.CRANE);
            return offer;
        }
    }

    private static class CustomerHelper {
        private static final String ADDITIONAL_INFO = "additional_info";
        private static final String EMAIL = "95959595956@wp.pl";
        private static final String MOB_PHONE = "111-222-333";
        private static final String NAME = "Marcin";
        private static final String SKILLS = "skills";

        protected Customer createCustomer() {
            Customer customer = new Customer();
            customer.setAdditionalInfo(ADDITIONAL_INFO);
            customer.setEmail(EMAIL);
            customer.setMobPhone(MOB_PHONE);
            customer.setName(NAME);
            customer.setSkills(SKILLS);
            customer.addOffer(new OfferHelper().createOffer());
            customer.addOffer(new OfferHelper().createOffer());
            customer.addOffer(new OfferHelper().createOffer());
            return customer;
        }
    }
}
