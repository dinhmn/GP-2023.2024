<template lang="">
  <div class="w-full flex items-center justify-between h-[70px] text-white px-8 bg-[#171B2D]">
    <div
      class="text-3xl font-bold tracking-[5px] cursor-pointer logo hover:cursor-pointer flex items-center leading-[30px]"
    >
      <vue-feather class="mt-1 w-7 h-7" type="menu"></vue-feather>
      <span class="ml-3 text-[#42bbff]">DASHBOARD.</span>
    </div>
    <div class="flex items-center justify-center gap-5 ml-2">
      <div class="relative h-full w-[50px] text-center">
        <vue-feather
          class="relative w-8 h-8 mt-1 message-circle"
          type="message-circle"
        ></vue-feather>
        <span class="py-1 px-2 text-xs rounded-[100%] absolute -top-0 -right-0 z-[999] bg-brown"
          >0</span
        >
      </div>
      <div class="relative h-full w-[50px] text-center mr-6">
        <vue-feather class="relative w-8 h-8 mt-1 message-circle" type="bell"></vue-feather>
        <span class="py-1 px-2 text-xs rounded-[100%] absolute -top-0 -right-0 z-[999] bg-brown"
          >0</span
        >
      </div>
      <div
        class="relative flex items-center justify-center gap-3 cursor-pointer logout"
        @click="onLogout"
      >
        <img
          src="../../assets/images/default.png"
          class="w-[40px] h-[40px] object-cover rounded-full"
          alt=""
        />
        <span class="text-base"> Admin </span>
        <vue-feather class="w-5 h-5 mt-1 -ml-2" type="chevron-down"></vue-feather>
        <div class="logout-child absolute w-[150px] flex flex-col top-[55px] right-0">
          <router-link :to="{ name: 'Login', params: {} }">
            <div
              class="flex items-center justify-between px-4 py-2 rounded-tl-sm rounded-tr-sm bg-slate-700 hover:bg-slate-500"
            >
              <span>Logout</span>
              <vue-feather
                class="relative w-5 h-5 mt-1 message-circle"
                type="log-out"
              ></vue-feather>
            </div>
          </router-link>
          <router-link :to="{ name: 'User', params: {} }">
            <div
              class="flex items-center justify-between px-4 py-2 rounded-tl-sm rounded-tr-sm bg-slate-700 hover:bg-slate-500"
            >
              <span>Home</span>
              <vue-feather class="relative w-5 h-5 message-circle" type="home"></vue-feather>
            </div>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup></script>
<style lang="scss">
.logout {
  position: relative;
  .logout-child {
    display: none;
  }
  &:hover {
    .logout-child {
      display: block;
    }
  }
  &::after {
    position: absolute;
    content: '';
    height: 20px;
    width: 150px;
    bottom: -15px;
    &:hover {
      .logout-child {
        display: block;
      }
    }
  }
}
</style>
