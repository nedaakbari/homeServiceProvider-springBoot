package ir.maktab.homeserviceprovider.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ExpertFilterDto extends BaseDto {

    private String firstName;
    private String lastName;
    private String email;
    private double score;
    private String subCategoryTitle;

}
