<template lang="">
  <BasePage >
    <template v-slot:body>
      <div>
    Heading
  </div>
  <div class="grid w-full grid-cols-4 gap-2">
    <div class="col-span-1 form-search h-[100%] bg-slate-700 rounded-md p-2">
      <h3 class="text-[#17b1ea] font-bold text-xl block text-left">Danh mục sản phẩm</h3>
      <div>
        <ul>
          <li>Danh mục</li>
          <li>Danh mục</li>
          <li>Danh mục</li>
          <li>Danh mục</li>
          <li>Danh mục</li>
          <li>Danh mục</li>
        </ul>
      </div>
    </div>
    <div class="col-span-3">
      <div class="grid grid-cols-4 gap-3">
      
        <div v-for="item in count" classChild="col-span-1 mb-3 bg-[#0c3247] text-[#17b1ea]">
          <router-link :to="{ name: 'DetailProduct', params: {id: item}}">
            <Item
            :price="item * 16456 + 80000"
            :priceSale="item * 92340 + 70000"
            :quantitySold="40 + item * 3"
            productName="Giày Nike"
            src="item.png"
            alt="Giày"
            :key="item"
          />
          </router-link>
        </div>
      </div>
    </div>
  </div>
    </template>
  </BasePage>
</template>
<script setup>
import BasePage from '../auth/BasePage.vue';
import Item from '@/components/common/product/Item.vue';
const count = 10

</script>
<style lang=""></style>
