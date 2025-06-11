package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.Claim;

import java.util.List;

public interface ClaimService {

    Claim createClaim(Claim Claim);

    Claim updateClaim(Long id, Claim Claim);

    List<Claim> getAllClaims();

    Claim getClaimById(Long id);

    Claim deleteClaim(Long id);
}
