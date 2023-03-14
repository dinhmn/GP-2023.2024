import {defineAsyncComponent} from "vue";

export function registerGlobalComponents(app) {
  app.component("user-layout", defineAsyncComponent(() => import("@/authentication/User.vue")));
  app.component("admin-layout", defineAsyncComponent(() => import("@/authentication/Admin.vue")));
  app.component("auth-layout", defineAsyncComponent(() => import("@/pages/auth/Login.vue")));
};

