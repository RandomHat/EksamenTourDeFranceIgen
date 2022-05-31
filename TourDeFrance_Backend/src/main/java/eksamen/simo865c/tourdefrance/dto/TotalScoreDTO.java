package eksamen.simo865c.tourdefrance.dto;

import eksamen.simo865c.tourdefrance.entities.Rider;
import eksamen.simo865c.tourdefrance.mappers.RiderMapper;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class TotalScoreDTO {
    private RiderDTO rider;
    private long totalTime;
    private long totalSprintPoints;
    private long totalMountainPoints;

    public TotalScoreDTO(Rider rider, long totalTime, long totalSprintPoints, long totalMountainPoints){
        RiderMapper mapper = new RiderMapper();
        this.rider = mapper.toDto(rider);
        this.totalTime = totalTime;
        this.totalSprintPoints = totalSprintPoints;
        this.totalMountainPoints = totalMountainPoints;
    }
}
