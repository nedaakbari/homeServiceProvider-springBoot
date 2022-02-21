package ir.maktab.homeserviceprovider.data.repository.specification;

import ir.maktab.data.entity.Person.User;
import ir.maktab.dto.UserFilterDto;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public interface UserSpecifications {
    static Specification<User> filterUsers(UserFilterDto dto) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            boolean b = dto.getRole() == null;
            boolean b1 = dto.getFirstName().equals("");

            if ( !dto.getFirstName().isEmpty())
                predicates.add(cb.equal(root.get("firstName"), dto.getFirstName()));
            if (!dto.getLastName().isEmpty())
                predicates.add(cb.equal(root.get("lastName"), dto.getLastName()));
            if (!dto.getEmail().isEmpty())
                predicates.add(cb.equal(root.get("email"), dto .getEmail()));
            if ( dto.getRole()!=null)
                predicates.add(cb.equal(root.get("role"), dto.getRole()));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }


    static Specification<User> filterUserstwo(UserFilterDto dto) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if ( dto.getRegisterDate()!=null)
                predicates.add(cb.equal(root.get("registerDate"), dto.getRegisterDate()));
            if ( !dto.getFirstName().isEmpty())
                predicates.add(cb.equal(root.get("firstName"), dto.getFirstName()));
            if (!dto.getLastName().isEmpty())
                predicates.add(cb.equal(root.get("lastName"), dto.getLastName()));
            if (!dto.getEmail().isEmpty())
                predicates.add(cb.equal(root.get("email"), dto .getEmail()));
            if ( dto.getRole()!=null)
                predicates.add(cb.equal(root.get("role"), dto.getRole()));
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
