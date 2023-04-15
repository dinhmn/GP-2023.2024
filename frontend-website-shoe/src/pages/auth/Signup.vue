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
          <input-component
            @change="onChangeValue($event, 'firstName')"
            v-model="data.firstName"
            name="firstName"
            placeholder="First Name"
            :classChild="
              v$.firstName.$error && error.firstNameError == true
                ? 'border-[2px] border-red-600 border-solid'
                : 'border-transparent'
            "
          />
          <input-component
            @change="onChangeValue($event, 'lastName')"
            v-model="data.lastName"
            name="lastName"
            placeholder="Last Name"
            :classChild="
              v$.lastName.$error && error.lastNameError == true
                ? 'border-[2px] border-red-600 border-solid'
                : 'border-transparent'
            "
          />
        </div>
        <input-component
          @change="onChangeValue($event, 'username')"
          v-model="data.username"
          name="username"
          placeholder="Username"
          :classChild="
            v$.username.$error && error.usernameError == true
              ? 'border-[2px] border-red-600 border-solid'
              : 'border-transparent'
          "
        />
        <input-component
          @change="onChangeValue($event, 'email')"
          v-model="data.email"
          type="email"
          name="email"
          placeholder="Email"
          :classChild="
            v$.email.$error && error.emailError == true
              ? 'border-[2px] border-red-600 border-solid'
              : 'border-transparent'
          "
        />
        <input-component
          v-model="data.password"
          @change="onChangeValue($event, 'password')"
          type="password"
          name="password"
          placeholder="Password"
          :classChild="
            v$.password.$error && error.passwordError == true
              ? 'border-[2px] border-red-600 border-solid'
              : 'border-transparent'
          "
        />
        <input-component
          v-model="data.confirm"
          @change="onChangeValue($event, 'confirm')"
          type="password"
          name="confirm"
          placeholder="Confirm Password"
          :classChild="
            v$.confirm.$error && error.confirmError == true
              ? 'border-[2px] border-red-600 border-solid'
              : 'border-transparent'
          "
        />
        <button-component
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
        <button-component
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
<script>
import { ref, reactive, computed } from 'vue'
import Auth from './Auth.vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import AuthService from '@/stores/modules/AuthService'
import useValidate from '@vuelidate/core'
import { required, email, minLength } from '@vuelidate/validators'
export default {
  name: 'SignupPage',
  components: {
    ButtonComponent: Button,
    InputComponent: Input,
    Auth: Auth
  },
  setup(props) {
    const data = reactive({
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      confirm: '',
      username: ''
    })
    const error = reactive({
      firstNameError: false,
      lastNameError: false,
      emailError: false,
      passwordError: false,
      confirmError: false,
      usernameError: false
    })
    const rules = computed(() => {
      return {
        firstName: { required },
        lastName: { required },
        email: { required, email },
        password: { required, minLength: minLength(8) },
        confirm: { required, minLength: minLength(8) },
        username: { required }
      }
    })
    const success = ref(false)
    const v$ = useValidate(rules, data)
    return { props, data, error, success, v$ }
  },
  methods: {
    onSubmit() {
      console.log(this.data)
      this.v$.$validate()
      if (!this.v$.$error) {
        // if ANY fail validation
        this.success = true
        console.log(this.state)
        AuthService.register(this.data)
      } else {
        alert('Form failed validation')
      }
    },
    onChangeValue(event, type) {
      this.v$.$validate()
      if (type == 'email') {
        this.error.emailError = true
      } else {
        this.error.emailError = false
      }
      if (type == 'username') {
        this.error.usernameError = true
      } else {
        this.error.usernameError = false
      }
      if (type == 'firstName') {
        this.error.firstNameError = true
      } else {
        this.error.firstNameError = false
      }
      if (type == 'lastName') {
        this.error.lastNameError = true
      } else {
        this.error.lastNameError = false
      }
      if (type == 'password') {
        this.error.passwordError = true
      } else {
        this.error.passwordError = false
      }
      if (type == 'confirm') {
        if (this.data.confirm === this.data.password) {
          this.error.confirmError = true
        } else {
          this.error.confirmError = false
        }
      } else {
        this.error.confirmError = false
      }
    }
  }
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
