<template lang="">
  <section>
    <div class="h-[100vh]">This is home page.</div>
    <vue-feather type="star"></vue-feather>
  </section>
</template>
<script>
import Navbar from '../components/templates/Navbar.vue'
export default {
  name: 'HomePage',
  component: {
    BaseHeader: Navbar
  },
  setup(props, context) {
    let msg = 'body'
    return { props, context, msg }
  }
}
</script>
<style lang="scss"></style>
