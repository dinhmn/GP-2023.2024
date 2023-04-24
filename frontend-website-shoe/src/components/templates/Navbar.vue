<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="w-full bg-[#abb8c3] flex items-center justify-center fixed -mt-20 z-20">
    <nav class="2xl:w-[1280px] flex items-center justify-between h-[70px] text-cyan-900">
      <div class="cursor-pointer logo hover:cursor-pointer">LOGO.</div>
      <div class="flex items-center justify-center gap-3">
        <router-link :to="route.url" v-for="(route, index) in routes" :key="index"
          ><span class="font-bold">{{ route.urlName }}</span></router-link
        >
      </div>
      <div class="relative flex items-center justify-center gap-2 -mr-3">
        <div class="flex items-center justify-center">
          <Input
            type="text"
            classChild="min-w-[300px] px-2 py-2 rounded-sm"
            name="search"
            placeholder="Search"
            v-model="api.searchValue"
            @keyup="onSearchValue($event)"
          />
          <button
            class="right-0 px-6 py-[9px] bg-[#0c3247] hover:bg-[#135070] rounded-tr-[3px] -translate-x-3 rounded-br-[3px] m-0 text-xs rounded-none"
          >
            <vue-feather class="w-5 h-5 text-cyanBlue" type="search"></vue-feather>
          </button>
        </div>
        <ul
          class="absolute top-[55px] left-0 flex-col items-center justify-center w-[55%] text-black bg-white"
          v-if="api.size > 0"
          :class="api.searchValue === '' ? 'hidden' : ''"
        >
          <li
            class="border-b-[1px] border-[#0c3247] border-solid w-full p-1 hover:bg-slate-200 transition-all"
            v-for="item in api.data"
            :key="item.productId"
            @click="redirectPage"
          >
            <router-link
              :to="{
                name: 'DetailProduct',
                params: { categoryId: item.categoryId, productId: item.productId }
              }"
            >
              {{ item.productName }}
            </router-link>
          </li>
        </ul>
        <router-link to="/payment">
          <button
            class="w-full relative bg-transparent hover:border-[rgba(87, 61, 61, 0.6)] border-2 rounded-lg card py-[8px] px-[8px] -mr-1 flex items-center justify-center"
          >
            <span class="relative mr-1">0đ</span>
            <vue-feather class="w-5 h-5" type="shopping-bag"></vue-feather>
            <span
              class="py-1 px-2 rounded-[100%] absolute -top-2 -right-3 z-[999] bg-slate-700 text-cyanBlue"
              id="cart"
              >{{ item }}</span
            >
          </button>
        </router-link>
        <div v-if="currentUser !== null" class="mr-6 login">
          <Button
            @click="logout"
            text="Logout"
            class="button font-bold w-full hover:bg-[#0c3247] text-[#17b1ea] hover:text-white"
          />
        </div>
        <div v-if="currentUser === null" class="mr-6 login">
          <router-link :to="{ name: 'Login', params: {} }"
            ><Button
              text="Login"
              class="px-3 button font-bold w-full hover:bg-[#0c3247] text-[#17b1ea]"
          /></router-link>
        </div>
      </div>
    </nav>
  </div>
</template>
<script setup>
import { ref, reactive, computed } from 'vue'
import Input from '../common/input/Input.vue'
import Button from '../common/button/Button.vue'
import ProductService from '@/stores/modules/ProductService'
import store from '@/stores/store'
import { useRouter } from 'vue-router'
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
const api = reactive({
  data: [],
  size: 0,
  searchValue: ''
})
const currentUser = computed(() => store.state.auth.user)
const logout = () => {
  store.dispatch('auth/logout')
  useRouter().push('/')
}

const item = ref(0)
if (window.localStorage.getItem('order') !== null) {
  item.value = JSON.parse(window.localStorage.getItem('order')).length
} else {
  item.value = 0
}
async function onSearchValue(event) {
  setTimeout(async () => {
    await getAllData(api, event.target.value)
    api.size = api.data.length
  }, 500)
}
async function getAllData(api, value) {
  try {
    const res = await ProductService.getAllSearch('/search', value)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }
    api.data = result.data
    console.log(api.data)
  } catch (error) {
    console.log(error)
  }
}
function redirectPage() {
  api.searchValue = ''
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
      padding: 8px 20px;
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
        &:hover {
          background-color: transparent;
          button {
            background-color: #0e3b53;
            color: white;
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
.button {
  background-color: #0c3247;
}
.login {
  a {
    padding: 0px;
  }
}
</style>
