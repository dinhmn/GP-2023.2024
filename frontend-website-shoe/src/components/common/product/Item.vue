<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div
    class="flex flex-col w-full gap-4 py-4 px-1 rounded cursor-pointer h-[360px] item bg-slate-700 hover:bg-cyan-bluish-gray transition-all"
    :class="classChild"
  >
    <img
      class="object-cover w-full h-full max-h-[230px]"
      :src="getImageUrl(product.src)"
      :alt="alt"
    />
    <div class="flex flex-col gap-1 ml-2">
      <span class="text-base text-white">{{ productName }}</span>
      <h4 class="text-base text-white">
        Giá: <span class="text-sm line-through">{{ formatPrice(product.price) }} đ</span>
        {{ formatPrice(product.priceSale) }} đ
      </h4>
      <h5 class="text-sm text-[#17b1ea] pb-3">Số lượng: {{ product.quantitySold }}</h5>
    </div>
  </div>
</template>
<script setup>
const product = defineProps({
  src: {
    type: String,
    default: 'default.png'
  },
  alt: {
    type: String,
    default: 'No image'
  },
  productName: {
    type: String,
    default: 'Giày'
  },
  price: {
    type: String,
    default: '0'
  },
  priceSale: {
    type: String,
    default: '0'
  },
  quantitySold: {
    type: Number,
    default: 0
  },
  classChild: {
    type: String,
    default: ''
  }
})
const getImageUrl = (root) => {
  return new URL(`../../../assets/images/${root}`, import.meta.url).href
}
const formatPrice = (value) => {
  // let val = (value / 1).toFixed(2).replace('.', ',')
  let val = (value / 1).toFixed(0).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
}
</script>
<style lang=""></style>
