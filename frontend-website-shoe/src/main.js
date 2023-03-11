import { createApp } from 'vue'
import App from './App.vue'
import router from "./routers"

import VueFeather from 'vue-feather';
import './assets/styles/reset.css'
import './assets/styles/tailwind.css'
import './assets/styles/global.css'

import {registerGlobalComponents} from "./utils/import";



const app = createApp(App);
registerGlobalComponents(app);

app.use(router)
app.component(VueFeather.name, VueFeather);
app.mount('#app');
