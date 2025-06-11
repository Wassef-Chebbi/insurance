package tn.esprit.artifact.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.Offer;
import tn.esprit.artifact.repository.OfferRepository;
import tn.esprit.artifact.service.OfferService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class OfferServiceImpl implements OfferService {
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Offer createOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public Offer updateOffer(Long id, Offer offer) {
        Optional<Offer> optionalOffre = offerRepository.findById(id);

        if (optionalOffre.isPresent()) {
            Offer existingOffer = optionalOffre.get();

            // Update fields only if they are not null
            if (offer.getDescription() != null) {
                existingOffer.setDescription(offer.getDescription());
            }

            if (offer.getNom() != null) {
                existingOffer.setNom(offer.getNom());
            }

            if (offer.getOfferType() != null) {
                existingOffer.setOfferType(offer.getOfferType());
            }

            if (offer.getPrix() != null) {
                existingOffer.setPrix(offer.getPrix());
            }

            if (offer.getDureeMois() != null) {
                existingOffer.setDureeMois(offer.getDureeMois());
            }

            // Update Evaluations if it's not null
            if (offer.getContracts() != null) {
                existingOffer.setContracts(offer.getContracts());
            } else {
                existingOffer.setContracts(new HashSet<>()); // Or handle as appropriate if null should be explicitly set
            }

            // Save the updated offre entity
            return offerRepository.save(existingOffer);
        } else {
            // Handle the case where the offre with the given ID is not found
            throw new IllegalArgumentException("Offre not found with ID: " + id);
        }
    }


    @Override
    public List<Offer> getAllOffers() {
        Iterable<Offer> offresIterable = offerRepository.findAll();
        List<Offer> offresList = new ArrayList<>();
        for (Offer offer : offresIterable) {
            offresList.add(offer);
        }
        return offresList;
    }

    @Override
    public Offer getOfferById(Long id) {
        return offerRepository.findById(id).orElse(null);
    }

    @Override
    public Offer deleteOffer(Long id) {
        try{
            Optional<Offer> optionalOffre = offerRepository.findById(id);



            // If the offre exists, retrieve it
            Offer offerToDelete = optionalOffre.get();

            // Delete the offre by its ID
            offerRepository.deleteById(id);

            // Return the deleted stage
            return offerToDelete;
        } catch(Exception e) {
            // If the stage does not exist, throw an exception or handle it in any other appropriate way
            throw new IllegalArgumentException("offre not found");
        }

    }


}
