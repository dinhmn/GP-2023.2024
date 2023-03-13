<!-- eslint-disable vue/multi-word-component-names -->
<template>
  <div class="block text-center min-w-[300px]">
    <button class="w-full px-8 py-2 rounded" :class="{ className }" :id="{ name }">
      {{ props.text }}
    </button>
  </div>
</template>
<script setup>
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
