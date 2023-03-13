<template lang="">
  <Navbar />
  <router-view />
  <!-- <component :is="layout">
    <router-view />
  </component> -->
  <base-footer />
</template>
<script>
import Navbar from '../components/templates/Navbar.vue'
import Footer from '../components/templates/Footer.vue'
export default {
  name: 'UserAuth',
  components: {
    Navbar: Navbar,
    BaseFooter: Footer
  }
}
</script>
<style lang=""></style>
