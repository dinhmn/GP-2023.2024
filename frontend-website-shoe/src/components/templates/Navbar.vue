<template lang="">
  <div class="w-full bg-[#425868] flex items-center justify-center">
    <nav class="2xl:w-[1280px] flex items-center justify-between h-[70px] text-white">
      <div class="cursor-pointer logo hover:cursor-pointer">LOGO.</div>
      <div class="flex items-center justify-center gap-3">
        <router-link :to="route.url" v-for="(route, index) in routes" :key="index"
          ><span>{{ route.urlName }}</span></router-link
        >
      </div>
      <div class="flex items-center justify-center gap-2 -mr-3">
        <div class="flex items-center justify-center">
          <input-common
            type="text"
            classChild="min-w-[300px] px-2 py-2 rounded-sm"
            name="search"
            placeholder="Search"
            v-model="search"
          />
          <button
            class="right-0 px-6 py-[9px] rounded-tr-[3px] -translate-x-3 rounded-br-[3px] m-0 text-xs rounded-none"
          >
            <vue-feather class="w-5 h-5" type="search"></vue-feather>
          </button>
        </div>
        <router-link to="/payment">
          <button
            class="relative bg-transparent hover:border-[rgba(87, 61, 61, 0.6)] border-2 rounded-lg card py-[8px] px-[8px] -mr-1 flex items-center justify-center"
          >
            <span class="relative mr-1">0đ</span>
            <vue-feather class="w-5 h-5" type="shopping-bag"></vue-feather>
            <span class="py-1 px-2 rounded-[100%] absolute -top-2 -right-3 z-[999] bg-slate-700">{{
              item
            }}</span>
          </button>
        </router-link>
        <router-link :to="{ name: 'Login', params: {} }"
          ><button class="font-bold translate-x-4">Login</button></router-link
        >
      </div>
    </nav>
  </div>
</template>
<script>
import { ref } from 'vue'
import Input from '../common/input/Input.vue'
const routes = [
  {
    url: '/',
    urlName: 'Home'
  },
  {
    url: '/feature',
    urlName: 'Feature'
  },
  {
    url: '/about',
    urlName: 'About'
  },
  {
    url: '/product',
    urlName: 'Product'
  },
  {
    url: '/contact',
    urlName: 'Contact'
  }
]
const item = 0
export default {
  name: 'NavigationPage',
  components: {
    InputCommon: Input
  },
  setup(props) {
    const search = ref('')
    return { props, routes, item, search }
  }
}
</script>
<style lang="scss" scoped>
nav {
  .logo {
    font-size: 30px;
    font-weight: bold;
  }
  div {
    a {
      padding: 5px 20px;
      transition: all 0.5s;
      border-radius: 3px;
      &:hover {
        background-color: #cc334d;
      }
      &.router-link-active {
        background-color: #cc334d;
      }
    }
    &:last-child {
      a {
        &:hover {
          background-color: transparent;
          button {
            background-color: #cc334d;
          }
        }
        &.router-link-active {
          button {
            background-color: #cc334d;
          }
          background-color: transparent;
        }
      }
    }
  }
  input[type='text'] {
    height: 40px;
  }
  .card {
    span {
      line-height: 16px;
      font-size: 12spx;
    }
  }
}
</style>
