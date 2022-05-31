package eksamen.simo865c.tourdefrance.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StageTest {

    private final Stage stage = new Stage();
    private Set<Score> winners = new HashSet<>();

    @BeforeEach
    void setupEach(){
        Set<Score> scores = new HashSet<>();
        for (int i = 1; i<11; i++){
            Score score = new Score(i,null, null, i, i, i);
            if (i == 1){ // add time winner
                score.setTimeInSeconds(1000);
                winners.add(score);
            }
            if(i == 5){ // add mountain winner
                score.setMountainPoints(50);
                winners.add(score);
            }
            if(i == 10){ // add sprint winner
                // Same Score wins both sprint and mountain
                score.setSprintPoints(50);
                winners.add(score);
            }
            scores.add(score);
        }
        stage.setScores(scores);
    }

    @Test
    void winningScores_No_Ties() {
        // Act
        Set<Score> winnersFromTest = stage.winningScores();

        // Assert
        assertNotSame(winners, winnersFromTest);
        assertEquals(winners, winnersFromTest);
    }

    @Test
    void winningScores_Should_Include_Ties() {
        // Arrange
        Score scoreWithTie = new Score(11, null, null, 0, 0, 50);
        stage.getScores().add(scoreWithTie);
        winners.add(scoreWithTie);


        // Act
        Set<Score> winnersFromTest = stage.winningScores();

        // Assert
        assertNotSame(winners, winnersFromTest);
        assertEquals(winners, winnersFromTest);
    }

    @Test
    void winningScores_should_not_include_duplicates(){
        // Arrange
        Score singleWinner = new Score(11, null, null, 2000, 2000, 2000);
        stage.getScores().add(singleWinner);
        winners = Set.of(singleWinner);

        // Act
        Set<Score> winnersFromTest = stage.winningScores();

        // Assert
        assertNotSame(winners, winnersFromTest);
        assertEquals(winners, winnersFromTest);
    }
}