package tn.esprit.artifact.service;

import tn.esprit.artifact.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User updateUser(Long id, User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User deleteUser(Long id);



}
