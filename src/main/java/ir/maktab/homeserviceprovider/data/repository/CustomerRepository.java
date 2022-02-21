package ir.maktab.homeserviceprovider.data.repository;

import ir.maktab.data.entity.Person.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findById(int id);

    Optional<Customer> findByUsernameAndPassword(String userName, String password);

    @Modifying
    @Transactional
    @Query(value = "update Customer c set c.creditCart=:credit where  c.email=:email")
    void updateCreditCart(@Param("email") String email, @Param("credit") double credit);

    @Modifying
    @Transactional
    @Query(value = "update Customer c set c.password=:password where c.email=:email")
    void updatePassword(@Param("email") String email, @Param("password") String password);

}
