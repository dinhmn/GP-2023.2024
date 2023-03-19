<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <select
    :name="props.name"
    class="w-full px-3 py-2 text-gray-800 rounded outline-none cursor-pointer"
    :value="props.modelValue"
    v-on:input="updateValue($event.target.value)"
  >
    <slot name="option" />
  </select>
</template>
<script setup>
const props = defineProps({
  name: {
    type: String,
    default: 'city'
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
<style lang="scss">
select {
  position: relative;
  option {
    padding: 8px 12px;
  }
  &::after {
    position: absolute;
    content: '';
    height: 10px;
    width: 10px;
    background: #000;
    z-index: 1;
  }
}
</style>
