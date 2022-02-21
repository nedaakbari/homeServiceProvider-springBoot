package ir.maktab.homeserviceprovider.data.repository;


import ir.maktab.data.entity.service.Category;
import ir.maktab.data.entity.service.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {

    Optional<SubCategory> findByTitle(String title);

    @Query(value = "From SubCategory s where s.category =:category")
    List<SubCategory> findSubCategoryByCategory(@Param("category") Category category);

}
