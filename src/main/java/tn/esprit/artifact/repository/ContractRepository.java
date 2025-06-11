package tn.esprit.artifact.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.artifact.entity.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long>{


}
