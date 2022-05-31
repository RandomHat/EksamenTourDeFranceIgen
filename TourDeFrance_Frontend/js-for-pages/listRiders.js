import { SERVER_URL } from "../settings.js";
import { makeOptions, handleErrors} from "../fetchUtils.js";
import { encode, encodeURL } from "../utils.js";


export function teamsFilterHandler(){
    fetchTeams()
    fetchRiders()
    document.getElementById("button-add").onclick = addRider
    document.getElementById("button-edit").onclick = editRider
    document.getElementById("button-delete").onclick = deleteRider
}

async function fetchTeams() {
    let URL = SERVER_URL + "/teams"
    let options = makeOptions("GET", false, false)

    const result = await fetch(URL, options).then(res => handleErrors(res))

    const html =  `<option value="">` + result.map(data =>`
    <option value="${encode(data)}">${encode(data)}</option>
    `).join("")

    document.getElementById("teams-list").innerHTML = html
}

async function loadRider(evt){
    let options = makeOptions("GET")
    const URL = SERVER_URL + "/riders/" + evt.currentTarget.getAttribute("data-value")

    const rider = await fetch(URL, options).then(res => handleErrors(res))

    document.getElementById("rider-fname").value = rider.firstName
    document.getElementById("rider-lname").value = rider.lastName
    document.getElementById("rider-tname").value = rider.teamName
    document.getElementById("rider-age").value = rider.age
    document.getElementById("rider-id").value = rider.id
}

async function addRider() {

    if (document.getElementById("rider-id").value){
        clearForm()
    }

    let body = {}
    body.firstName = document.getElementById("rider-fname").value
    body.lastName = document.getElementById("rider-lname").value
    body.age = document.getElementById("rider-age").value
    body.teamName = document.getElementById("rider-tname").value

    console.log(body)

    let options = makeOptions("POST", body, false)
    let URL = SERVER_URL + "/riders/"

    await fetch(URL, options).then(res => handleErrors(res))
    
    document.getElementById("form-error").textContent
}

async function editRider() {
    let body = {}
    body.id = document.getElementById("rider-id").value
    body.firstName = document.getElementById("rider-fname").value
    body.lastName = document.getElementById("rider-lname").value
    body.age = document.getElementById("rider-age").value
    body.teamName = document.getElementById("rider-tname").value
    

    console.log(body)

    let options = makeOptions("PUT", body, false)
    let URL = SERVER_URL + "/riders/"

    await fetch(URL, options).then(res => handleErrors(res))
    
    document.getElementById("form-error").textContent

}

async function deleteRider() {
    let id = document.getElementById("rider-id").value

    let URL = SERVER_URL + "/riders/" + id
    let options = makeOptions("DELETE")
    let result = await fetch(URL, options).then(res => handleErrors(res))

    document.getElementById("form-error").textContent = result.message
}

function clearForm(){
    document.getElementById("rider-fname").value = null
    document.getElementById("rider-lname").value = null
    document.getElementById("rider-tname").value = null
    document.getElementById("rider-age").value = null
    document.getElementById("rider-id").value = null
    document.getElementById("form-error").textContent = "To prevent adding duplicates, the form was cleared"
}

export async function fetchRiders() {

    const URL = SERVER_URL + "/riders"

    let options = makeOptions("GET", false, false)

    const result = await fetch(URL, options).then(res => handleErrors(res))

    const tableHeaders = `<thead>
        <th>Name:</th>
        <th></th>
        <th>Age:</th>
        <th>Team:</th>
    </thead><tbody>`

    const html = tableHeaders + result.map(data =>`
    <tr id="rider-${encode(data.id)}" data-value="${encode(data.id)}">
        <td>${encode(data.firstName)}</td>
        <td>${encode(data.lastName)}</td>
        <td>${encode(data.age)}</td>
        <td>${encode(data.teamName)}</td>
    </tr>
    `).join("") + "</tbody>"

    document.getElementById("rider-table").innerHTML = html

    result.forEach(rider => {
        document.getElementById("rider-" + rider.id).onclick = loadRider        
    });
}
