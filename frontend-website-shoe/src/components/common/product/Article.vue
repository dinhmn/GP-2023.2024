<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div
    class="flex flex-col w-full gap-4 p-4 transition-all rounded-lg cursor-pointer hover:text-brown bg-slate-700"
  >
    <img class="object-cover w-full h-[200px]" :src="getImageUrl(article.src)" :alt="article.alt" />
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
const getImageUrl = (root) => {
  return new URL(`../../../assets/images/${root}`, import.meta.url).href
}
</script>
<style lang="" scoped></style>
