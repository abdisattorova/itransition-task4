package com.itransition.usermanagementsystem.dto;

import com.itransition.validation.PasswordsEqualConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@PasswordsEqualConstraint.List({
        @PasswordsEqualConstraint(
                field = "password",
                fieldMatch = "confirmPassword",
                message = "Passwords do not match!"
        )
})

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRegistrationDto {

    @Size(min = 3, message = "Full name should consist of at least 3 letters")
    private String fullName;

    @NotEmpty(message = "Email can't be empty")
    private String email;

    @NotBlank(message = "New password is mandatory")
    private String password;

    @NotBlank(message = "Confirm Password is mandatory")
    private String confirmPassword;

}
