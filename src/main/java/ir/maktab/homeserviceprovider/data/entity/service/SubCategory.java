package ir.maktab.homeserviceprovider.data.entity.service;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @NotNull(message = "❌❌❌ field of mainService can't be empty ❌❌❌")
    private Category category;

    @Column(unique = true)
    private String title;

    private Double basePrice;

    private String description;

}
