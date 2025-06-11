package tn.esprit.artifact.serviceImpl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.entity.*;
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
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update fields only if they are not null

            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getNumber() != null) {
                existingUser.setNumber(user.getNumber());
            }
            if (user.getFirstname() != null) {
                existingUser.setFirstname(user.getFirstname());
            }
            if (user.getLastname() != null) {
                existingUser.setLastname(user.getLastname());
            }
//            if (user.getType() != null) {
//                existingUser.setType(user.getType());
//            }

            if (user.getContracts() != null) {
                existingUser.setContracts(user.getContracts());
            }else{
                existingUser.setContracts(new HashSet<>());
            }



           /* if (user.getChefEquipeService() != null) {
                existingUser.setChefEquipeService(user.getChefEquipeService());
            }else{
                existingUser.setServiceEq(null);

            }*/


            // Save the updated user entity
            return userRepository.save(existingUser);
        } else {
            // Handle the case where the user with the given ID is not found
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
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
