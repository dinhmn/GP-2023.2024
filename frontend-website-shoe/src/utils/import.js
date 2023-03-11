import {defineAsyncComponent} from "vue";

export function registerGlobalComponents(app) {
  app.component("auth-layout", defineAsyncComponent(() => import("@/views/About.vue")));
  app.component("default-layout", defineAsyncComponent(() => import("@/views/Home.vue")));
};

