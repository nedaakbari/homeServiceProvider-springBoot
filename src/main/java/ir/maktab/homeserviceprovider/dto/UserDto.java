package ir.maktab.homeserviceprovider.dto;

import ir.maktab.data.enums.Role;
import ir.maktab.service.validation.OnLogin;
import ir.maktab.service.validation.OnRegister;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    @Size(min = 3, max = 10, message = "length should be in between 3 to 10", groups = OnRegister.class)
    private String firstName;

    @Size(min = 5, max = 15, message = "length should be in between 5 to 15", groups = OnRegister.class)
    private String lastName;

    @Email(message = "Email has invalid format", groups = {OnLogin.class, OnRegister.class})
    private String email;

    @NotBlank(message = "should not be empty", groups = OnRegister.class)
    private String username;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "length must be 8,include number,character,capital word", groups = {OnLogin.class, OnRegister.class})
    private String password;

    private Role role;
    private Date registerDate;
    private double creditCart;

}
