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
    @NotBlank(message = " [WARNING] ***ID Cannot be Blank*** ")
    @Size(max = 40)
    private String passengerId;
    @NotBlank(message = " [WARNING] ***Name Cannot be Blank*** ")
    @Size(max = 100)
    private String name;
    @Email(message = " [WARNING] ***Email must not be Blank!***!")
    @NotBlank(message = " [WARNING] ***Email Cannot be Blank*** ")
    private String email;
}