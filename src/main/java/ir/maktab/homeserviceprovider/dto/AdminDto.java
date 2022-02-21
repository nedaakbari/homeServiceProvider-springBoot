package ir.maktab.homeserviceprovider.dto;

import ir.maktab.data.enums.Role;
import ir.maktab.service.validation.OnLogin;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDto {
    private int id;


    private String username;

    @NotBlank(message = "field empty", groups = {OnLogin.class})
    private String password;

    @NotBlank(message = "field empty", groups = {OnLogin.class})
    private String email;

    private Role role;

}
