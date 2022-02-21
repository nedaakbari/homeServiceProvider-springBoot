package ir.maktab.homeserviceprovider.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class BaseDto {

    private int pageNumber = 0;

    @Min(value = 1,message = "Page size must not be less than one!!!")
    private int pageSize = 10;


}
