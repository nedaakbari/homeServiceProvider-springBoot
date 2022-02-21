package ir.maktab.homeserviceprovider.data.repository;

import ir.maktab.data.entity.Person.User;
import ir.maktab.data.enums.UserRegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsernameAndPassword(String userName, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String email, String password);

    @Query("FROM User u where u.status='NEW'")
    List<User> findAllUserForVerify();

    @Modifying
    @Transactional
    @Query(value = "update User u set u.status=:status where  u.email=:email")
    void updateStatus(@Param("email") String email, @Param("status") UserRegistrationStatus status);


}


