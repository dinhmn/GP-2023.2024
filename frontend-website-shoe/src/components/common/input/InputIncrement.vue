<template lang="">
  <div class="flex items-center justify-center" :class="classChild">
    <button
      class="flex p-2 mr-1 align-middle bg-dark-blue hover:bg-dark-blue-hover"
      @click="decrement"
    >
      <vue-feather class="w-4 h-4" type="minus" />
    </button>
    <input
      class="min-w-[40px] pointer-events-none select-none bg-dark-blue text-white text-center h-full w-[30px] text-gray-800 rounded"
      disabled
      type="text"
      :name="name"
      :id="props.name"
      :value="propsValue"
      v-on:input="updateValue($event.target.value)"
    />
    <button
      class="flex p-2 ml-1 align-middle bg-dark-blue hover:bg-dark-blue-hover"
      @click="increment"
    >
      <vue-feather class="w-4 h-4" type="plus" />
    </button>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import render from '@/stores/modules/re-render'
import { elements } from 'chart.js'
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
    type: Number,
    default: 1
  },
  classChild: {
    type: String,
    default: ''
  }
})
const propsValue = ref(props.modelValue)
const emit = defineEmits(['update:modelValue'])

const updateValue = (value) => {
  value = propsValue.value
  emit('update:modelValue', value)
}
const increment = () => {
  const items = JSON.parse(localStorage.getItem('order'))
  const productId = props.name.replaceAll('quantity-', '')
  if (items !== null) {
    items.value.forEach((element) => {
      if (element.productId === Number(productId)) {
        element.productQuantity += 1
      }
    })
    let item = {
      value: items.value,
      expiry: new Date().getTime() + 86400000
    }
    localStorage.setItem('order', JSON.stringify(item))
    render()
  }
  propsValue.value += 1
  updateValue(propsValue.value)
}
const decrement = () => {
  const items = JSON.parse(localStorage.getItem('order'))
  const productId = props.name.replaceAll('quantity-', '')
  if (items !== null) {
    items.value.forEach((element) => {
      if (
        element.productId === Number(productId) &&
        Number(elements.productQuantity) !== Number(1)
      ) {
        console.log(element.productQuantity)
        element.productQuantity -= 1
      }
    })
    let item = {
      value: items.value,
      expiry: new Date().getTime() + 86400000
    }
    localStorage.setItem('order', JSON.stringify(item))
    render()
  }

  propsValue.value = propsValue.value === 1 ? 1 : propsValue.value - 1
  updateValue(propsValue.value)
}
</script>
<style lang=""></style>
