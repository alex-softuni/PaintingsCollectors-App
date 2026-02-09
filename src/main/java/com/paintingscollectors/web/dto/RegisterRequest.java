package com.paintingscollectors.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotNull
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters.")
    private String username;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
    private String password;

    @NotNull
    @Size(min = 3, max = 20, message = "Password must be between 3 and 20 characters.")
    private String confirmPassword;

}
