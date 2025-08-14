package com.tap.dto;

import com.tap.entities.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreationDto {

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be 8 characters long")
    private String password;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Role is required")
    private Role.RoleName roleName;

}
