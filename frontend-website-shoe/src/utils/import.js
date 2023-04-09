import {defineAsyncComponent} from "vue";

export function registerGlobalComponents(app) {
  app.component("user-layout", defineAsyncComponent(() => import("@/authentication/User.vue")));
  app.component("admin-layout", defineAsyncComponent(() => import("@/authentication/Admin.vue")));
  app.component("login-auth", defineAsyncComponent(() => import("@/pages/auth/Login.vue")));
  app.component("signup-auth", defineAsyncComponent(() => import("@/pages/auth/Signup.vue")));
  app.component("404-layout", defineAsyncComponent(() => import("@/pages/auth/404-page.vue")));
};

