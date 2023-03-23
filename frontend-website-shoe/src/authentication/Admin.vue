<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <NavbarAdmin />
  <div class="grid grid-cols-7 gap-1 bg-slate-400">
    <SidebarAdmin class="col-span-1" />
    <div class="col-span-6 p-5 m-3 rounded-md bg-slate-600">
      <router-view />
    </div>
  </div>
</template>
<script setup>
import NavbarAdmin from '@/components/templates/NavbarAdmin.vue'
import SidebarAdmin from '@/components/templates/SidebarAdmin.vue'
</script>
<style lang=""></style>
