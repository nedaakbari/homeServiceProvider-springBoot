package ir.maktab.homeserviceprovider.dto;

import ir.maktab.data.enums.OfferStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OfferDto {

    private OfferStatus status;
    private Double proposedPrice;
    private String description;
    private Double duringTime;

   // @DateTimeFormat(pattern = "HH:MM:SS a")//todo
    @DateTimeFormat(pattern = "HH:MM:SS")
  //  @NotNull(message = "nou null")
    private Date startWorkTime;
    private OrdersDto orders;
    private ExpertDto expert;
    private Date submissionDate;
    private String codeNumber= UUID.randomUUID().toString();
}
