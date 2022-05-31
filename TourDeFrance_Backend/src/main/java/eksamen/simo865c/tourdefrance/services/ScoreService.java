package eksamen.simo865c.tourdefrance.services;

import eksamen.simo865c.tourdefrance.dto.TotalScoreDTO;
import eksamen.simo865c.tourdefrance.entities.Rider;
import eksamen.simo865c.tourdefrance.repositories.CompetitionRepository;
import eksamen.simo865c.tourdefrance.repositories.RiderRepository;
import eksamen.simo865c.tourdefrance.repositories.ScoreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScoreService {

    private RiderRepository riderRepository;
    private ScoreRepository scoreRepository;

    public Set<TotalScoreDTO> getScores(){

        List<Rider> riders = riderRepository.findAll();
        Set<TotalScoreDTO> scores = riders.stream().map(rider -> (scoreRepository.cumulativeScoresByRider_toDTO(rider))).collect(Collectors.toSet());

        return scores;
    }
}
