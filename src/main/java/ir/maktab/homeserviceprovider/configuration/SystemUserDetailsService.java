package ir.maktab.homeserviceprovider.configuration;

import ir.maktab.data.entity.Person.Admin;
import ir.maktab.data.repository.AdminRepository;
import ir.maktab.data.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


public class SystemUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;
    private final AdminRepository managerRepository;
    private final PasswordEncoder passwordEncoder;

    public SystemUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder
            , AdminRepository managerRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ir.maktab.data.entity.Person.User> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isEmpty()) {
            Optional<Admin> optionalManager = managerRepository.findByEmail(username);
            if (optionalManager.isEmpty())
                throw new UsernameNotFoundException(username);
            else {
                Admin manager = optionalManager.get();
                return User.withUsername(manager.getEmail())
                        .password(passwordEncoder.encode(manager.getPassword())).roles("ADMIN").build();
            }
        }
        ir.maktab.data.entity.Person.User user = optionalUser.get();
        return User.withUsername(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword())).roles(user.getRole().toString()).build();
    }
}