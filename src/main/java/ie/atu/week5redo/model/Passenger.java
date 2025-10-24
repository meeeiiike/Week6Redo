package ie.atu.week5redo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor@Builder
public class Passenger {
    @NotBlank@Size(max = 40)
    private String passengerId;
    @NotBlank@Size(max = 100)
    private String name;
    @Email@NotBlank
    private String email;
}
