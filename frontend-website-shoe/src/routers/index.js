import {createRouter, createWebHistory} from "vue-router"
import Home from "../views/Home.vue";

const routes = [
  {
    path: "/",
    name: "User",
    meta: {
      layout: 'user'
    },
    component: () => import("../authentication/User.vue"),
  },
  {
    path: "/admin",
    name: "Admin",
    meta: {
      layout: 'admin'
    },
    component: () => import("../authentication/Admin.vue"),
  },
  {
    path: "/home",
    name: "Home",
    meta: {
      layout: 'user'
    },
    component: Home,
  },
  {
    path: "/feature",
    name: "Feature",
    meta: {
      layout: 'user'
    },
    component: () => import("../views/Feature.vue"),
  },
  {
    path: "/about",
    name: "AboutPage",
    meta: {
      layout: 'user'
    },
    component: () => import("../views/About.vue"),
  },
  {
    path: "/product",
    name: "Product",
    meta: {
      layout: 'user'
    },
    component: () => import("../views/Product.vue"),
  },
  {
    path: "/contact",
    name: "Contact",
    meta: {
      layout: 'user'
    },
    component: () => import("../views/Contact.vue"),
  },
  {
    path: "/login",
    name: "Login",
    meta: {
      layout: 'login'
    },
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/cart",
    name: "Cart",
    meta: {
      layout: 'user'
    },
    component: () => import("../views/Cart.vue"),
  },
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;
