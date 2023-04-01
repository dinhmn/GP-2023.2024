<template lang="">
  <component :is="layout">
    <router-view></router-view>
  </component>
</template>

<script>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { PUBLIC_AUTH, PUBLIC_ADMIN, NOT_FOUND } from '@/constants'

export default {
  name: 'App',
  setup() {
    const route = useRoute()
    return {
      layout: computed(
        () => (route.meta.layout || PUBLIC_AUTH || PUBLIC_ADMIN || NOT_FOUND) + '-layout'
      )
    }
  }
}
</script>
<style></style>
