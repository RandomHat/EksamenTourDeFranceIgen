package eksamen.simo865c.tourdefrance.repositories;

import eksamen.simo865c.tourdefrance.entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RiderRepository extends JpaRepository<Rider, Integer> {
    Optional<List<Rider>> findAllByTeamName(String name);
}
