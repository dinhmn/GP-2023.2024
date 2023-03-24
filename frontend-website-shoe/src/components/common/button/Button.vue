<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <button :type="type" class="px-8 py-2 rounded" :class="className" :id="name">
    {{ props.text }}
  </button>
</template>
<script setup>
import { defineProps } from 'vue'
const props = defineProps({
  type: {
    type: String,
    default: 'button'
  },
  name: {
    type: String,
    default: 'id'
  },
  text: {
    type: String,
    default: 'Button'
  },
  className: {
    type: String,
    default: ''
  }
})
</script>
<style lang=""></style>
