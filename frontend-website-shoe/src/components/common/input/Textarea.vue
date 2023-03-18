<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <textarea
    rows="2"
    class="w-full p-2 text-gray-900 outline-none rounded-[4px] mt-1"
    :placeholder="props.placeholder"
    :name="props.name"
  />
</template>
<script setup>
const props = defineProps({
  name: {
    type: String,
    default: 'textarea'
  },
  placeholder: {
    type: String,
    default: 'Place holder'
  }
})
</script>
<style lang=""></style>
