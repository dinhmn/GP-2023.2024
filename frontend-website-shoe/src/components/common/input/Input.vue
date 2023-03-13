<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="flex items-center justify-center w-full">
    <label class="pr-2" :for="{ type }">{{ props.text }}</label>
    <input class="w-full text-gray-800" :type="{ type }" :name="{ name }" :id="{ name }" value="" />
  </div>
</template>
<script setup>
const props = defineProps({
  type: {
    type: String,
    default: 'text'
  },
  name: {
    type: String,
    default: 'formInput'
  },
  text: {
    type: String,
    default: 'Input'
  }
})
</script>
<style lang=""></style>
