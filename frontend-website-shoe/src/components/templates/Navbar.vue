<template lang="">
  <nav class="flex items-center justify-around bg-[#425868] h-[70px] text-white">
    <div class="cursor-pointer logo hover:cursor-pointer">LOGO</div>
    <ul class="flex items-center justify-center gap-3">
      <li v-for="(route, index) in routes" :key="index">
        <router-link :to="route.url">{{ route.urlName }}</router-link>
      </li>
    </ul>
    <div class="flex items-center justify-center gap-2">
      <div class="relative flex items-center justify-center">
        <input
          type="text"
          class="text-black min-w-[300px]"
          placeholder="Search"
          name="search"
          value=""
          :model="search"
        />
        <button class="right-0 px-5 m-0 text-xs rounded-none">
          <vue-feather class="w-5 h-5" type="search"></vue-feather>
        </button>
      </div>
      <router-link to="/cart">
        <button
          class="relative bg-transparent hover:border-[rgba(87, 61, 61, 0.6)] border-2 rounded-lg card py-[8px] px-[8px] -mr-1 flex items-center justify-center"
        >
          <vue-feather class="w-5 h-5" type="shopping-bag"></vue-feather>
          <span class="py-1 px-2 rounded-[100%] absolute -top-2 -right-3 z-[999] bg-slate-700">{{
            item
          }}</span>
        </button>
      </router-link>
      <router-link :to="{ name: 'Login', params: {} }"
        ><button class="font-bold">Login</button></router-link
      >
    </div>
  </nav>
</template>
<script>
const routes = [
  {
    url: '/home',
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
  setup(props) {
    return { props, routes, item }
  }
}
</script>
<style lang="scss" scoped>
nav {
  .logo {
    font-size: 30px;
    font-weight: bold;
  }
  ul {
    li {
      transition: all 0.5s;
      padding: 5px 20px;
      &:hover {
        background-color: #573d3d;
        border-radius: 3px;
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
