package tn.esprit.artifact.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.artifact.dto.auth.AuthenticationRequest;
import tn.esprit.artifact.dto.auth.AuthenticationResponse;
import tn.esprit.artifact.dto.auth.RegisterRequest;
import tn.esprit.artifact.enums.Role;
import tn.esprit.artifact.serviceImpl.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/authentication")
@Tag(name = "Authentication Controller", description = "Operations related to authentication")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }



    @PostMapping("/authenticate")
    @Operation(summary = "Login")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.login(request));
    }



    @PostMapping("/register")
    @Operation(summary = "Create a new user")
    public ResponseEntity<AuthenticationResponse> register( Authentication authentication,
                                                            @RequestBody RegisterRequest request
    ){


            return ResponseEntity.ok(authenticationService.register(request));

    }



//    @GetMapping("/connected-users")
//    @Operation(summary = "Get Connected Users")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<List<String>> getConnectedUsers(Authentication authentication) {
//        if (authentication != null && authentication.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals(Role.ADMIN.name()))) {
//
//            List<String> connectedUsers = sessionRegistry.getActiveSessions().stream()
//                    .filter(email -> !email.equals(authentication.getName()))
//                    .collect(Collectors.toList());
//
//            return ResponseEntity.ok(connectedUsers);
//        }
//        return ResponseEntity.status(403).build();
//    }
}
