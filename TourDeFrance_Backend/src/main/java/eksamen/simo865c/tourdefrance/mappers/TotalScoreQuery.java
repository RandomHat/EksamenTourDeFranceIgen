package eksamen.simo865c.tourdefrance.mappers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalScoreQuery {
    private long totalTime;
    private long totalSprintPoints;
    private long totalMountainPoints;
}
