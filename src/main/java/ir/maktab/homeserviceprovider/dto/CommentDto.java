package ir.maktab.homeserviceprovider.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommentDto {

    private String comment;
    private CustomerDto customer;
    private ExpertDto expert;
    private OrdersDto order;
    private String codeNumber;

}
