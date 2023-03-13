import {createRouter, createWebHistory} from "vue-router"
// import Home from "../views/Home.vue";

const routes = [
  // {
  //   path: "/",
  //   name: "Home",
  //   component: Home,
  // },
  {
    path: "/",
    name: "Home",
    layout: "default",
    component: () => import("../views/Home.vue"),
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;
