import { createApp } from 'vue'
import App from './App.vue'
import router from "./routers"
import 'leaflet/dist/leaflet.css';
import 'vue-map-ui/dist/normalize.css';
import 'vue-map-ui/dist/style.css';
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css';
import {registerGlobalComponents} from "./utils/import";
import VueFeather from 'vue-feather';
import { setupCalendar, Calendar, DatePicker } from 'v-calendar';
import 'v-calendar/style.css';
import './assets/styles/reset.css'
import './assets/styles/tailwind.css'
import './assets/styles/global.css'
import store from "./stores/store";

const app = createApp(App);
registerGlobalComponents(app);

app.use(router)
app.use(store)
app.use(setupCalendar, {})
app.component(VueFeather.name, VueFeather);
app.component("QuillEditor", QuillEditor);
// Use the components
app.component('VCalendar', Calendar)
app.component('VDatePicker', DatePicker)
app.mount('#app');
