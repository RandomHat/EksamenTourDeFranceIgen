package eksamen.simo865c.tourdefrance.services;

import eksamen.simo865c.tourdefrance.dto.RiderCreationDTO;
import eksamen.simo865c.tourdefrance.dto.RiderDTO;
import eksamen.simo865c.tourdefrance.dto.TotalScoreDTO;
import eksamen.simo865c.tourdefrance.entities.Rider;
import eksamen.simo865c.tourdefrance.entities.Team;
import eksamen.simo865c.tourdefrance.error.Client4xxException;
import eksamen.simo865c.tourdefrance.mappers.RiderMapper;
import eksamen.simo865c.tourdefrance.repositories.RiderRepository;
import eksamen.simo865c.tourdefrance.repositories.ScoreRepository;
import eksamen.simo865c.tourdefrance.repositories.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RiderService {

    private final RiderRepository riderRepository;
    private final ScoreRepository scoreRepository;
    private final TeamRepository teamRepository;
    private final RiderMapper mapper;

    public RiderDTO findRider(int id){
        return mapper.toDto(riderRepository.findById(id).orElseThrow(() -> new Client4xxException("rider was not found", HttpStatus.BAD_REQUEST)));
    }

    public List<RiderDTO> getRiders(){
        return riderRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<RiderDTO> getRiderByTeam(String name){
        List<Rider> riders = riderRepository.findAllByTeamName(name).orElseThrow(() -> new Client4xxException("Team does not exist, or has a typo in the name", HttpStatus.BAD_REQUEST));
        return riders.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public RiderDTO createRider(RiderCreationDTO rider){
        Team team = teamRepository.findById(rider.getTeamName()).orElseThrow();
        Rider newRider = mapper.fromDto(rider);
        team.addRider(newRider);
        teamRepository.save(team);
        return mapper.toDto(newRider);
    }

    public RiderDTO updateRider(RiderDTO rider){
        Rider updateRider = riderRepository.findById(rider.getId()).orElseThrow(() -> new Client4xxException("rider was not found", HttpStatus.BAD_REQUEST));
        updateRider.setFirstName(rider.getFirstName());
        updateRider.setLastName(rider.getLastName());
        updateRider.setAge(rider.getAge());
        return mapper.toDto(riderRepository.save(updateRider));
    }

    public void deleteRider(int id) {
        Rider rider = riderRepository.findById(id).orElseThrow(() -> new Client4xxException("The rider couldn't be deleted, id is missing", HttpStatus.BAD_REQUEST));
        scoreRepository.findAllByRider(rider).forEach(score -> score.setRider(null));
        rider.getTeam().removeRider(rider);
        riderRepository.delete(rider);
    }
}
