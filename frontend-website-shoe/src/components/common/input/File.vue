<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="">
    <label for="file" class="grid grid-cols-8 mt-2">
      <input
        type="text"
        class="col-span-7 rounded-tr-none rounded-br-none disabled:bg-white max-h-[40px]"
        name="fileName"
        for="file"
        disabled
        :value="fileName"
        v-on:change="updateValue($event.target.value)"
        placeholder="Choose file..."
      />
      <span
        class="text-[#17b1ea] block text-center rounded-tl-none rounded-bl-none col-span-1 rounded cursor-pointer bg-[#0c3247] py-2 px-3"
        >Upload file</span
      >
    </label>
    <input @change="onChange($event)" type="file" id="file" class="hidden w-full" multiple />
  </div>
</template>
<script setup>
import { ref } from 'vue'
const fileName = ref([])
const onChange = (event) => {
  fileName.value = event.target.files
  console.log(fileName.value)
}
const emit = defineEmits(['update:fileName'])

const updateValue = (value) => {
  emit('update:fileName', value)
}
</script>
<style lang="css">
#file {
  display: none;
}
</style>
