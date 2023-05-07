<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div
    class="flex flex-col w-full gap-4 p-4 transition-all rounded-lg hover:bg-gray-200 cursor-pointer text-cyan-800 bg-[#F4F4F4]"
  >
    <slot name="articleChild" />
    <div class="flex flex-col items-center justify-center gap-2 ml-2">
      <span class="block text-base text-center" :class="classTitle">{{ article.title }}</span>
    </div>
  </div>
</template>
<script setup>
const article = defineProps({
  src: {
    type: String,
    default: 'default.png'
  },
  alt: {
    type: String,
    default: 'No image'
  },
  title: {
    type: String,
    default: 'Title'
  },
  classTitle: {
    type: String,
    default: ''
  }
})
</script>
<style lang="" scoped></style>
