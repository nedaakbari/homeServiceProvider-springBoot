package ir.maktab.homeserviceprovider.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AddressDto {
    private String city;
    private String street;
    private String zipCode;

    @Override
    public String toString() {
        return " city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", zipCode='" + zipCode ;
    }
}
