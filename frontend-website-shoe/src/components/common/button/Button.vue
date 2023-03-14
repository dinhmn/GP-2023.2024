<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="block text-center">
    <button class="px-8 py-2 rounded hover:bg-gray-800" v-bind:class="className" :id="name">
      {{ props.text }}
    </button>
  </div>
</template>
<script setup>
import { defineProps } from 'vue'
const props = defineProps({
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
