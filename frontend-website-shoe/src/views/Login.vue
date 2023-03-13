<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <section class="flex items-center justify-center text-white">
    <div class="image bg-slate-300 w-[50%] h-[100vh]">Image</div>
    <div class="form-login w-[50%] h-[100vh] flex flex-col gap-[50px] items-center justify-center">
      <div
        class="wrap w-[60%] h-[60%] bg-slate-600 flex flex-col items-center justify-center gap-5"
      >
        <div>
          <h1>Welcome back, Olivia</h1>
          <h3>Welcome back! Please enter your details.</h3>
        </div>
        <Button
          className="bg-transparent border-2 border-x-cyan-50"
          name="loginWithGoogle"
          text="Login in with Google"
        />
        <div>
          <h5>or</h5>
          <span></span>
        </div>
        <form action="" method="post" class="flex flex-col gap-5">
          <div>
            <label for="email">Email</label>
            <input type="text" name="email" id="email" value="" />
          </div>
          <div>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" value="" />
          </div>
          <Input type="password" name="password" text="Password" />
          <div>
            <div class="checkbox">
              <input type="checkbox" name="remember" id="remember" value="" />
              <label for="remember">Remember for 30 days</label>
            </div>
            <div>
              <a href="#">Forgot Password</a>
            </div>
          </div>
          <Button className="bg-white" name="login" text="Login" />
          <div class="block w-full text-center">
            <h5 class="text-xs italic">Don't have an account? <a href="#">Sign up for free</a></h5>
          </div>
        </form>
      </div>
    </div>
  </section>
</template>
<script setup>
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'

// export default {
//   name: 'LoginPage',
//   components: { BaseInput: Input },
//   setup(props) {
//     return { props }
//   }
// }
</script>
<style lang=""></style>
