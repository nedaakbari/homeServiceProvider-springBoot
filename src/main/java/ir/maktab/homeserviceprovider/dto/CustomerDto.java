package ir.maktab.homeserviceprovider.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@ToString
@SuperBuilder
public class CustomerDto extends UserDto{
    // private Set<OrdersDto> ordersList = new HashSet<>();
    //private List<CommentDto> comments = new ArrayList<>();
}
