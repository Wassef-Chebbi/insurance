package tn.esprit.artifact.controller;


import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.artifact.entity.Claim;
import tn.esprit.artifact.service.ClaimService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClaimController {

    @Autowired
    ClaimService claimService;

    @PostMapping("/sinistre")
    public ResponseEntity<Claim> addSinistre(@RequestBody Claim claim) {
        Claim addedClaim = claimService.createClaim(claim);
        return new ResponseEntity<>(addedClaim, HttpStatus.CREATED);
    }


    @GetMapping("/sinistre/{sinistreId}")
    @Operation(summary = "Say Hello", description = "Returns a greeting message.")
    public ResponseEntity<Claim> showSinistreByid(@PathVariable Long sinistreId) {
        Claim claim = claimService.getClaimById(sinistreId);
        if (claim != null) {
            return ResponseEntity.ok(claim);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sinistre")
    public ResponseEntity<List<Claim>> getAllSinistres() {
        List<Claim> claims = claimService.getAllClaims();
        return ResponseEntity.ok(claims);
    }

    @PutMapping("/sinistre/{sinistreId}")
    public ResponseEntity<Claim> updateSinistre(@PathVariable("sinistreId") Long sinistreId, @RequestBody Claim updatedClaim) {
        try {
            Claim updated = claimService.updateClaim(sinistreId, updatedClaim);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/sinistre/{sinistreId}")
    public ResponseEntity<Void> deleteSinistre(@PathVariable Long sinistreId) {
        try {
            Claim deletedClaim = claimService.deleteClaim(sinistreId);

            // Create the response message
            String message = "Contrat with ID " + sinistreId + " deleted successfully.";

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
