<template lang="">
  <component :is="layout" />
</template>

<script>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { PUBLIC_AUTH } from '@/constants'
import { PUBLIC_ADMIN } from '@/constants'

export default {
  name: 'App',
  setup() {
    const route = useRoute()
    return {
      layout: computed(() => (route.meta.layout || PUBLIC_AUTH || PUBLIC_ADMIN) + '-layout')
    }
  }
}
</script>
<style></style>
