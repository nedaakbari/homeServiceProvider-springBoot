package ir.maktab.homeserviceprovider.data.repository.specification;

import ir.maktab.data.entity.Orders;
import ir.maktab.data.entity.service.SubCategory;
import ir.maktab.dto.OrderFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface OrderSpecifications {

    static Specification<Orders> orderFilter(OrderFilterDto dto) {
        return (root, cq, cb) -> {
            CriteriaQuery<Orders> resultCriteria = cb.createQuery(Orders.class);
            List<Predicate> predicates = new ArrayList<>();
            if (dto.getSubTitle() != null) {
                Join<Orders, SubCategory> subCategory = root.join("subCategory");
                predicates.add(cb.equal(subCategory.get("title"), dto.getSubTitle()));
            }
            if (dto.getStartDate() != null )
                predicates.add(cb.greaterThanOrEqualTo(root.get("startDate"), dto.getStartDate()));
            if (dto.getEndDate() != null  )
                predicates.add(cb.lessThanOrEqualTo(root.get("endDate"), dto.getEndDate()));
            if (dto.getState() != null)
                predicates.add(cb.equal(root.get("state"), dto.getState()));
            resultCriteria.select(root).where(predicates.toArray(new Predicate[0]));
            return resultCriteria.getRestriction();
        };
    }

}
