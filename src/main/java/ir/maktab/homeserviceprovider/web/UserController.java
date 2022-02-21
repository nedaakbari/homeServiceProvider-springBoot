
package ir.maktab.homeserviceprovider.web;

import ir.maktab.configuration.LastViewInterceptor;
import ir.maktab.data.enums.Role;
import ir.maktab.data.repository.UserRepository;
import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.dto.UserDto;
import ir.maktab.dto.UserFilterDto;
import ir.maktab.dto.mapper.UserMapper;
import ir.maktab.service.exception.ExpertNotFoundException;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.interfaces.CustomerService;
import ir.maktab.service.interfaces.ExpertService;
import ir.maktab.service.interfaces.UserService;
import ir.maktab.service.validation.OnRegister;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class UserController {
    private final UserService userService;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    /* @Autowired
    public ConfirmationTokenService confirmationTokenService;*/

    @GetMapping("/")
    public String display(HttpSession httpsession, SessionStatus status) {
        status.setComplete();
        httpsession.invalidate();
        return "homePage";
    }

    @RequestMapping(value = "/signOut", method = RequestMethod.GET)
    public String logout(HttpSession httpsession, SessionStatus status) {
        status.setComplete();
        httpsession.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public ModelAndView showRegisterPage() {
        return new ModelAndView("register", "user", new UserDto());
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Validated(OnRegister.class) UserDto dto,
                           @RequestParam("image") CommonsMultipartFile image, Model model) {
        if (dto.getRole().equals(Role.EXPERT)) {//image== null
            ExpertDto expertDto = userMapper.toExpertDto(dto);
            model.addAttribute("expertDto", expertDto);
            expertService.register(expertDto, image);
        } else {
            CustomerDto customerDto = userMapper.toCustomerDto(dto);
            model.addAttribute("customerDto", customerDto);
            customerService.register(customerDto);

        }



/*
        log.info("register user");
        User user = userRepository.findByEmail(dto.getEmail()).get();
        try {
            ConfirmationToken confirmationToken = ConfirmationToken.builder()
                    .userEntity(user)
                    .confirmationToken(UUID.randomUUID().toString())
                    .build();
            confirmationTokenDao.save(confirmationToken);
            String text = "To confirm your account, please click here : "
                    + "http://localhost:8080/expert/confirm-account?token=" + confirmationToken.getConfirmationToken();
            MailService.sendMail(user.getEmail(), "verify email", text);
            log.info("email send to customer");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

 */
        String message="confirmationMessage A confirmation e-mail has been successfully sent to " + dto.getEmail()+ " ";
        model.addAttribute("message",message);
        return "emailNotif";
    }

  /*  @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(HttpSession session, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenDao.findByConfirmationToken(confirmationToken);

        if (token != null) {
            User user = userService.findByEmail(token.getUserEntity().getEmail()).get();
            userService.updateStatus(user.getEmail(), UserRegistrationStatus.WAITING_FOR_CONFIRM);
            confirmationTokenDao.delete(token);
            session.setAttribute("messageSuccess", "your email verified successfully,please wait to confirm by manager");
        } else {
            session.setAttribute("error", "The link is invalid or broken!");
        }
        return "redirect:/";
    }*/

  /*  @PostMapping("/customer/register")
    public String registerCustomer(@ModelAttribute("customer") @Validated(OnRegister.class) CustomerDto dto,
                                   HttpServletRequest request, Model model) {
        CustomerDto customer = service.register(dto);
        Map<String, Object> map = new HashMap<>();
        map.put("name", customer.getFirstName());
        map.put("lastName", customer.getLastName());
        map.put("email", customer.getEmail());
        request.getSession().setAttribute("customerDto", customer);
        model.addAllAttributes(map);

       *//* log.info("register customer");
        User user = userService.findByEmail(dto.getEmail()).get();
        try {
            ConfirmationToken confirmationToken = ConfirmationToken.builder()
                    .userEntity(user)
                    .confirmationToken(UUID.randomUUID().toString())
                    .build();
            confirmationTokenDao.save(confirmationToken);
            String text = "To confirm your account, please click here : "
                    + "http://localhost:8080/expert/confirm-account?token=" + confirmationToken.getConfirmationToken();
            MailService.sendMail(user.getEmail(), "verify email", text);
            log.info("email send to customer");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }*//*
        return "emailNotif";
    }
*/
    @GetMapping("/login")
    public ModelAndView showLoginPage() {
        return new ModelAndView("login", "userDto", new UserDto());
    }
/*
    @PostMapping("/login")
    public ModelAndView doLogin(@ModelAttribute("userDto") @Validated(OnLogin.class)UserDto userDto, ModelAndView modelAndView, HttpSession session) {
        UserDto found = userService.getByEmail(userDto.getEmail());
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userDto.getUsername());
        map.put("creditCart", userDto.getCreditCart());
        map.put("name", userDto.getFirstName());
        map.put("lastName", userDto.getFirstName());
        map.put("role", userDto.getRole());
    if (found.getRole().equals(Role.EXPERT)) {
        ExpertDto expertDto = expertService.findByEmail(found.getEmail());
        modelAndView.addObject("expertDto", expertDto);
        map.put("accNum", expertDto.getAccNumber());
        session.setAttribute("expertDto", expertDto);
        modelAndView.setViewName("expertPanel/profile");
    }
    if (found.getRole().equals(Role.CUSTOMER)) {
        CustomerDto customerDto = customerService.getByEmail(found.getEmail());
        modelAndView.addObject("customerDto", customerDto);
        session.setAttribute("customerDto", customerDto);
        modelAndView.setViewName("customerPanel/profile");
    }
        modelAndView.addObject(map);
        return modelAndView;
    }*/

  /*  private void sendMail(UserDto userDto) throws EmailNotSendException {
        ConfirmationTokenDto newDtoToken = new ConfirmationTokenDto();
        newDtoToken.setUserDto(userDto);
        String token = confirmationTokenService.saveToken(newDtoToken);
        String mailText = env.getProperty("Email.Verification.Text") + token;
        try {
            MailService.sendMail(userDto.getEmail(), env.getProperty("Email.Verification.Subject"), mailText);
        } catch (MessagingException | IOException e) {
            throw new EmailNotSendException(env.getProperty("Email.Sending.Failed"));
        }
    }*/


    @GetMapping(value = "ajax")
    public String showProductList(Model model) {
        model.addAttribute("userFilterDto", new UserFilterDto());
        return "userListAjax";
    }

    @PostMapping("ajax/search")
    public ModelAndView searchProducts(@ModelAttribute("userFilterDto") UserFilterDto dto,
                                       HttpSession session) {
        List<UserDto> userDtos = userService.searchUsers(dto);
        session.setAttribute("userDtos", userDtos);
        return new ModelAndView("userListAjax", "userDtos", userDtos);
    }


    //search?minPrice=100&maxPrice=700
    @GetMapping("ajax/search")
    public ModelAndView searchExperts(@RequestParam(value = "firstName", required = false) String firstName,
                                      @RequestParam(value = "lastName", required = false) String lastName,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "role", required = false) Role role) {
        UserFilterDto dto = new UserFilterDto();
        dto.setFirstName(firstName);
        dto.setLastName(lastName);
        dto.setEmail(email);
        dto.setRole(role);
        List<UserDto> userDtos = userService.searchUsers(dto);
        return new ModelAndView("userListAjax", "userDtos", userDtos);
    }

    @ExceptionHandler(value = BindException.class)
    public ModelAndView bindExceptionHandler(BindException ex, HttpServletRequest request) {
        String lastView = (String) request.getSession().getAttribute(LastViewInterceptor.LAST_VIEW_ATTRIBUTE);
        return new ModelAndView(lastView, ex.getBindingResult().getModel());//مدلمو از این بایندینگ اکسپنه گرفتم
    }

    @ExceptionHandler(value = NotFoundDta.class)
    public ModelAndView loginExceptionHandler(ExpertNotFoundException ex) {
        Map<String, Object> model = new HashMap<>();
        model.put("user", new UserDto());
        model.put("error", ex.getMessage());
        return new ModelAndView("/login", model);
    }

}

