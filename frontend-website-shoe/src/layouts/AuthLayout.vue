<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <section class="flex items-center justify-center text-white font-poppins">
    <div class="image bg-slate-800 w-[50%] h-[100vh]">
      <img src="../../src/assets/images/login.png" alt="" />
    </div>
    <div
      class="text-black form-login bg-slate-300 w-[50%] h-[100vh] flex flex-col gap-[50px] items-center justify-center"
    >
      <slot></slot>
    </div>
  </section>
</template>
<script setup></script>
<style lang="scss"></style>
