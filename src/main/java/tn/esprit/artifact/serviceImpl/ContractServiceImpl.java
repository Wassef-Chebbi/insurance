package tn.esprit.artifact.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.Contract;
import tn.esprit.artifact.repository.ContractRepository;
import tn.esprit.artifact.service.ContractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract createContract(Contract contract) {


        return contractRepository.save(contract);
    }

    @Override
    public Contract updateContract(Long id, Contract contract) {
        Optional<Contract> optionalContrat = contractRepository.findById(id);

        if (optionalContrat.isPresent()) {
            Contract existingContract = optionalContrat.get();

            // Update fields only if they are not null
            if (contract.getDateDebut() != null) {
                existingContract.setDateDebut(contract.getDateDebut());
            }
            if (contract.getDateFin() != null) {
                existingContract.setDateFin(contract.getDateFin());
            }
            if (contract.getStatut() != null) {
                existingContract.setStatut(contract.getStatut());
            }
            if (contract.getOffer() != null) {
                existingContract.setOffer(contract.getOffer());
            }
            if (contract.getUser() != null) {
                existingContract.setUser(contract.getUser());
            }else {
                existingContract.setUser(null); // Or handle as appropriate if null should be explicitly set
            }



            // Save the updated contrat entity
            return contractRepository.save(existingContract);
        } else {
            // Handle the case where the contrat with the given ID is not found
            throw new IllegalArgumentException("Contrat not found with ID: " + id);
        }
    }


    @Override
    public List<Contract> getAllContracts() {
        Iterable<Contract> contratsIterable = contractRepository.findAll();
        List<Contract> contratsList = new ArrayList<>();
        for (Contract contract : contratsIterable) {
            contratsList.add(contract);
        }
        return contratsList;
    }

    @Override
    public Contract getContractById(Long id) {
        return contractRepository.findById(id).orElse(null);
    }

    @Override
    public Contract deleteContract(Long id) {
        try{
            Optional<Contract> optionalContrat = contractRepository.findById(id);



            // If the contrat exists, retrieve it
            Contract contractToDelete = optionalContrat.get();

            // Delete the contrat by its ID
            contractRepository.deleteById(id);

            // Return the deleted stage
            return contractToDelete;
        } catch(Exception e) {
            // If the stage does not exist, throw an exception or handle it in any other appropriate way
            throw new IllegalArgumentException("contrat not found");
        }

    }



}
