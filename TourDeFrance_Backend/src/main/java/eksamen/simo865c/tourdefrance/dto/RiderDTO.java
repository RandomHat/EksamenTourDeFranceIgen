package eksamen.simo865c.tourdefrance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class RiderDTO {
    int id;
    String firstName;
    String lastName;
    int age;
    String teamName;
}
