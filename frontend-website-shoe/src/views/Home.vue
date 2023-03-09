<template lang="">
  <base-header />
  <div class="wrapper">{{ msg }}</div>
  <base-footer />
</template>
<script>
import Header from '../components/templates/Header.vue'
import Footer from '../components/templates/Footer.vue'
export default {
  name: 'HomePage',
  components: {
    BaseHeader: Header,
    BaseFooter: Footer
  },
  setup(props, context) {
    let msg = 'body'
    return { props, context, msg }
  }
}
</script>
<style lang=""></style>
