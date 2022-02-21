package ir.maktab.homeserviceprovider.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {
    private int id;

    @NotBlank(message = "should not be empty")
    private String title;
}
