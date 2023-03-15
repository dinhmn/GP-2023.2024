<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <Auth>
    <div
      class="wrap w-[500px] h-[60%] bg-slate-300 flex flex-col items-center justify-center gap-5"
    >
      <div class="w-full text-left">
        <h1 class="text-3xl font-bold text-left">Create new account.</h1>
        <h3 class="">Welcome back! Please enter your details.</h3>
      </div>
      <div class="relative w-full py-5 text-center">
        <div class="w-full h-[2px] bg-gray-400 absolute top-[50%]"></div>
      </div>
      <form @submit.prevent="onSubmit" method="post" class="flex flex-col w-full gap-5">
        <div class="flex items-center justify-center gap-3">
          <Input v-model="data.firstName" name="firstName" placeholder="First Name" />
          <Input v-model="data.lastName" name="lastName" placeholder="Last Name" />
        </div>
        <Input v-model="data.email" name="email" placeholder="Email/Username" />
        <Input v-model="data.password" type="password" name="password" placeholder="Password" />
        <Input
          v-model="data.confirm"
          type="password"
          name="confirm"
          placeholder="Confirm Password"
        />
        <Button
          type="submit"
          className="bg-black text-white w-full m-0"
          name="signup"
          text="Create Account"
        />
        <div class="relative w-full py-5 text-center">
          <h5
            class="absolute px-2 bg-slate-300 right-[50%] translate-x-[15px] translate-y-[-10px] z-10"
          >
            or
          </h5>
          <div class="w-full h-[2px] bg-gray-400 absolute top-[50%]"></div>
        </div>
        <Button
          className="w-full bg-transparent border-2 border-x-cyan-50 block text-center m-0 hover:text-white"
          name="loginWithGoogle"
          text="Login in with Google"
        />
        <div class="block w-full text-left">
          <h5 class="text-xs italic">
            Already have a account?
            <span class="underline"><router-link to="/auth/login">Log In</router-link></span>
          </h5>
        </div>
      </form>
    </div>
  </Auth>
</template>
<script setup>
import { reactive } from 'vue'
import Auth from './Auth.vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
// const error = ref('');
const data = reactive({
  firstName: '',
  lastName: '',
  email: '',
  password: '',
  confirm: ''
})
// const onSubmit = () => {}
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
