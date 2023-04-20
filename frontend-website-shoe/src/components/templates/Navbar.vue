<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div
    class="w-full bg-[#425868] flex items-center justify-center mobile:text-[12px] mobile:w-full mobile:gap-0"
  >
    <nav
      class="desktop::w-[1280px] flex items-center justify-between h-[70px] text-white mobile:gap-5 mobile:h-[40px] mobile:w-full mobile:justify-start"
    >
      <div
        class="cursor-pointer hover:cursor-pointer mobile:text-base desktop:text-[30px] font-bold mobile:ml-2"
      >
        LOGO.
      </div>
      <div
        class="flex items-center justify-center gap-3 mobile:flex-col md:bg-red-500 mobile:hidden"
      >
        <router-link :to="route.url" v-for="(route, index) in routes" :key="index"
          ><span>{{ route.urlName }}</span></router-link
        >
      </div>
      <div
        class="flex items-center justify-center gap-2 -mr-3 mobile:gap-0 mobile:mr-0 mobile:w-[200px] mobile:justify-between"
      >
        <!-- <div
          class="flex items-center justify-center gap-3 mobile:flex-col md:bg-red-500 mobile:block desktop:hidden"
        >
          <router-link :to="route.url" v-for="(route, index) in routes" :key="index"
            ><span>{{ route.urlName }}</span></router-link
          >
        </div> -->
        <div class="flex items-center justify-center">
          <Input
            type="text"
            classChild="min-w-[300px] px-2 py-2 rounded-sm desktop:h-[40px] mobile:p-1 mobile:min-w-[150px] "
            name="search"
            placeholder="Search"
            v-model="search"
          />
          <button
            class="right-0 mobile:px-2 mobile:py-[6px] mobile:bg-none px-6 py-[9px] bg-[#0c3247] hover:bg-[#135070] rounded-tr-[3px] -translate-x-3 rounded-br-[3px] m-0 text-xs rounded-none"
          >
            <vue-feather class="w-5 h-5 mobile:w-3 mobile:h-3" type="search"></vue-feather>
          </button>
        </div>
        <router-link to="/payment">
          <button
            class="w-full relative bg-transparent mobile:border-none mobile:border-[1px] mobile:rounded-[4px] border-2 rounded-lg card mobile:m-0 mobile:py-1 py-[8px] px-[8px] -mr-1 flex items-center justify-center"
          >
            <span class="relative mr-1 mobile:text-[12px]">0đ</span>
            <vue-feather class="w-5 h-5 mobile:w-3 mobile:h-3" type="shopping-bag"></vue-feather>
            <span
              class="py-1 px-2 mobile:text-[12px] mobile:py-[1px] mobile:px-[4px] mobile:-top-1 mobile:-right-2 rounded-[100%] absolute -top-2 -right-3 z-[999] bg-slate-700"
              id="cart"
              >{{ item }}</span
            >
          </button>
        </router-link>
        <router-link :to="{ name: 'Login', params: {} }"
          ><Button
            text="Login"
            class="button font-bold mobile:mr-2 w-full hover:bg-[#0c3247] text-[#17b1ea] mobile:py-[3px] mobile:px-[4px]"
        /></router-link>
      </div>
    </nav>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import Input from '../common/input/Input.vue'
import Button from '../common/button/Button.vue'
const routes = [
  {
    url: '/',
    urlName: 'Home'
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
const item = ref(0)
if (window.localStorage.getItem('order') !== null) {
  item.value = JSON.parse(window.localStorage.getItem('order')).length
} else {
  item.value = 0
}
const search = ref('')
</script>
<style lang="scss" scoped>
nav {
  div {
    a {
      padding: 5px 20px;
      transition: all 0.2s;
      border-radius: 3px;
      &:hover {
        color: #17b1ea;
      }
      &.router-link-active {
        background: #0c3247;
        color: #17b1ea;
      }
    }
    &:last-child {
      a {
        padding: 0px;
        &:hover {
          background-color: transparent;
          button {
            background-color: #0e3b53;
          }
        }
        &.router-link-active {
          button {
            background-color: #0c3247;
          }
          background-color: transparent;
        }
      }
    }
  }
  .card {
    span {
      line-height: 16px;
      font-size: 12spx;
    }
  }
}
.button {
  background-color: #0c3247;
}
</style>
