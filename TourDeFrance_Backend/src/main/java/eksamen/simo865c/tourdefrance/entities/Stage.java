package eksamen.simo865c.tourdefrance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter @Setter
public class Stage {

    @Id
    @GeneratedValue
    private int id;

    private int routeNo;

    private String description;

    @OneToMany(mappedBy = "stage", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Score> scores;

    @ManyToOne()
    private Competition competition;

    public Stage(){
        this.scores = new HashSet<>();
    }

    /** A Method that filters through the list of scores associated with the stage and aggregates the winning scores.
     *  The scores are not counted multiple times, if the same score wins on multiple parameters.
        @return a list of all winning scores in a stage. Includes ties.
     */
    public Set<Score> winningScores(){

        Set<Score> winners = new HashSet<>();
        List<Score> unordered = new ArrayList<>(scores);
        List<Comparator<Score>> comparators = Arrays.asList(new Score.ByTime(), new Score.BySprintPoints(), new Score.ByMountainPoints());

        comparators.forEach((comparator) -> {
            unordered.sort(comparator);

            Score winningScore = unordered.get(0);
            winners.add(winningScore);

            // Also add ties if any
            int i = 1;
            while(comparator.compare(winningScore, unordered.get(i)) == 0){
                winners.add(unordered.get(i));
                i++;
            }
        });

       return winners;
    }

    public void addScore(Score score){
        this.scores.add(score);
        score.setStage(this);
    }

    public void removeScore(Score score){
        this.scores.remove(score);
        score.setStage(null);
    }
}
