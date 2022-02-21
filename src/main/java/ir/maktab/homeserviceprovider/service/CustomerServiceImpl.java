package ir.maktab.homeserviceprovider.service;

import ir.maktab.data.entity.Person.Customer;
import ir.maktab.data.enums.Role;
import ir.maktab.data.enums.UserRegistrationStatus;
import ir.maktab.data.repository.CustomerRepository;
import ir.maktab.dto.CustomerDto;
import ir.maktab.service.exception.DuplicateData;
import ir.maktab.service.exception.IncorrectInformation;
import ir.maktab.service.exception.NotFoundDta;
import ir.maktab.service.exception.UserNotFoundException;
import ir.maktab.service.interfaces.CustomerService;
import ir.maktab.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerDao;
    //private final CustomerMapper customerMapper;
    private final UserService userService;
    private final ModelMapper mapper;

    @Override
    public CustomerDto register(CustomerDto customerDto) {//register
        Optional<Customer> foundUser = customerDao.findByUsernameAndPassword
                (customerDto.getUsername(), customerDto.getPassword());

        if (foundUser.isPresent()) {
            throw new DuplicateData("this customer is already exist");
        } else {
            boolean isDuplicate = userService.isDuplicateEmail(customerDto.getEmail());
            if (!isDuplicate) {
               // foundUser.get().setConfirmationToken(UUID.randomUUID().toString());
                Customer customer = mapper.map(customerDto, Customer.class);
                customer.setStatus(UserRegistrationStatus.NEW);
                customer.setRole(Role.CUSTOMER);
                Customer save = customerDao.save(customer);
                //return customerMapper.toCustomerDto(save);
                return mapper.map(save, CustomerDto.class);
            } else
                throw new DuplicateData("this email is already exist");
        }
    }

    @Override
    public CustomerDto login(CustomerDto customerDto)  {
        Optional<Customer> customer = customerDao.findByEmailAndPassword(
                customerDto.getEmail(),customerDto.getPassword());
        if (customer.isPresent())
            return mapper.map(customer.get(),CustomerDto.class);
        else
            throw new NotFoundDta("no user found with these info");
    }


    @Override
    public void delete(CustomerDto customerDto) {
        Optional<Customer> customer = customerDao.findByEmail(customerDto.getEmail());
        if (customer.isPresent())
            customerDao.delete(customer.get());
        else
            throw new UserNotFoundException();
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerDao.findAll().stream()
                .map(customer -> mapper.map(customer, CustomerDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDto getById(Integer theId) {
        Optional<Customer> found = customerDao.findById(theId);
        if (found.isPresent())
            return mapper.map(found.get(), CustomerDto.class);
        else throw new NotFoundDta("no customer found");
    }

    @Override
    public CustomerDto getByEmail(String email) {
        Optional<Customer> found = customerDao.findByEmail(email);
        if (found.isPresent())
            return mapper.map(found.get(), CustomerDto.class);
        else throw new NotFoundDta("no customer found");
    }

    @Override
    public void updatePassword(String newPassword, String oldPassword, CustomerDto customerDto) {
        if (customerDto.getPassword().equals(oldPassword)) {
            customerDao.updatePassword(customerDto.getEmail(), newPassword);
        } else
            throw new IncorrectInformation("incorrect password");
        //customerDao.updatePassword(email, newPassword);
    }

    @Override
    public void updateCreditCart(double amount, CustomerDto customerDto) {
        customerDao.updateCreditCart(customerDto.getEmail(), amount);
    }

    @Override
    public CustomerDto findCustomerByUseAndPass(String username, String password) {
        Optional<Customer> customer = customerDao.findByUsernameAndPassword(username, password);
        if (customer.isPresent()) {
            return mapper.map(customer.get(), CustomerDto.class);
        } else
            throw new NotFoundDta("no customer found with these use and pass");
    }


}
