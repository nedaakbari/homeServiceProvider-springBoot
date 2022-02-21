package ir.maktab.homeserviceprovider.dto;

import ir.maktab.data.enums.OrderState;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrdersDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "not null")
    private Date doWorkDate;
    private String description;
    private AddressDto address;
    private SubCategoryDto subCategory;
    private CustomerDto customer;
    private ExpertDto expert;
    private String codeNumber= UUID.randomUUID().toString();
    private OrderState state;
    private Double score;
    private String comment;
    private Double agreedPrice;
    private Date orderRegistrationDate;
}
