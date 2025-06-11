package tn.esprit.artifact.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.Claim;
import tn.esprit.artifact.repository.ClaimRepository;
import tn.esprit.artifact.service.ClaimService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
public class ClaimServiceImpl implements ClaimService {

    @Autowired
    private ClaimRepository claimRepository;


    @Override
    public Claim createClaim(Claim Claim) {
        return claimRepository.save(Claim);
    }

    @Override
    public Claim updateClaim(Long id, Claim Claim) {
        return null;
    }

    @Override
    public List<Claim> getAllClaims() {
        Iterable<Claim> sinistresIterable = claimRepository.findAll();
        List<Claim> sinistresList = new ArrayList<>();
        for (Claim claim : sinistresIterable) {
            sinistresList.add(claim);
        }
        return sinistresList;
    }

    @Override
    public Claim getClaimById(Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    @Override
    public Claim deleteClaim(Long id) {
        try{
            Optional<Claim> optionalSinistre = claimRepository.findById(id);



            // If the contrat exists, retrieve it
            Claim claimToDelete = optionalSinistre.get();

            // Delete the contrat by its ID
            claimRepository.deleteById(id);

            // Return the deleted stage
            return claimToDelete;
        } catch(Exception e) {
            // If the stage does not exist, throw an exception or handle it in any other appropriate way
            throw new IllegalArgumentException("sinistre not found");
        }
    }
}
