<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage>
    <template v-slot:body>
      <div
        class="flex items-center justify-between w-full px-3 py-3 mb-2 text-white rounded bg-slate-700"
      >
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
            v-model="state.search"
          />
          <button
            class="right-0 px-6 py-[12px] bg-[#0c3247] hover:bg-[#135070] rounded-tr-[3px] -translate-x-3 rounded-br-[3px] m-0 text-xs rounded-none"
            @click="onSearch($event)"
          >
            Tìm
          </button>
        </div>
      </div>
      <div class="grid w-full grid-cols-4 gap-2">
        <div class="col-span-1 form-search h-[100%] flex flex-col gap-4 rounded-md py-2">
          <h3 class="text-[#17b1ea] font-bold text-xl block text-center py-2 bg-slate-700 rounded">
            Danh mục sản phẩm
          </h3>
          <div>
            <ul class="flex flex-col gap-2 py-2 text-sm bg-slate-700">
              <li
                v-for="item in count"
                :key="item"
                @click="onSelectedCategory($event, item)"
                class="py-3 px-4 text-base text-white hover:text-[#17b1ea] border-transparent border-l-[4px] border-solid"
                :class="active == item ? 'active' : ''"
              >
                Danh mục
              </li>
            </ul>
          </div>
          <form action="" class="flex flex-col gap-1 bg-slate-700 text-[#17b1ea] rounded">
            <h1 class="px-2 py-2 text-lg font-bold text-center">Tìm kiếm theo</h1>
            <div class="p-2">
              <h3>Giá</h3>
              <div>
                <VueSimpleRangeSlider
                  class="w-[300px] mt-3"
                  :min="0"
                  :max="10000000"
                  v-model="state.range"
                  active-bar-color="#17b1ea"
                  bar-color="#fff"
                >
                  <template #suffix> đ</template>
                </VueSimpleRangeSlider>
              </div>
            </div>
            <div class="w-full p-2">
              <h3>Size</h3>
              <ul class="grid w-full grid-cols-5 gap-3 mt-3">
                <li
                  class="border-[#0c3247] border-solid border-[2px] px-2 col-span-1 text-center"
                  :class="size.selected ? 'bg-[#0c3247]' : ''"
                  @click="onSelected($event, size, 'size')"
                  v-for="(size, index) in sizes"
                  :key="index"
                >
                  {{ size.size }}
                </li>
              </ul>
            </div>
            <div class="w-full p-2">
              <h3>Màu</h3>
              <ul class="grid w-full grid-cols-5 gap-3 mt-3">
                <li
                  class="border-transparent border-solid border-[2px] px-2 col-span-1 text-center"
                  :class="color.selected ? '' : 'text-transparent'"
                  @click="onSelected($event, color, 'color')"
                  :style="{ backgroundColor: color.color }"
                  v-for="(color, index) in colors"
                  :key="index"
                >
                  x
                </li>
              </ul>
            </div>
            <button class="right-0 bg-[#0c3247] hover:bg-[#135070] m-0">Lọc</button>
          </form>
        </div>
        <div class="col-span-3">
          <div class="grid grid-cols-4 gap-3">
            <div
              v-for="item in count"
              :key="item"
              classChild="col-span-1 mb-3 bg-[#0c3247] text-[#17b1ea]"
            >
              <router-link :to="{ name: 'DetailProduct', params: { id: item } }">
                <Item
                  :price="item * 16456 + 80000 + ''"
                  :priceSale="item * 92340 + 70000 + ''"
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
import { ref, reactive } from 'vue'
import BasePage from '../auth/BasePage.vue'
import Item from '@/components/common/product/Item.vue'
import Input from '@/components/common/input/Input.vue'
import VueSimpleRangeSlider from 'vue-simple-range-slider'
import 'vue-simple-range-slider/css'
const sizes = reactive([
  {
    size: 36,
    selected: false
  },
  {
    size: 37,
    selected: false
  },
  {
    size: 38,
    selected: false
  },
  {
    size: 39,
    selected: false
  },
  {
    size: 40,
    selected: false
  },
  {
    size: 41,
    selected: false
  },
  {
    size: 42,
    selected: false
  },
  {
    size: 43,
    selected: false
  },
  {
    size: 44,
    selected: false
  }
])
const colors = reactive([
  {
    color: '#FFFFFF',
    selected: false
  },
  {
    color: '#000000',
    selected: false
  },
  {
    color: '#0000FF',
    selected: false
  },
  {
    color: '#FF3333',
    selected: false
  },
  {
    color: '#00CC00',
    selected: false
  }
])
const onSelected = (event, value, type) => {
  if (type == 'size') {
    sizes.forEach((element) => {
      if (element.size === value.size) {
        element.selected = !element.selected
      }
    })
  }
  if (type == 'color') {
    colors.forEach((element) => {
      if (element.color === value.color) {
        element.selected = !element.selected
      }
    })
  }
}
const count = 10
const active = ref(1)
const state = reactive({
  range: [500000, 2000000],
  number: 100000,
  search: '',
  sizes: [],
  colors: []
})
const onSelectedCategory = (event, categoryId) => {
  active.value = categoryId
}
const onSearch = (event) => {
  state.sizes = sizes.filter((size) => (size.selected = true))
  state.colors = colors.filter((color) => (color.selected = true))
  console.log(event, state)
}
</script>
<style lang="css">
.active {
  background-color: #0c3247;
  color: #17b1ea;
  border-left: 4px solid #17b1ea;
}
</style>
