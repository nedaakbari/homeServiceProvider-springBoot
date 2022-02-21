package ir.maktab.homeserviceprovider.util.requestFilter;

import ir.maktab.data.entity.service.SubCategory;
import ir.maktab.data.enums.Role;
import ir.maktab.data.enums.UserRegistrationStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFilter {
    private Role role;
    private String firstName;
    private String email;
    private String lastName;
    private UserRegistrationStatus status;
    private SubCategory subService;
}
