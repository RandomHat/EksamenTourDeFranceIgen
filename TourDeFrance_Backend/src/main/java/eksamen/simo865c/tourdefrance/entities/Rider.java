package eksamen.simo865c.tourdefrance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Rider {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;
    private int age;

    @ManyToOne
    private Team team;
}
