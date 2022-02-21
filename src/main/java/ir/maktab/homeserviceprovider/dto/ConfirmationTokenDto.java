package ir.maktab.homeserviceprovider.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ConfirmationTokenDto {
    String token;
    UserDto userDto;
    Date createdDate;
    Date expiryDate;
}

