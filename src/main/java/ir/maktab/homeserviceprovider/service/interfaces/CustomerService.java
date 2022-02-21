package ir.maktab.homeserviceprovider.service.interfaces;

import ir.maktab.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto register(CustomerDto customerDto);

    CustomerDto login(CustomerDto customerDto);

    void delete(CustomerDto customerDto);

    List<CustomerDto> getAll();

    CustomerDto getById(Integer theId);

    CustomerDto getByEmail(String email);

   // void updatePassword(String newPassword, String oldPassword, String email);
    void updatePassword(String newPassword, String oldPassword, CustomerDto customerDto);

    CustomerDto findCustomerByUseAndPass(String username, String password);

    void updateCreditCart(double amount, CustomerDto customerDto);

}
