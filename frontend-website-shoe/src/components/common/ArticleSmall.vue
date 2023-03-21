<template lang="">
  <div class="flex w-full gap-4 mt-3">
    <img
      src="../../assets/images/default.png"
      class="h-[80px] w-[80px] object-cover rounded"
      alt=""
    />
    <span class="text-sm">{{ props.title }}</span>
  </div>
</template>
<script setup>
const props = defineProps({
  title: {
    type: String,
    default: ''
  }
})
</script>
<style lang=""></style>
