<template lang="">
  <div class="w-full flex items-center justify-between h-[70px] text-white px-8 bg-[#202641]">
    <div
      class="text-3xl font-bold tracking-[5px] cursor-pointer logo hover:cursor-pointer flex items-center leading-[30px]"
    >
      <vue-feather class="mt-1 w-7 h-7" type="menu"></vue-feather>
      <router-link to="/admin"><span class="ml-3 text-[#42bbff]">DASHBOARD.</span></router-link>
    </div>
    <div class="flex items-center justify-center gap-5 ml-2">
      <div class="relative h-full w-[50px] text-center cursor-pointer announce">
        <vue-feather
          class="relative w-8 h-8 mt-1 message-circle"
          type="message-circle"
        ></vue-feather>
        <span class="py-1 px-2 text-xs rounded-[100%] absolute -top-0 -right-0 z-[999] bg-brown">{{
          dataMessage.length
        }}</span>
        <div class="bg-white w-[400px] z-99 -left-[350px] absolute rounded-md text-black block">
          <div class="flex flex-col items-start justify-center">
            <span
              class="flex flex-col justify-between"
              v-for="item in dataMessage"
              :key="item.chatId"
              @click="$emit('userChat', item)"
            >
              <span class="text-lg font-bold">{{ item.messageFrom }}</span>
              <span class="text-sm">{{ item.messageText }}</span>
            </span>
          </div>
        </div>
      </div>
      <div class="relative h-full w-[50px] text-center mr-6 cursor-pointer announce">
        <vue-feather class="relative w-8 h-8 mt-1 message-circle" type="bell"></vue-feather>
        <span class="py-1 px-2 text-xs rounded-[100%] absolute -top-0 -right-0 z-[999] bg-brown">{{
          dataMessage.length
        }}</span>
        <div class="bg-white w-[400px] z-99 -left-[350px] absolute rounded-md text-black">
          <div class="flex flex-col items-start justify-center">
            <span class="">Thông báo 1</span>
            <span class="">Thông báo 1</span>
          </div>
        </div>
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
        <div class="logout-child absolute w-[150px] flex flex-col top-[55px] right-0 z-50">
          <div
            class="flex items-center justify-between px-4 py-2 rounded-tl-sm rounded-tr-sm bg-slate-700 hover:bg-slate-500"
            @click="logout"
          >
            <span>Logout</span>
            <vue-feather class="relative w-5 h-5 mt-1 message-circle" type="log-out"></vue-feather>
          </div>
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
<script setup>
import { onMounted, ref } from 'vue'
import store from '@/stores/store'
import ChatService from '@/stores/modules/ChatService'
import { useRouter } from 'vue-router'
const router = useRouter()
const dataMessage = ref([])
onMounted(async () => {
  await getAllUserChat(dataMessage)
})
const logout = () => {
  router.push('/')
  store.dispatch('auth/logout')
}
async function getAllUserChat(data) {
  const res = await ChatService.getAllMessage()
  data.value = res.data
}
</script>
<style lang="scss">
.announce {
  &:hover {
    & > div {
      display: block;
    }
  }
  & > div {
    z-index: 1;
    top: 50px;
    display: none;
    transition: all 0.3s ease-in-out;
    div {
      & > span {
        text-align: left;
        width: 100%;
        padding: 10px;
        border-bottom: 1px solid gray;
        &.announce-check {
          background-color: rgb(201, 201, 201);
        }
      }
    }
    &::after {
      position: absolute;
      content: '';
      right: 15px;
      top: -10px;
      width: 0;
      height: 0;
      border-left: 10px solid transparent;
      border-right: 10px solid transparent;
      border-bottom: 10px solid white;

      clear: both;
    }
  }
}
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
