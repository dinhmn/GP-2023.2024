<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <component :is="auth">
    <router-view />
  </component>
</template>
<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { AUTH_LOGIN } from '@/constants/index'
const route = useRoute()
const auth = computed(() => (route.meta.layout || AUTH_LOGIN) + '-auth')
</script>
<style lang=""></style>
