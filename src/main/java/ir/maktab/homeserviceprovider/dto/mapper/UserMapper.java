package ir.maktab.homeserviceprovider.dto.mapper;

import ir.maktab.data.entity.Person.User;
import ir.maktab.dto.AdminDto;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toUserDto(User user){
        return UserDto.builder()
                .email(user.getEmail())
                .creditCart(user.getCreditCart())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public User toUser(UserDto userDto){
        return User.builder()
                .email(userDto.getEmail())
                .creditCart(userDto.getCreditCart())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .role(userDto.getRole())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }
    public AdminDto toAdminDto(UserDto userDto){
        return AdminDto.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .username(userDto.getUsername())
                .build();
    }
    public ExpertDto toExpertDto(UserDto userDto){
        return ExpertDto.builder()
                .email(userDto.getEmail())
                .creditCart(userDto.getCreditCart())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .role(userDto.getRole())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }

    public CustomerDto toCustomerDto(UserDto userDto){
        return CustomerDto.builder()
                .email(userDto.getEmail())
                .creditCart(userDto.getCreditCart())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .role(userDto.getRole())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
    }


}
