package ie.atu.week5redo.controller.errorHandling;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data@AllArgsConstructor@NoArgsConstructor
public class ExceptionDetails {
    private String fieldName;
    private String errorMessage;
    private LocalDateTime timestamp;
    private int status;

}
