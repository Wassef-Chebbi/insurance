package tn.esprit.artifact.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.artifact.entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim,Long> {
}
