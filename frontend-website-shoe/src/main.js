import { createApp } from 'vue'
import App from './App.vue'
import router from "./routers"

import VueFeather from 'vue-feather';
import './assets/styles/reset.css'
import './assets/styles/tailwind.css'
import './assets/styles/global.css'
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import {registerGlobalComponents} from "./utils/import";
import 'leaflet/dist/leaflet.css';
import 'vue-map-ui/dist/normalize.css';
import 'vue-map-ui/dist/style.css';


const app = createApp(App);
registerGlobalComponents(app);

app.use(router)
app.component(VueFeather.name, VueFeather);
app.component("QuillEditor", QuillEditor);
app.mount('#app');
