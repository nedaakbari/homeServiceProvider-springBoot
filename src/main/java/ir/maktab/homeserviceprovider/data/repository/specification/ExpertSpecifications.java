package ir.maktab.homeserviceprovider.data.repository.specification;

import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.entity.service.SubCategory;
import ir.maktab.dto.ExpertFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface ExpertSpecifications {

    static Specification<Expert> filterExpert(ExpertFilterDto dto) {
        return (root, cq, cb) -> {//criteriaQuery criteriaBuilder
            CriteriaQuery<Expert> resultCriteria = cb.createQuery(Expert.class);
            List<Predicate> predicates = new ArrayList<>();
            if (dto.getSubCategoryTitle()!=null) {
                Join<Expert, SubCategory> subCategoryList = root.join("subCategoryList");
                predicates.add(cb.equal(subCategoryList.get("title"), dto.getSubCategoryTitle()));
            }
            if (!dto.getFirstName().isEmpty())
                predicates.add(cb.equal(root.get("firstName"), dto.getFirstName()));
            if (!dto.getLastName().isEmpty())
                predicates.add(cb.equal(root.get("lastName"), dto.getLastName()));
            if (!dto.getEmail().isEmpty())
                predicates.add(cb.equal(root.get("email"), dto.getEmail()));
            if (dto.getScore()!=0)
                predicates.add(cb.equal(root.get("score"), dto.getEmail()));
            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
            //return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
