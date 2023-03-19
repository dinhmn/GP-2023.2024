<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="relative block mx-0 my-2 bg-gray-800">
    <input
      :value="propsValue"
      v-on:input="updateValue($event.target.value)"
      :id="props.id"
      name="method"
      type="radio"
    />
    <label :for="props.id">{{ props.text }}</label>
  </div>
</template>
<script setup>
import { ref } from 'vue'
const props = defineProps({
  id: {
    type: String,
    default: 'id'
  },
  text: {
    type: String,
    default: 'Radio Text'
  },
  modelValue: {
    type: String,
    default: 'cod'
  }
})
const propsValue = ref(props.modelValue)
const emit = defineEmits(['update:modelValue'])
const updateValue = (value) => {
  emit('update:modelValue', value)
  propsValue.value = value
}
</script>
<style lang="scss" scoped>
div {
  label {
    padding: 4px 30px;
    width: 100%;
    display: block;
    text-align: left;
    color: rgb(180, 180, 180);
    cursor: pointer;
    position: relative;
    z-index: 2;
    transition: color 0.2s ease-in;
    overflow: hidden;
    &::before {
      width: 10px;
      height: 10px;
      border-radius: 50%;
      content: '';
      background-color: #cc334d;
      position: absolute;
      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%) scale3d(1, 1, 1);
      transition: all 300ms cubic-bezier(0.4, 0, 0.2, 1);
      opacity: 0;
      z-index: -1;
    }
    &::after {
      width: 20px;
      height: 20px;
      content: '';
      border: 2px solid #d1d7dc;
      background-color: #fff;
      background-image: url("data:image/svg+xml,%3Csvg width='20' height='20' viewBox='0 0 32 32' xmlns='http://www.w3.org/2000/svg'%3E%3Cpath d='M5.414 11L4 12.414l5.414 5.414L20.828 6.414 19.414 5l-10 10z' fill='%23fff' fill-rule='nonzero'/%3E%3C/svg%3E ");
      background-repeat: no-repeat;
      background-position: 1px 1px;
      border-radius: 50%;
      z-index: 2;
      position: absolute;
      right: 30px;
      top: 50%;
      transform: translateY(-50%);
      cursor: pointer;
      transition: all 200ms ease-in;
    }
  }
  input {
    width: 16px;
    height: 16px;
    order: 1;
    z-index: 2;
    position: absolute;
    right: 30px;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
    visibility: hidden;
    &:checked ~ label {
      color: #fff;
      &::before {
        transform: translate(-50%, -50%) scale3d(56, 56, 1);
        opacity: 1;
      }
      &::after {
        background-color: #54e0c7;
        border-color: #54e0c7;
      }
    }
  }
}
</style>
