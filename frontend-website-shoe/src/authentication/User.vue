<template lang="">
  <Navbar />
  <router-view />
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
