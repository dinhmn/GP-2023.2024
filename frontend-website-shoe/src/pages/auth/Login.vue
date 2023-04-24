<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <Auth class="relative" :checkLogin="checkLogin">
    <div
      class="wrap w-[500px] h-[60%] bg-slate-300 flex flex-col items-center justify-center gap-5"
      :class="checkLogin ? '' : 'opacity-40'"
    >
      <div class="w-full text-left">
        <h1 class="text-3xl font-bold text-left">We will help you!</h1>
        <h3 class="">
          {{ recover === true ? 'Please enter your recent old password!' : 'Login now' }}!
        </h3>
      </div>
      <Button
        v-if="recover !== true"
        className="w-full bg-transparent border-2 border-x-cyan-50 block text-center m-0 hover:text-white"
        name="loginWithGoogle"
        text="Login in with Google"
      />
      <div class="relative w-full py-5 text-center">
        <h5
          v-if="recover !== true"
          class="absolute px-2 bg-slate-300 right-[50%] translate-x-[15px] translate-y-[-10px] z-10"
        >
          or
        </h5>
        <div class="w-full h-[2px] bg-gray-400 absolute top-[50%]"></div>
      </div>
      <form action="" method="post" class="flex flex-col w-full gap-5">
        <Input v-model="data.username" name="username" placeholder="Username" />
        <Input
          v-if="recover === false"
          v-model="data.password"
          type="password"
          name="password"
          placeholder="Password"
        />
        <Input
          v-if="recover === true"
          v-model="data.email"
          type="text"
          name="email"
          placeholder="Email"
        />
        <Input
          v-if="recover === true"
          v-model="data.recoverPassword"
          type="password"
          name="recoverPassword"
          placeholder="Recover password"
        />
        <div class="flex items-center justify-between">
          <div v-if="recover !== true" class="checkbox">
            <Checkbox id="remember" />
            <label for="remember" class="text-sm">Remember for 30 days</label>
          </div>
          <div :class="recover === true ? 'inline-block w-full text-right' : ''">
            <span @click="resetPassword" class="text-sm underline cursor-pointer">{{
              recover === true ? 'Log in now !' : 'Forgot Password'
            }}</span>
          </div>
        </div>
        <Button
          type="button"
          @click="onSubmit"
          className="bg-[#0c3247] hover:bg-[#135070] text-white w-full m-0"
          name="login"
          :text="recover === true ? 'Reset password' : 'Log in'"
        />
        <div class="block w-full text-center">
          <h5 class="text-xs italic">
            Don't have an account?
            <span class="underline"
              ><router-link to="/auth/signup">Sign up for free</router-link></span
            >
          </h5>
        </div>
      </form>
    </div>
    <div
      class="absolute w-[400px] h-[200px] bg-white z-99 left-[40%] p-5 text-center rounded-md shadow-md flex flex-col items-center justify-between"
      :class="checkLogin ? 'hidden' : ''"
    >
      <div><vue-feather class="w-16 h-16 text-red-600" type="x-circle"></vue-feather></div>
      <div class="">Username or password is incorrect, please check again!</div>
      <button
        type="button"
        class="px-10 text-white transition-all bg-[#0c3247] hover:bg-[#135070] hover:opacity-90"
        @click="checkLogin = true"
      >
        OK
      </button>
    </div>
  </Auth>
</template>
<script setup>
import { ref, reactive } from 'vue'
// import AuthService from '@/stores/modules/AuthService'
import Auth from './Auth.vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Checkbox from '@/components/common/input/Checkbox.vue'
import { useRouter } from 'vue-router'
import store from '@/stores/store'
const data = reactive({
  username: '',
  email: '',
  password: '',
  recoverPassword: '',
  status: ''
})
const user = reactive({
  username: '',
  password: ''
})
const checkLogin = ref(true)
const recover = ref(false)
const router = useRouter()
const resetPassword = () => {
  recover.value = !recover.value
}
function onSubmit() {
  // loading.value = true
  user.username = data.username
  user.password = data.password
  store.dispatch('auth/login', user).then(
    (currentUser) => {
      if (currentUser.response) {
        checkLogin.value = false
      } else {
        checkLogin.value = true
        if (currentUser.roles.includes('ADMIN')) {
          router.push('/admin')
        } else if (currentUser.roles.includes('CUSTOMER')) {
          router.push('/')
        }
      }
    },
    (error) => {
      checkLogin.value = false
      console.log(error)
    }
  )
}
</script>
<style lang="scss">
section {
  .image {
    img {
      background-position: center;
      background-repeat: no-repeat;
      background-size: cover;
      height: 100%;
      width: 100%;
      object-fit: cover;
    }
  }
}
</style>
