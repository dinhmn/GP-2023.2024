<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <textarea
    rows="2"
    class="w-full p-2 text-gray-900 outline-none rounded-[4px] mt-2"
    :placeholder="props.placeholder"
    :id="props.name"
    :name="props.name"
    :value="props.modelValue"
    v-on:input="updateValue($event.target.value)"
  />
</template>
<script setup>
const props = defineProps({
  id: {
    type: String,
    default: 'textarea'
  },
  name: {
    type: String,
    default: 'textarea'
  },
  placeholder: {
    type: String,
    default: 'Place holder'
  },
  modelValue: {
    type: String,
    default: ''
  }
})
const emit = defineEmits(['update:modelValue'])

const updateValue = (value) => {
  emit('update:modelValue', value)
}
</script>
<style lang=""></style>
