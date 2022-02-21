package ir.maktab.homeserviceprovider.data.repository;

import ir.maktab.data.entity.TransActions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransActionRepository extends JpaRepository<TransActions, Long> {

}
