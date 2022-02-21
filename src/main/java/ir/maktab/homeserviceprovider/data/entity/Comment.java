package ir.maktab.homeserviceprovider.data.entity;

import ir.maktab.homeserviceprovider.data.entity.Person.Customer;
import ir.maktab.homeserviceprovider.data.entity.Person.Expert;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String comment;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Expert expert;

    @OneToOne
    private Orders order;

    private String codeNumber = UUID.randomUUID().toString();

}
