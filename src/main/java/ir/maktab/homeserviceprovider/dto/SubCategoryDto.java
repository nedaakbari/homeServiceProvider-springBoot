package ir.maktab.homeserviceprovider.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubCategoryDto {
    @NotBlank(message = "title should not be empty")
    private String title;
    @NotBlank(message = "basePrice should not be empty")
    private Double basePrice;
    private String description;
    //private CategoryDto category;
}
