package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.Offer;

import java.util.List;

public interface OfferService {

    Offer createOffer(Offer Offer);

    Offer updateOffer(Long id, Offer Offer);

    List<Offer> getAllOffers();

    Offer getOfferById(Long id);

    Offer deleteOffer(Long id);

}
