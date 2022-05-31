package eksamen.simo865c.tourdefrance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RiderCreationDTO {
    private String firstName;
    private String lastName;
    private int age;
    private String teamName;
}
