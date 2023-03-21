<template lang="">
  <div class="mt-2">
    <h2 class="text-2xl font-bold">{{ props.title }}</h2>
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
<style lang="css" scoped>
h2 {
  background-color: #ff4a684d;
  padding: 8px 12px;
  border-radius: 4px;
}
</style>
