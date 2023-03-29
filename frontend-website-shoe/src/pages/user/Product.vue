<template lang="">
  <BasePage >
    <template v-slot:body>
      <div>
    Heading
  </div>
  <div class="grid w-full grid-cols-4 gap-2">
    <div class="col-span-1 form-search h-[100vh] bg-slate-200">
      <h3>Danh mục sản phẩm</h3>
    </div>
    <div class="col-span-3">
      <div class="grid grid-cols-4 gap-3">
        <Item
            :price="item * 16456 + 80000"
            :priceSale="item * 92340 + 70000"
            :quantitySold="40 + item * 3"
            productName="Giày Nike"
            src="item.png"
            alt="Giày"
            v-for="item in count"
            :key="item"
            classChild="col-span-1 mb-3 bg-[#0c3247] text-[#17b1ea]"
          />
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
