package ir.maktab.homeserviceprovider.data.repository;

import ir.maktab.data.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Lazy
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long>, JpaSpecificationExecutor<Orders> {

    @Query(value = "from Orders O join fetch O.subCategory S where S.id=:id and (O.state='WAITING_FOR_EXPERT_SUGGESTION' or O.state='WAITING_FOR_SELECT_AN_EXPERT') ")
    List<Orders> findOrdersOfSubService(@Param("id") int serviceId);

    @Query(value = "from Orders o join fetch o.customer c where c.id=:id order by o.orderRegistrationDate desc ")
    List<Orders> findOrderOfCustomer(@Param("id") int customerId);
    //todo and O.state NOT IN ('PAID') //and o.state not in (:state)

    Optional<Orders> findByCodeNumber(String uuid);

}
