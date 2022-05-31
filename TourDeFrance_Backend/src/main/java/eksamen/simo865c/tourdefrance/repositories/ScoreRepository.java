package eksamen.simo865c.tourdefrance.repositories;

import eksamen.simo865c.tourdefrance.dto.TotalScoreDTO;
import eksamen.simo865c.tourdefrance.entities.Competition;
import eksamen.simo865c.tourdefrance.entities.Rider;
import eksamen.simo865c.tourdefrance.entities.Score;
import eksamen.simo865c.tourdefrance.mappers.TotalScoreQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface ScoreRepository extends JpaRepository<Score, Integer> {

    @Query("select new eksamen.simo865c.tourdefrance.mappers.TotalScoreQuery(sum(s.timeInSeconds), sum(s.sprintPoints), sum(s.mountainPoints)) from Score s  where s.rider = ?1")
    TotalScoreQuery cumulativeScoresByRider(Rider rider);

    @Query("select new eksamen.simo865c.tourdefrance.mappers.TotalScoreQuery(sum(s.timeInSeconds), sum(s.sprintPoints), sum(s.mountainPoints)) from Score s  where s.rider = ?1 and s.stage.competition = ?2")
    TotalScoreQuery cumulativeScoresByRiderAndCompetition(Rider rider, Competition competition);

    @Query("select new eksamen.simo865c.tourdefrance.dto.TotalScoreDTO(s.rider, sum(s.timeInSeconds), sum(s.sprintPoints), sum(s.mountainPoints)) from Score s  where s.rider = ?1")
    TotalScoreDTO cumulativeScoresByRider_toDTO(Rider rider);

    @Query("select new eksamen.simo865c.tourdefrance.dto.TotalScoreDTO(s.rider, sum(s.timeInSeconds), sum(s.sprintPoints), sum(s.mountainPoints)) from Score s where s.stage.competition = ?1") //Broken
    Set<TotalScoreDTO> finalScoringByCompetition(Competition competition);

    Set<Score> findAllByRider(Rider rider);
}
