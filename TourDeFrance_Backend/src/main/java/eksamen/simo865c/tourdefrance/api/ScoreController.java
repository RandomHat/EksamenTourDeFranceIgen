package eksamen.simo865c.tourdefrance.api;

import eksamen.simo865c.tourdefrance.dto.TotalScoreDTO;
import eksamen.simo865c.tourdefrance.services.ScoreService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/scores")
@CrossOrigin
@AllArgsConstructor
public class ScoreController {

    private ScoreService scoreService;

    @GetMapping()
    public List<TotalScoreDTO> finalScores(){
        return new ArrayList<>(scoreService.getScores());
    }
}
