<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <NavbarAdmin />
  <div class="grid grid-cols-6">
    <SidebarAdmin class="col-span-1" />
    <div class="col-span-5 p-5 m-3 ml-8 rounded-md bg-slate-600">
      <Category />
    </div>
  </div>
</template>
<script setup>
import NavbarAdmin from '@/components/templates/NavbarAdmin.vue'
import SidebarAdmin from '@/components/templates/SidebarAdmin.vue'
import Category from '@/pages/admin/category/Category.vue'
</script>
<style lang=""></style>
