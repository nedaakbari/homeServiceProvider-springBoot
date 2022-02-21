package ir.maktab.homeserviceprovider.data.repository;

import ir.maktab.data.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    Optional<Address> findAddressesByZipCode(String zipCode);

}
