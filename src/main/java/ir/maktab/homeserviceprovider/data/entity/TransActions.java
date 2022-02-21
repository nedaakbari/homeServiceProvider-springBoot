package ir.maktab.homeserviceprovider.data.entity;

import ir.maktab.data.entity.Person.Customer;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class TransActions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    private long expertAccNumber;

    //@Size(max = 16)
    private long accNumber;


    private int cvv2;

    @CreationTimestamp
    private Date transferDate;

    private double amount;

    //private Date timeOut;

    private long trackingNumber = makeRandomNumber();//کد رهگیری

    @Temporal(TemporalType.DATE)
    private Date expireDate;

    @OneToOne
    private Orders orders;

    public long makeRandomNumber() {
        long leftLimit = 100000000000000L;
        long rightLimit = 1000000000000000L;
        return leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    }
}
