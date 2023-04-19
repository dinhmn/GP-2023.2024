<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="relative">
    <Navbar />
    <router-view />
    <Footer />
    <div
      v-if="socket.connected == false"
      @click="connect"
      class="fixed flex items-center justify-end cursor-pointer right-2 bottom-3"
    >
      <span class="px-2 py-1 mr-2 text-xs bg-white rounded-[4px] w-fit">Liên hệ để hỗ trợ!</span>
      <img
        src="../assets/images/default.png"
        class="w-[40px] h-[40px] object-cover rounded-full"
        alt=""
      />
    </div>
    <form
      v-if="socket.connected == true"
      class="w-[350px] h-[400px] grid grid-rows-7 bottom-0 right-20 fixed bg-gray-700 z-10 text-white"
      method="post"
    >
      <div
        class="flex items-center h-[50px] bg-gray-400 text-black justify-between row-span-1 px-2 border-b-2 border-gray-500 border-solid"
      >
        <div class="flex items-center gap-2">
          <img
            src="../assets/images/default.png"
            class="w-[30px] h-[30px] object-cover rounded-full"
            alt=""
          />
          <h2>Hỗ trợ khách hàng</h2>
        </div>
        <button
          @click="disconnect"
          class="text-white font-bold max-h-[36px] px-3 py-1 translate-x-4"
        >
          X
        </button>
      </div>
      <div class="flex flex-col min-h-[300px] row-span-5 gap-3 px-2 overflow-y-scroll">
        <div v-if="message.receivedMessagesDatabase.length != 0">
          <div
            v-for="(item, index) in message.receivedMessagesDatabase"
            :key="index"
            class="flex flex-col gap-3 px-2 py-1"
          >
            <div v-if="item.messageFrom == 'Admin'" class="flex items-center justify-start gap-3">
              <img
                src="../assets/images/default.png"
                class="w-[40px] h-[40px] object-cover rounded-full"
                alt=""
              />
              <span
                class="px-3 border-gray-300 border-solid border-[1px] rounded-md max-w-[220px] gap-3 break-words"
              >
                {{ item.messageText }}
              </span>
              <strong class="text-[10px]">
                {{ item.messageTime }}
              </strong>
            </div>
            <div v-if="item.messageFrom != 'Admin'" class="flex items-center justify-end gap-3">
              <strong class="text-[10px]">
                {{ item.messageTime }}
              </strong>
              <span
                class="px-3 border-gray-300 border-solid border-[1px] rounded-md max-w-[220px] gap-3 break-words"
              >
                {{ item.messageText }}
              </span>
              <img
                src="../assets/images/default.png"
                class="w-[40px] h-[40px] object-cover rounded-full"
                alt=""
              />
            </div>
          </div>
        </div>
        <div
          v-for="(response, index) in message.receivedMessages"
          :key="index + message.receivedMessagesDatabase.length"
          class="flex flex-col gap-3 px-2"
        >
          <div v-if="response.messageFrom == 'Admin'" class="flex items-center justify-start gap-3">
            <img
              src="../assets/images/default.png"
              class="w-[40px] h-[40px] object-cover rounded-full"
              alt=""
            />
            <span
              class="px-3 border-gray-300 border-solid border-[1px] rounded-md max-w-[220px] gap-3 break-words"
            >
              {{ response.messageText }}
            </span>
            <strong class="text-[10px]">
              {{ response.messageTime }}
            </strong>
          </div>
          <div v-if="response.messageFrom != 'Admin'" class="flex items-center justify-end gap-3">
            <strong class="text-[10px]">
              {{ response.messageTime }}
            </strong>
            <span
              class="px-3 border-gray-300 border-solid border-[1px] rounded-md max-w-[220px] gap-3 break-words"
            >
              {{ response.messageText }}
            </span>
            <img
              src="../assets/images/default.png"
              class="w-[40px] h-[40px] object-cover rounded-full"
              alt=""
            />
          </div>
        </div>
      </div>
      <div class="flex items-end justify-between row-span-1">
        <Input
          v-model="message.sendMessage.messageText"
          class="w-full h-[44px] rounded-none"
        ></Input>
        <button
          @click.prevent="send"
          id="send"
          class="text-white font-bold w-[80px] text-sm m-0 py-3 rounded-none"
        >
          Send
        </button>
      </div>
    </form>
  </div>
</template>
<script setup>
import { reactive, onMounted } from 'vue'
import Input from '../components/common/input/Input.vue'
import Navbar from '../components/templates/Navbar.vue'
import Footer from '../components/templates/Footer.vue'
import SockJS from 'sockjs-client'
import Stomp from 'webstomp-client'
import { globalCookiesConfig } from 'vue3-cookies'
import ChatService from '@/stores/modules/ChatService'

const login = reactive({
  username: null,
  password: null
})
const message = reactive({
  receivedMessagesDatabase: [],
  receivedMessages: [],
  sendMessage: { chatId: 1, messageText: '', messageFrom: '1', messageTo: '' },
  messageRequest: { messageFrom: '', messageTo: '' }
})
const socket = reactive({
  socket: '',
  stompClient: '',
  connected: false
})
globalCookiesConfig({
  expireTimes: '30d',
  path: '/',
  domain: '',
  secure: true,
  sameSite: 'None'
})
onMounted(async () => {
  // await connect()
})

const send = () => {
  if (socket.stompClient && socket.stompClient.connected) {
    const sendMessage = message.sendMessage
    if (login.username == null) {
      message.sendMessage.messageTo = 'Admin'
    }
    socket.stompClient.send('/app/chat', JSON.stringify(sendMessage), {})
  }
  message.sendMessage.messageText = ''
}

const connect = async () => {
  message.messageRequest.messageFrom = '1'
  message.messageRequest.messageTo = 'Admin'
  await fetchAllMessage(message.messageRequest.messageFrom, message.messageRequest.messageTo)
  socket.socket = new SockJS('http://localhost:8088/chat')
  socket.stompClient = Stomp.over(socket.socket)
  socket.stompClient.connect(
    {},
    (frame) => {
      socket.connected = true
      console.log(frame)
      socket.stompClient.subscribe('/topic/messages', (tick) => {
        message.receivedMessages.push(JSON.parse(tick.body))
      })
      console.log(message.receivedMessages)
    },
    (error) => {
      console.log(error)
      socket.connected = false
    }
  )
}

async function fetchAllMessage(messageFrom, messageTo) {
  const res = await ChatService.getAll(messageFrom, messageTo)
  message.receivedMessagesDatabase = res.data
}

const disconnect = () => {
  if (socket.stompClient) {
    socket.stompClient.disconnect()
  }
  socket.connected = false
}
// const tickleConnection = () => {
//   socket.connected ? disconnect() : connect()
// }
</script>
<style lang="css">
.message-left {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.message-right {
  display: flex;
  justify-content: flex-end;
  align-items: center;
}
</style>
