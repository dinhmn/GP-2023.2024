import { createApp } from 'vue'
import App from './App.vue'
// import VueRouter from "vue-router"
/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import specific icons */
import { faUserSecret } from '@fortawesome/free-solid-svg-icons'

// import './assets/main.css'
import './assets/styles/reset.css'
import './assets/styles/tailwind.css'
import './assets/styles/global.css'

library.add(faUserSecret)

createApp(App)
.component('font-awesome-icon', FontAwesomeIcon)
// .use(VueRouter)
.mount('#app')
