package eksamen.simo865c.tourdefrance.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Comparator;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Score {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Rider rider;

    @ManyToOne
    private Stage stage;

    private int timeInSeconds;

    private int sprintPoints;

    private int mountainPoints;

    public static class ByTime implements Comparator<Score> {
        @Override
        public int compare(Score score, Score t1) {
            return t1.timeInSeconds - score.timeInSeconds;
        }
    }

    public static class BySprintPoints implements Comparator<Score> {

        @Override
        public int compare(Score score, Score t1) {
            return t1.sprintPoints - score.sprintPoints;
        }
    }

    public static class ByMountainPoints implements Comparator<Score> {
        @Override
        public int compare(Score score, Score t1) {
            return  t1.mountainPoints - score.mountainPoints;
        }
    }
}


