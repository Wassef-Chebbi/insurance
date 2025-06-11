package tn.esprit.artifact.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.artifact.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long>{

}
