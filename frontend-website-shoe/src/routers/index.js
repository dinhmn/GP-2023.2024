import {createRouter, createWebHistory} from "vue-router"
import Home from "../views/Home.vue";

const routes = [
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/feature",
    name: "Feature",
    meta: {
      layout: 'default'
    },
    component: () => import("../views/Feature.vue"),
  },
  {
    path: "/about",
    name: "AboutPage",
    // meta: {
    //   layout: 'auth'
    // },
    component: () => import("../views/About.vue"),
  },
  {
    path: "/product",
    name: "Product",
    // meta: {
    //   layout: 'auth'
    // },
    component: () => import("../views/Product.vue"),
  },
  {
    path: "/contact",
    name: "Contact",
    // meta: {
    //   layout: 'auth'
    // },
    component: () => import("../views/Contact.vue"),
  },
  {
    path: "/login",
    name: "Login",
    // meta: {
    //   layout: 'auth'
    // },
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/cart",
    name: "Cart",
    // meta: {
    //   layout: 'auth'
    // },
    component: () => import("../views/Cart.vue"),
  },
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;
