import { getScores } from './js-for-pages/4shirts.js'
import { fetchRiders, teamsFilterHandler } from './js-for-pages/listRiders.js'
import { renderTemplate, setActive, showPage } from './utils.js'

function renderMenuItems(evt) {
  const element = evt.target
  setActive(element)
  const id = element.id
  renderTemplate(id) //This setups the HTML for the page
  switch (id) {
    //Here you can execute JavaScript for the selected page
    case 'page-home': {
      break
    }
    case 'page-list-riders': {
      teamsFilterHandler()
      break
    }
    case 'page-4shirts':{
      getScores()
      break
    }
  }
}

document.getElementById('menu').onclick = renderMenuItems
showPage('page-home') //Set the default page to render
