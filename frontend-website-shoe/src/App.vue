<!-- <script setup>
import Navbar from './components/templates/Navbar.vue'
</script> -->
<template lang="">
  <Navbar />
  <router-view />
  <!-- <component :is="layout">
    <router-view />
  </component> -->
  <base-footer />
</template>

<script>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { PUBLIC_LAYOUT } from '@/constants'
import Navbar from './components/templates/Navbar.vue'
import Footer from './components/templates/Footer.vue'
export default {
  name: 'App',
  components: {
    Navbar: Navbar,
    BaseFooter: Footer
  },
  setup() {
    const route = useRoute()
    return {
      layout: computed(() => (route.meta.layout || PUBLIC_LAYOUT) + '-layout')
    }
  }
}
</script>
<style></style>
