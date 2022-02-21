package ir.maktab.homeserviceprovider.dto;

import ir.maktab.data.enums.Role;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@ToString
public class UserFilterDto extends BaseDto {

    private Role role;
    @Size(min = 3, max = 10, message = "length should be between 3 to 10")
    private String firstName;
    @Size(min = 5, max = 15, message = "length should be between 5 to 15")
    private String lastName;
    @Email(message = "Email has invalid format")
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date  registerDate;
}
