import {createRouter, createWebHistory} from "vue-router"

const routes = [
  {
    path: "/home",
    name: "User",
    meta: {
      layout: 'user'
    },
    component: () => import("../pages/user/Home.vue"),
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
    path: "/feature",
    name: "Feature",
    meta: {
      layout: 'user'
    },
    component: () => import("../pages/user/Feature.vue"),
  },
  {
    path: "/about",
    name: "AboutPage",
    meta: {
      layout: 'user'
    },
    component: () => import("../pages/user/About.vue"),
  },
  {
    path: "/product",
    name: "Product",
    meta: {
      layout: 'user'
    },
    component: () => import("../pages/user/Product.vue"),
  },
  {
    path: "/contact",
    name: "Contact",
    meta: {
      layout: 'user'
    },
    component: () => import("../pages/user/Contact.vue"),
  },
  {
    path: "/auth/login",
    name: "Login",
    meta: {
      layout: 'auth'
    },
    component: () => import("../pages/auth/Login.vue"),
  },
  {
    path: "/auth/signup",
    name: "Signup",
    meta: {
      layout: 'auth'
    },
    component: () => import("../pages/auth/Signup.vue"),
  },
  {
    path: "/cart",
    name: "Cart",
    meta: {
      layout: 'user'
    },
    component: () => import("../pages/user/Cart.vue"),
  },
  
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router;
