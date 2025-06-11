package tn.esprit.artifact.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.artifact.entity.Contract;
import tn.esprit.artifact.service.ContractService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ContractController {
    @Autowired
    ContractService contractService;

    @PostMapping("/contrat")
    public ResponseEntity<Contract> addContrat(@RequestBody Contract contract) {
        Contract addedContract = contractService.createContract(contract);
        return new ResponseEntity<>(addedContract, HttpStatus.CREATED);
    }

    @GetMapping("/contrat/{contratId}")
    @Operation(summary = "Say Hello", description = "Returns a greeting message.")
    public ResponseEntity<Contract> showContratByid(@PathVariable Long contratId) {
        Contract contract = contractService.getContractById(contratId);
        if (contract != null) {
            return ResponseEntity.ok(contract);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contrat")
    public ResponseEntity<List<Contract>> getAllContrats() {
        List<Contract> contracts = contractService.getAllContracts();
        return ResponseEntity.ok(contracts);
    }

    @PutMapping("/contrat/{contratId}")
    public ResponseEntity<Contract> updateContrat(@PathVariable("contratId") Long contratId, @RequestBody Contract updatedContract) {
        try {
            Contract updated = contractService.updateContract(contratId, updatedContract);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/contrat/{contratId}")
    public ResponseEntity<Void> deleteContrat(@PathVariable Long contratId) {
        try {
            Contract deletedContract = contractService.deleteContract(contratId);

            // Create the response message
            String message = "Contrat with ID " + contratId + " deleted successfully.";

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
