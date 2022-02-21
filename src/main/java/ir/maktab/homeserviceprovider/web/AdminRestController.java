package ir.maktab.homeserviceprovider.web;

import io.swagger.annotations.Api;
import ir.maktab.data.enums.Role;
import ir.maktab.dto.ApiErrorDto;
import ir.maktab.dto.SubCategoryDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserFilterDto;
import ir.maktab.service.interfaces.CategoryService;
import ir.maktab.service.interfaces.ExpertService;
import ir.maktab.service.interfaces.SubCategoryService;
import ir.maktab.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
@Api(value = "AdminRestController")
public class AdminRestController {
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final UserService userService;
    private final ExpertService expertService;

    @PostMapping(value = "/search-users", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> searchProducts(@RequestBody @Valid UserFilterDto dto) {
        List<UserDto> userDtos = userService.searchUsers(dto);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/search-users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDto>> searchProducts(@RequestParam(value = "firstName", required = false) String firstName,
                                                        @RequestParam(value = "lastName", required = false) String lastName,
                                                        @RequestParam(value = "email", required = false) String email,
                                                        @RequestParam(value = "role", required = false) Role role) {
        UserFilterDto dto = new UserFilterDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setRole(role);
        List<UserDto> userDtos = userService.searchUsers(dto);
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping(value = "/findAllSubCategory")
    public List<SubCategoryDto> showAllSubCategories(HttpServletRequest request) {
        String categoryTitle = request.getParameter("categoryTitle");
        return subCategoryService.findAllSubCategoryOfACategory(categoryTitle);
    }

    @GetMapping(value = "/findAllUsersForRest",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> findAllUsers() {
        return userService.getAll();
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<?> bindExceptionHandler(BindException ex) {
        List<String> validationErrors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            validationErrors.add(error.getField() + " : " + error.getDefaultMessage());
        });
        ApiErrorDto apiErrorDto = new ApiErrorDto(HttpStatus.BAD_REQUEST, ex.getClass().getName(), validationErrors);
        return new ResponseEntity<>(apiErrorDto, apiErrorDto.getStatus());
    }

}
