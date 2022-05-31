package eksamen.simo865c.tourdefrance.api;

import eksamen.simo865c.tourdefrance.dto.RiderCreationDTO;
import eksamen.simo865c.tourdefrance.dto.RiderDTO;
import eksamen.simo865c.tourdefrance.dto.TotalScoreDTO;
import eksamen.simo865c.tourdefrance.services.RiderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/riders")
@AllArgsConstructor
@CrossOrigin
public class RiderController {

    private RiderService service;

    @GetMapping
    public ResponseEntity<List<RiderDTO>> getRiders(@RequestParam(name = "team", required = false) String teamName){
        List<RiderDTO> riders;

        try {
            if (teamName != null) {
                riders = service.getRiderByTeam(teamName);
            } else {
                riders = service.getRiders();
            }

            return ResponseEntity.ok(riders);
        }
        catch (NotFoundException err){
            // TODO ErrorHandling here;
            return null;
        }
    }

    @GetMapping("/{id}" )
    public ResponseEntity<RiderDTO> getRider(@PathVariable int id){
        return ResponseEntity.ok(service.findRider(id));
    }

    @PostMapping
    public ResponseEntity<RiderDTO> create(@RequestBody RiderCreationDTO rider){
        RiderDTO riderDTO = service.createRider(rider);
        return ResponseEntity.ok(riderDTO);
    }

    @PutMapping()
    public ResponseEntity<RiderDTO> edit(@RequestBody RiderDTO riderDTO){
        return ResponseEntity.ok().body(service.updateRider(riderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRider(@PathVariable int id){
        try {
            service.deleteRider(id);
            return ResponseEntity.ok("{\"message\": \"Rider was deleted succesfully\"}");
        } catch (Exception ex){
            return ResponseEntity.badRequest().body("Check that id is correct.");
        }
    }
}
