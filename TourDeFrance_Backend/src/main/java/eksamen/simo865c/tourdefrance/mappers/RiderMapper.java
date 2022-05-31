package eksamen.simo865c.tourdefrance.mappers;

import eksamen.simo865c.tourdefrance.dto.RiderCreationDTO;
import eksamen.simo865c.tourdefrance.dto.RiderDTO;
import eksamen.simo865c.tourdefrance.entities.Rider;
import org.springframework.stereotype.Component;

@Component
public class RiderMapper{

    public RiderDTO toDto(Rider rider) {
        return new RiderDTO(
                rider.getId(),
                rider.getFirstName(),
                rider.getLastName(),
                rider.getAge(),
                rider.getTeam().getName());
    }

    public Rider fromDto(RiderCreationDTO rider) {
        Rider newRider = new Rider();
        newRider.setFirstName(rider.getFirstName());
        newRider.setLastName(rider.getLastName());
        newRider.setAge(rider.getAge());
        return newRider;
    }
}
