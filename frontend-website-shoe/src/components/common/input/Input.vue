<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <input
    class="w-full px-3 py-2 text-gray-800 rounded"
    :type="props.type"
    :placeholder="placeholder"
    :name="name"
    :id="props.name"
    :value="props.modelValue"
    v-on:input="updateValue($event.target.value)"
  />
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
  placeholder: {
    type: String,
    default: 'Enter you text'
  },
  modelValue: {
    type: [String, Number],
    default: ''
  }
})
const emit = defineEmits(['update:modelValue'])

const updateValue = (value) => {
  emit('update:modelValue', value)
}
</script>
<style lang=""></style>
