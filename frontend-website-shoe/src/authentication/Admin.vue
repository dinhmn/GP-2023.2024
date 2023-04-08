<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <NavbarAdmin />
  <div class="h-full] grid grid-cols-7 bg-[#1a1e30] relative">
    <SidebarAdmin class="col-span-1" />
    <div class="col-span-6 m-3 p-5 rounded-md bg-[#171B2D] shadow-md">
      <router-view />
    </div>
    <div
      class="w-[350px] h-[400px] grid grid-rows-7 bottom-0 right-20 fixed bg-[#0c3247] z-10 text-white"
    >
      <div
        class="flex items-center h-[50px] justify-between row-span-1 px-2 border-b-2 border-gray-500 border-solid"
      >
        <h2>Admin</h2>
        <button class="translate-x-2">X</button>
      </div>
      <div class="flex flex-col h-full row-span-5 gap-3 px-2 py-2">
        <div class="flex items-center justify-start gap-3">
          <img
            src="../assets/images/default.png"
            class="w-[50px] h-[50px] object-cover rounded-full"
            alt=""
          />
          <span class="px-3 border-gray-300 border-solid border-[1px] rounded-md"> text </span>
        </div>
        <div class="flex items-center justify-end gap-3">
          <span class="px-3 border-gray-300 border-solid border-[1px] rounded-md"> text </span>
          <img
            src="../assets/images/default.png"
            class="w-[50px] h-[50px] object-cover rounded-full"
            alt=""
          />
        </div>
      </div>
      <div class="flex items-end justify-between row-span-1">
        <Input class="w-full h-[44px] rounded-none" />
        <button class="w-[80px] text-sm m-0 py-3 rounded-none">Send</button>
      </div>
    </div>
  </div>
</template>
<script setup>
import Input from '../components/common/input/Input.vue'
import NavbarAdmin from '@/components/templates/NavbarAdmin.vue'
import SidebarAdmin from '@/components/templates/SidebarAdmin.vue'
</script>
<style lang=""></style>
