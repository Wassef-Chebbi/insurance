package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.Contract;

import java.util.List;

public interface ContractService {

    Contract createContract(Contract Contract);

    Contract updateContract(Long id, Contract Contract);

    List<Contract> getAllContracts();

    Contract getContractById(Long id);

    Contract deleteContract(Long id);

}
