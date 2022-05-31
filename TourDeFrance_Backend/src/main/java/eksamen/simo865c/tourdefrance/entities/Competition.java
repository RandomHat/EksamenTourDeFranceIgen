package eksamen.simo865c.tourdefrance.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Competition {

    @Id
    @GeneratedValue
    private int id;

    private String name;
    private int competitionYear;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "team_participating",
            joinColumns = @JoinColumn(name = "competition_id"),
            inverseJoinColumns = @JoinColumn(name = "team_name")
    )
    private Set<Team> participatingTeams;

    @OneToMany(mappedBy = "competition", cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER) // Since Routes change every Year, it is better to create new routes with a creation of a new Tournament.
    private Set<Stage> stages;

    public Competition(){
        this.participatingTeams = new HashSet<>();
        this.stages = new HashSet<>();
    }

    public void addTeam(Team team){
        this.participatingTeams.add(team);
        team.getCompetitions().add(this);
    }

    public void removeTeam(Team team){
        this.participatingTeams.remove(team);
        team.getCompetitions().remove(this);
    }

    public void addStage(Stage stage){
        this.stages.add(stage);
        stage.setCompetition(this);
    }

    public void removeStage(Stage stage){
        this.stages.remove(stage);
        stage.setCompetition(null);
    }
}
