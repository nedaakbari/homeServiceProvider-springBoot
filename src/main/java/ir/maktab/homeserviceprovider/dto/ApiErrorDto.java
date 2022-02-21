package ir.maktab.homeserviceprovider.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ApiErrorDto {
    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private String message;
    private List<String> validationErrors;

    public ApiErrorDto(HttpStatus status, String message, List<String> validationErrors) {
        timestamp = new Date();
        this.status = status;
        this.message = message;
        this.validationErrors = validationErrors;
    }
}
