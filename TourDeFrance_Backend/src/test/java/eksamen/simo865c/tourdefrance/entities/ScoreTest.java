package eksamen.simo865c.tourdefrance.entities;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    private static Score s1;
    private static Score s2;
    private static Score s3;

    @BeforeAll
    public static void setup(){
        s1 = new Score(0, null, null, 100, 10, 10);
        s2 = new Score(0, null, null, 50, 30, 20);
        s3 = new Score(0, null, null, 10, 20, 30);
    }

    @Test
    public void SortByTime_resultsIn_True(){
        // Arrange
        List<Score> scores = Arrays.asList(s1,s2,s3);

        // Act
        scores.sort(new Score.ByTime());

        // Assert
        assertEquals(s1.getTimeInSeconds(), scores.get(0).getTimeInSeconds());
    }

    @Test
    public void SortByTime_resultsIn_False(){
        // Arrange
        List<Score> scores = Arrays.asList(s1,s2,s3);

        // Act
        scores.sort(new Score.ByTime());

        // Assert
        assertNotEquals(s3.getTimeInSeconds(), scores.get(0).getTimeInSeconds());
    }

    @Test
    public void SortBySprintPoints_resultsIn_True(){
        // Arrange
        List<Score> scores = Arrays.asList(s1,s2,s3);

        // Act
        scores.sort(new Score.BySprintPoints());

        // Assert
        assertEquals(s2.getTimeInSeconds(), scores.get(0).getTimeInSeconds());
    }

    @Test
    public void SortBySprintPoints_resultsIn_False(){
        // Arrange
        List<Score> scores = Arrays.asList(s1,s2,s3);

        // Act
        scores.sort(new Score.BySprintPoints());

        // Assert
        assertNotEquals(s1.getTimeInSeconds(), scores.get(0).getTimeInSeconds());
    }

    @Test
    public void SortByMountainPoints_resultsIn_True(){
        // Arrange
        List<Score> scores = Arrays.asList(s1,s2,s3);

        // Act
        scores.sort(new Score.ByMountainPoints());

        // Assert
        assertEquals(s3.getTimeInSeconds(), scores.get(0).getTimeInSeconds());
    }

    @Test
    public void SortByMountainPoints_resultsIn_False(){
        // Arrange
        List<Score> scores = Arrays.asList(s1,s2,s3);

        // Act
        scores.sort(new Score.ByMountainPoints());

        // Assert
        assertNotEquals(s1.getTimeInSeconds(), scores.get(0).getTimeInSeconds());
    }

}