package eksamen.simo865c.tourdefrance.api;

import eksamen.simo865c.tourdefrance.entities.Team;
import eksamen.simo865c.tourdefrance.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/teams")
@CrossOrigin
@AllArgsConstructor
public class TeamController {

    private TeamRepository teamRepository;

    @GetMapping
    public List<String> getTeams(){
        return teamRepository.findAll().stream().map(Team::getName).collect(Collectors.toList());
    }
}
