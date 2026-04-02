package com.multigenesys.ecommerce.dto;

import com.multigenesys.ecommerce.util.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String password;

//    @NotNull
//    private Role role;

}
