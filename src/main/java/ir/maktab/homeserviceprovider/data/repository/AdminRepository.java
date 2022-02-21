package ir.maktab.homeserviceprovider.data.repository;

import ir.maktab.data.entity.Person.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByUsernameAndPassword(String username, String password);

    Optional<Admin> findByEmail(String email);

}
