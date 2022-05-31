import { handleErrors, makeOptions } from "../fetchUtils.js";
import { SERVER_URL } from "../settings.js";

export async function getScores(){

    let options = makeOptions("GET")
    let URL = SERVER_URL + "/scores"

    const result = await fetch(URL, options).then(res => handleErrors(res))

    console.log(result)

    const yellow = {};
    const polkadot = {};
    const white = {};
    const green = {};

    result.forEach(score => {
        console.log(score)
        if(score.totalTime > yellow){
            yellow.score = score.totalTime
            yellow.winner = score.rider
        }
        if(score.totalSprintPoints > green){
            green.score = score.totalSprintPoints;
            green.winner = score.rider
        }
        if(score.totalMountainPoints > polkadot){
            polkadot.score = score.totalMountainPoints;
            polkadot.winner = score.rider
        }
        if(score.rider.age <= 26 && score.totalTime > white){
            white.score = score.totalTime
            white.winner = score.rider
        }
    });

    document.getElementById("shirts").innerHTML = `
    <div class="row justify-content-center">
    <div class="col"><div class=row> The winner is:${yellow.winner.firstName} ${yellow.winner.firstName} with a score of: ${yellow.score}</div><div class="row"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ7yv6ZhnHwAQS4Kwc0aopELkoK4_cTAJ4ykg&usqp=CAU" heigh="300" with="200"></div></div>
    <div class="col"><div class=row> The winner is:${polkadot.winner.firstName} ${polkadot.winner.firstName} with a score of: ${polkadot.score}</div><div class="row"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSpnIfn8D-OolbXyCx9Du-71TWHl9w1x5rn9A&usqp=CAU" heigh="300" with="200"></div></div>
    <div class="col"><div class=row> The winner is:${green.winner.firstName} ${green.winner.firstName} with a score of: ${green.score}</div><div class="row"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9Z71lbfo2rtC2S3hxa24AB8JNieheVkHvzw&usqp=CAU" heigh="300" with="200"></div></div>
    <div class="col"><div class=row> The winner is:${white.winner.firstName} ${white.winner.firstName} with a score of: ${white.score}</div><div class="row"><img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtkqkHGSfTsblfgEXf26NRP4WKqCDmxPfE08APNe9aL-CdWqhSgBUzUipwbg68Wsx3Log&usqp=CAU" heigh="300" with="200"></div></div>
    </div>`;
}