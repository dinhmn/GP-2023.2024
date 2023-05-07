<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <section class="relative flex items-center justify-center text-white font-poppins">
    <div class="image bg-slate-800 w-[50%] h-[100vh]" :class="props.checkLogin ? '' : 'opacity-40'">
      <img src="../../assets/images/login.png" alt="" />
    </div>
    <div
      class="text-black form-login bg-slate-300 w-[50%] h-[100vh] flex flex-col gap-[50px] items-center justify-center"
    >
      <slot />
    </div>
    <!-- <div
      @click="handleChangeTheme"
      class="absolute top-0 right-0 text-center -translate-x-5 translate-y-3 bg-transparent cursor-pointer hover:bg-transparent"
    >
      <vue-feather
        class="w-10 h-10 text-black -translate-x-2"
        type="toggle-left"
        :class="dark === false ? 'hidden' : ''"
      ></vue-feather>
      <vue-feather
        class="hidden w-10 h-10 text-black -translate-x-2"
        type="toggle-right"
        :class="dark === true ? 'hidden' : ''"
      ></vue-feather>
    </div> -->
    <router-link to="/">
      <button class="absolute bottom-0 right-0 -translate-x-2 -translate-y-2">
        Trang chủ
      </button></router-link
    >
  </section>
</template>
<script setup>
const props = defineProps({
  checkLogin: {
    type: Boolean,
    default: true
  }
})
// const dark = true
// const handleChangeTheme = () => (this.dark = !dark.value)
</script>
<style lang="scss"></style>
