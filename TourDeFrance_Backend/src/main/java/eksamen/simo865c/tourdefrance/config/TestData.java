package eksamen.simo865c.tourdefrance.config;

import eksamen.simo865c.tourdefrance.entities.*;
import eksamen.simo865c.tourdefrance.repositories.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.Set;


@Component
@AllArgsConstructor
@Profile("!test")
@Transactional
public class TestData implements ApplicationRunner {

    CompetitionRepository competitionRepository;

    public void makeTournament(){
        Competition competition = new Competition();
        competition.setName("Tour De France");
        competition.setCompetitionYear(2022);

        Stage stage1 = new Stage();
        stage1.setRouteNo(1);
        stage1.setDescription("Enkeltstart i København");
        competition.addStage(stage1);

        Stage stage2 = new Stage();
        stage2.setRouteNo(2);
        stage2.setDescription("Rute fra Roskilde til Nyborg, har 3 Bjergspurter og en pointspurt");
        competition.addStage(stage2);

        Stage stage3 = new Stage();
        stage3.setRouteNo(3);
        stage3.setDescription("Vejle til Sønderborg, ligesom 2. rute er der indlagt 3 bjergspurter og en pointspurt");
        competition.addStage(stage3);

        makeParticipants(competition);
        makeResults(competition);

        competitionRepository.save(competition);
    }

    public void makeParticipants(Competition competition){
        Team team1 = new Team();
        team1.setName("Quick-Step Alpha Vinyl Team");

        Rider rider1 = new Rider();
        rider1.setAge(37);
        rider1.setFirstName("Michael");
        rider1.setLastName("Mørkøv");
        team1.addRider(rider1);

        Rider rider2 = new Rider();
        rider2.setAge(27);
        rider2.setFirstName("Kasper");
        rider2.setLastName("Asgreen");
        team1.addRider(rider2);

        Rider rider3 = new Rider();
        rider3.setAge(25);
        rider3.setFirstName("Mikkel");
        rider3.setLastName("Frølich Honoré");
        team1.addRider(rider3);

        Team team2 = new Team();
        team2.setName("EF Education-EasyPost");

        Rider rider4 = new Rider();
        rider4.setFirstName("Michael");
        rider4.setLastName("Valgren");
        rider4.setAge(30);
        team2.addRider(rider4);

        Rider rider5 = new Rider();
        rider5.setFirstName("Magnus");
        rider5.setLastName("Cort");
        rider5.setAge(29);
        team2.addRider(rider5);

        Rider rider6 = new Rider();
        rider6.setAge(25);
        rider6.setFirstName("Mark");
        rider6.setLastName("Padun");
        team2.addRider(rider6);

        competition.addTeam(team1);
        competition.addTeam(team2);
    }

    public void makeResults(Competition competition){
        Set<Stage> stages = competition.getStages();
        Set<Team> teams = competition.getParticipatingTeams();
        Random random = new Random();
        PrimitiveIterator.OfInt rTimes = random.ints(18, 5000, 15000).iterator();
        PrimitiveIterator.OfInt rSprint = random.ints(18,0,55).iterator();
        PrimitiveIterator.OfInt rMountain = random.ints(6,1,3).iterator();

        stages.forEach(stage -> {
            teams.forEach(team ->{
                team.getRiders().forEach(rider -> {
                    Score score = new Score();

                    score.setRider(rider);
                    score.setTimeInSeconds(rTimes.next());
                    score.setSprintPoints(rSprint.next());
                    if(rMountain.hasNext() && random.nextBoolean()){
                        score.setMountainPoints(rMountain.next());
                    } else {
                        score.setMountainPoints(0);
                    }

                    stage.addScore(score);
                });
            });
        });
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        makeTournament();
    }
}
