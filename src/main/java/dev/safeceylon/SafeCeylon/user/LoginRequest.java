package dev.safeceylon.SafeCeylon.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password; // Frontend-hashed password
}
