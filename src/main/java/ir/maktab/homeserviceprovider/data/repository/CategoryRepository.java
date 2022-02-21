package ir.maktab.homeserviceprovider.data.repository;

import ir.maktab.data.entity.service.Category;
import ir.maktab.data.entity.service.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByTitle(String title);

    @Modifying
    @Transactional
    Integer deleteByTitle(String title);

    @Modifying
    @Transactional
    @Query(value = "update Category  c set c.subServiceList=:subServiceList where c.title=:title")
    void updateSubCategory(@Param("title") String title, @Param("subServiceList") Set<SubCategory> subServiceList);

}
