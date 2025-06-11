package tn.esprit.artifact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tn.esprit.artifact.entity.Offer;
import tn.esprit.artifact.service.OfferService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OfferController {

    @Autowired
    OfferService offerService;

    @PostMapping("/offre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Offer> addOffre(@RequestBody Offer offer) {
        Offer addedOffer = offerService.createOffer(offer);
        return new ResponseEntity<>(addedOffer, HttpStatus.CREATED);
    }

    @GetMapping("/offre/{offreId}")
    public ResponseEntity<Offer> showOffreByid(@PathVariable Long offreId) {
        Offer offer = offerService.getOfferById(offreId);
        if (offer != null) {
            return ResponseEntity.ok(offer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/offre")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Offer>> getAllOffres() {
        List<Offer> offers = offerService.getAllOffers();
        return ResponseEntity.ok(offers);
    }

    @PutMapping("/offre/{offreId}")
    public ResponseEntity<Offer> updateOffre(@PathVariable("offreId") Long offreId, @RequestBody Offer updatedOffer) {
        try {
            Offer updated = offerService.updateOffer(offreId, updatedOffer);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/offre/{offreId}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long offreId) {
        try {
            Offer deletedOffer = offerService.deleteOffer(offreId);

            // Create the response message
            String message = "Offre with ID " + offreId + " deleted successfully.";

            // Create the response headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Message", message);
            // Return the response entity with the response object and status OK
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }

        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }





}
