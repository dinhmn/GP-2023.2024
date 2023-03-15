<template lang="">
  <section>
    <div class="h-[100vh]">This is home page.</div>
    <vue-feather type="star"></vue-feather>
  </section>
</template>
<script>
export default {
  name: 'HomePage',
  setup(props, context) {
    let msg = 'body'
    return { props, context, msg }
  }
}
</script>
<style lang="scss"></style>
