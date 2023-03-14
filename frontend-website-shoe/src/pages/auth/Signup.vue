<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <AuthLayout>
    <div class="wrap w-[60%] h-[60%] bg-slate-300 flex flex-col items-center justify-center gap-5">
      <div class="w-[360px] text-left">
        <h1 class="text-3xl font-bold text-left">Signup</h1>
        <h3 class="">Signup.</h3>
      </div>
      <Button
        className="w-[360px] bg-transparent border-2 border-x-cyan-50 block text-center m-0 hover:text-white"
        name="loginWithGoogle"
        text="Login in with Google"
      />
      <div class="w-[360px] py-5 text-center relative">
        <h5
          class="absolute px-2 bg-slate-300 right-[50%] translate-x-[15px] translate-y-[-10px] z-10"
        >
          or
        </h5>
        <div class="w-full h-[2px] bg-gray-400 absolute top-[50%]"></div>
      </div>
      <form action="" method="post" class="flex flex-col gap-5 w-[360px]">
        <Input name="email" placeholder="Email/Username" />
        <Input type="password" name="password" placeholder="Password" />
        <div class="flex items-center justify-between">
          <div class="checkbox">
            <Checkbox id="remember" />
            <label for="remember" class="text-sm">Remember for 30 days</label>
          </div>
          <div>
            <a href="#" class="text-sm underline">Forgot Password</a>
          </div>
        </div>
        <Button className="bg-black text-white w-full m-0" name="login" text="Log in" />
        <div class="block w-full text-center">
          <h5 class="text-xs italic">
            Don't have an account? <a href="#" class="underline">Sign up for free</a>
          </h5>
        </div>
      </form>
    </div>
  </AuthLayout>
</template>
<script setup>
import AuthLayout from '@/layouts/AuthLayout.vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Checkbox from '@/components/common/input/Checkbox.vue'
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
