package tn.esprit.artifact.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.esprit.artifact.enums.Role;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private Role role;


}
