package ir.maktab.homeserviceprovider.data.entity.Person;

import ir.maktab.homeserviceprovider.data.entity.Comment;
import ir.maktab.homeserviceprovider.data.entity.Orders;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
//@ToString(callSuper = true)
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class Customer extends User {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Orders> ordersList = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private List<Comment> comments = new ArrayList<>();

    @Override
    public String toString() {
        return "Customer => " + super.toString();
    }
}
