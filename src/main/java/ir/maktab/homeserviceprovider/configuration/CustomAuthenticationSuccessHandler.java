package ir.maktab.homeserviceprovider.configuration;

import ir.maktab.dto.CustomerDto;
import ir.maktab.dto.ExpertDto;
import ir.maktab.service.interfaces.CustomerService;
import ir.maktab.service.interfaces.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


@Configuration
@RequiredArgsConstructor
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final CustomerService customerService;
    private final ExpertService expertService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
            , Authentication authentication) throws IOException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        HttpSession session = httpServletRequest.getSession();
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();

        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin/dashboard");

        } else if (roles.contains("ROLE_CUSTOMER")) {
            CustomerDto customerDto = customerService.getByEmail(username);
            session.setAttribute("customerDto", customerDto);
            httpServletResponse.sendRedirect("/customer/dashboard");
        } else if (roles.contains("ROLE_EXPERT")) {
            ExpertDto expertDto = expertService.findByEmail(username);
            session.setAttribute("expertDto", expertDto);
            httpServletResponse.sendRedirect("/expert/dashboard");
        }
    }
}