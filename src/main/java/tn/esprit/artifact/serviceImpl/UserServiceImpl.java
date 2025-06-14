package tn.esprit.artifact.serviceImpl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.*;
import tn.esprit.artifact.enums.Role;
import tn.esprit.artifact.repository.ContractRepository;
import tn.esprit.artifact.repository.UserRepository;
import tn.esprit.artifact.service.UserService;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {

            // Update only the provided fields
            if (user.getPassword() != null && !user.getPassword().isBlank()) {
                existingUser.setPassword(user.getPassword());
            }

            if (user.getEmail() != null && !user.getEmail().isBlank()) {
                existingUser.setEmail(user.getEmail());
            }

            if (user.getNumber() != null && !user.getNumber().isBlank()) {
                existingUser.setNumber(user.getNumber());
            }

            if (user.getFirstname() != null && !user.getFirstname().isBlank()) {
                existingUser.setFirstname(user.getFirstname());
            }

            if (user.getLastname() != null && !user.getLastname().isBlank()) {
                existingUser.setLastname(user.getLastname());
            }

            if (user.getContracts() != null) {
                existingUser.setContracts(user.getContracts());
            }

            // If you plan to support updating role/type/service later, uncomment/update those here.
            // Example:
             if (user.getRole() != null) {
                 existingUser.setRole(user.getRole());
             }

            return userRepository.save(existingUser);
        }).orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + id));
    }



    @Override
    public List<User> getAllUsers() {
        Iterable<User> usersIterable = userRepository.findAll();
        List<User> usersList = new ArrayList<>();
        for (User user : usersIterable) {
            usersList.add(user);
        }
        return usersList;
    }


    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteUser(Long id) {
        try{
            Optional<User> optionalUser = userRepository.findById(id);



            // If the user exists, retrieve it
            User userToDelete = optionalUser.get();

            // Delete the user by its ID
            userRepository.deleteById(id);

            // Return the deleted stage
            return userToDelete;
        } catch(Exception e) {
            // If the stage does not exist, throw an exception or handle it in any other appropriate way
            throw new IllegalArgumentException("user not found");
        }

    }




}
