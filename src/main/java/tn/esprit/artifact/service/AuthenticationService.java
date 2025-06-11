package tn.esprit.artifact.service;

import tn.esprit.artifact.dto.auth.AuthenticationRequest;
import tn.esprit.artifact.dto.auth.AuthenticationResponse;
import tn.esprit.artifact.dto.auth.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}
