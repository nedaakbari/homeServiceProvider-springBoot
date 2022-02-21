package ir.maktab.homeserviceprovider.service.interfaces;

import ir.maktab.data.entity.Person.User;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserFilterDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    void register(UserDto userDto);

    UserDto login(UserDto userDto);

    void delete(UserDto userDto);

    void updateUser(UserDto userDto);

    void updateCreditCart(double amount, UserDto userDto);

    void updatePassword(String newPassword, UserDto userDto);

    List<UserDto> getAll();

    List<User> findAll();;

    UserDto getById(Integer theId);

    UserDto getByEmail(String email);

    UserDto findUserByUseAndPass(String userName, String password);

    boolean isDuplicateEmail(String email);

    List<UserDto> findAllUsersByFilter(UserFilterDto userFilterDto);

    //List<UserDto> findByPagination(int firstPage, int pageSize, String firstname, String lastname, String email, Role role);

    List<UserDto> searchUsers(UserFilterDto dto);

    void updateStatus(String userEmail);
    Page<User> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
