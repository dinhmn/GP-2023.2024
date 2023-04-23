<template lang="">
  <div class="mt-2">
    <h2 class="px-2 py-4 text-2xl font-bold rounded bg-dark-blue">{{ props.title }}</h2>
    <slot />
  </div>
</template>
<script setup>
const props = defineProps({
  title: {
    type: String,
    default: 'Mô tả sản phẩm'
  }
})
</script>
