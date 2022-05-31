package eksamen.simo865c.tourdefrance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Team {

    @Id
    private String name;

    @OneToMany(mappedBy = "team", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Rider> riders;

    @ManyToMany(mappedBy = "participatingTeams")
    private Set<Competition> competitions;

    public Team(){
        this.riders = new HashSet<>();
        this.competitions = new HashSet<>();
    }

    public void addRider(Rider rider){
        this.riders.add(rider);
        rider.setTeam(this);
    }

    public void removeRider(Rider rider){
        this.riders.remove(rider);
        rider.setTeam(null);
    }
}
