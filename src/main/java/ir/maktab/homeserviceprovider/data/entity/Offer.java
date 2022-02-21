package ir.maktab.homeserviceprovider.data.entity;

import ir.maktab.data.entity.Person.Expert;
import ir.maktab.data.enums.OfferStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OfferStatus status;

    private Double proposedPrice;//قیمت پیشنهادی

    private String description;

    private Double duringTime;//مدت زمان انجام کار

    @Temporal(TemporalType.TIME)
    private Date startWorkTime;//ساعت شروع کار

    @CreationTimestamp
    private Date submissionDate;//زمان ثبت پیشنهاد

    @ManyToOne
    @JoinColumn(nullable = false)
    private Expert expert;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Orders orders;

    private String codeNumber ;
    ;//کد رهگیری

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", proposedPriceOffer=" + proposedPrice +
                ", description='" + description + '\'' +
                ", duringTime=" + duringTime +
                ", startWorkTime=" + startWorkTime +
                ", submissionDate=" + submissionDate +
                ", expert=" + expert +
                '}';
    }
}
