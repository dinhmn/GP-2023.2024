<template lang="">
  <BasePage >
    <template v-slot:body>
      <div class="flex items-center justify-between w-full px-3 py-3 mb-2 text-white rounded bg-slate-700">
          <div class="flex items-center justify-between gap-3 text-xl font-bold">
            <h3>Trang chủ</h3>
            <vue-feather type="chevron-right"></vue-feather>
            <h3 class="text-[#17b1ea]">Sản phẩm</h3>
          </div>
          <div class="flex items-center justify-center">
            <Input
              type="text"
              classChild="min-w-[300px] px-2 rounded-sm"
              name="search"
              placeholder="Tìm kiếm sản phẩm..."
              v-model="search"
            />
            <button
              class="right-0 px-6 py-[12px] bg-[#0c3247] hover:bg-[#135070] rounded-tr-[3px] -translate-x-3 rounded-br-[3px] m-0 text-xs rounded-none"
            >
              Tìm
            </button>
        </div>
      </div>
  <div class="grid w-full grid-cols-4 gap-2">
    <div class="col-span-1 form-search h-[100%] flex flex-col gap-4 rounded-md p-2">
      <h3 class="text-[#17b1ea] font-bold text-xl block text-center py-2 bg-slate-700">Danh mục sản phẩm</h3>
      <div>
        <ul class="flex flex-col gap-2 text-sm bg-slate-700">
          <li v-for="item in count" :key="item" @click="onSelectedCategory($event, item)" class="py-2 px-4 text-base text-white hover:text-[#17b1ea] border-transparent border-l-[4px] border-solid" :class="active == item ? 'active' : ''">Danh mục</li>
        </ul>
      </div>
      <div>

      </div>
    </div>
    <div class="col-span-3">
      <div class="grid grid-cols-4 gap-3">
        <div v-for="item in count" classChild="col-span-1 mb-3 bg-[#0c3247] text-[#17b1ea]">
          <router-link :to="{ name: 'DetailProduct', params: {id: item}}">
            <Item
            :price="Number(item * 16456 + 80000)"
            :priceSale="Number(item * 92340 + 70000)"
            :quantitySold="Number(40 + item * 3)"
            productName="Giày Nike"
            src="default.png"
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
import {ref, reactive} from "vue"
import BasePage from '../auth/BasePage.vue';
import Item from '@/components/common/product/Item.vue';
import Input from "@/components/common/input/Input.vue";
const count = 10
const active = ref('');
const onSelectedCategory = (event, categoryId) => {
  active.value =categoryId;
}
</script>
<style lang="css">
.active {
  background-color: #0c3247;
  color: #17b1ea;
  border-left: 4px solid #17b1ea;
}
</style>
