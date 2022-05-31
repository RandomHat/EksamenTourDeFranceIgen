package eksamen.simo865c.tourdefrance.repositories;

import eksamen.simo865c.tourdefrance.entities.Rider;
import eksamen.simo865c.tourdefrance.entities.Score;
import eksamen.simo865c.tourdefrance.mappers.TotalScoreQuery;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScoreRepositoryTest {

    @Autowired
    ScoreRepository scoreRepository;

    static Rider rider1;

    @BeforeAll
    static void setup(@Autowired ScoreRepository scoreRepository, @Autowired RiderRepository riderRepository){
        riderRepository.deleteAll();
        scoreRepository.deleteAll();
        Rider rider = new Rider();
        rider.setFirstName("testRider");
        rider.setLastName("testRider");
        rider.setAge(26);

        rider1 = riderRepository.save(rider);

        for(int i = 0; i<5; i++){
            Score score = new Score();
            score.setRider(rider1);
            score.setTimeInSeconds(1);
            score.setSprintPoints(10);
            score.setMountainPoints(100);

            scoreRepository.save(score);
        }
    }

    @Test
    void cumulativeScoresByRiderId() {
        // Arrange
        int[] expected = {5,50,500};

        // Act
        TotalScoreQuery actual = scoreRepository.cumulativeScoresByRider(rider1);

        // Assert
        assertEquals(expected[0], actual.getTotalTime());
        assertEquals(expected[1], actual.getTotalSprintPoints());
        assertEquals(expected[2], actual.getTotalMountainPoints());
    }
}