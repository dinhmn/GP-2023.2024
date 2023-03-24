<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <NavbarAdmin />
  <div class="grid grid-cols-7 bg-[#1a1e30]">
    <SidebarAdmin class="col-span-1" />
    <div class="col-span-6 m-3 p-5 rounded-md bg-[#171B2D] shadow-md">
      <router-view />
    </div>
  </div>
</template>
<script setup>
import NavbarAdmin from '@/components/templates/NavbarAdmin.vue'
import SidebarAdmin from '@/components/templates/SidebarAdmin.vue'
</script>
<style lang=""></style>
