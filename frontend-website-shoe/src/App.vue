<!-- <script setup>
import Navbar from './components/templates/Navbar.vue'
</script> -->
<template lang="">
  <component :is="layout">
    <router-view />
  </component>
</template>

<script>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { PUBLIC_LOGIN } from '@/constants'

export default {
  name: 'App',
  setup() {
    const route = useRoute()
    console.log(route.meta.layout)
    return {
      layout: computed(() => (route.meta.layout || PUBLIC_LOGIN) + '-layout')
    }
  }
}
</script>
<style></style>
