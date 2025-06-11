package tn.esprit.artifact.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.artifact.dto.auth.AuthenticationRequest;
import tn.esprit.artifact.dto.auth.AuthenticationResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import tn.esprit.artifact.dto.auth.RegisterRequest;
import tn.esprit.artifact.entity.User;
import tn.esprit.artifact.repository.UserRepository;
import tn.esprit.artifact.security.JwtService;
import tn.esprit.artifact.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.HttpHeaders;



@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {

        var user  = User.builder()
                .email(request.getEmail())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        userRepository.save(user);

        return AuthenticationResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {

        var user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        // Authenticate
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String jwtToken = jwtService.generateToken(user);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(jwtToken);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .email(user.getEmail())
                .role(user.getRole())
                .build();


    }


//    public void logout(String name) {
////        sessionRegistry.removeSession(name);
//    }
}
