<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage>
    <template v-slot:body>
      <div
        class="flex items-center justify-between w-full px-3 py-3 mb-2 text-white rounded bg-slate-700"
      >
        <div class="flex items-center justify-between gap-2 text-xl font-bold">
          <h3>Trang chủ</h3>
          <vue-feather type="chevron-right"></vue-feather>
          <h3 class="text-[#17b1ea]">Sản phẩm</h3>
        </div>
        <div class="flex items-center justify-center">
          <Input
            type="text"
            classChild="min-w-[300px] px-2 rounded-sm"
            name="search"
            placeholder="Tìm kiếm..."
            v-model="state.search"
          />
          <button
            class="right-0 px-6 py-4 bg-[#0c3247] hover:bg-[#135070] rounded-tr-[3px] rounded-br-[3px] m-0 text-xs rounded-none"
            @click="filter()"
          >
            Tìm
          </button>
        </div>
      </div>
      <div class="grid w-full grid-cols-4 gap-2">
        <div class="col-span-1 form-search h-[100%] flex flex-col gap-4 rounded-md py-2 -mt-2">
          <h3 class="text-[#17b1ea] font-bold text-xl block text-center py-2 bg-slate-700 rounded">
            Danh mục sản phẩm
          </h3>
          <div>
            <ul class="flex flex-col gap-2 py-2 text-sm bg-slate-700">
              <li
                v-for="item in api.categoryList"
                :key="item"
                @click="onSelectedCategory($event, item.categoryId)"
                class="py-3 px-4 text-base text-white hover:text-[#17b1ea] border-transparent border-l-[4px] border-solid"
                :class="active == item.categoryId ? 'active' : ''"
              >
                {{ item.categoryName }}
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
              <h3>Kích cỡ</h3>
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
              <h3>Màu sắc</h3>
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
            <button @click.prevent="filter" class="right-0 bg-[#0c3247] hover:bg-[#135070] m-0">
              Filter
            </button>
          </form>
        </div>
        <div class="col-span-3">
          <div class="grid grid-cols-4 gap-3">
            <div
              v-for="item in api.data"
              :key="item.productId"
              classChild="col-span-1 mb-3 bg-[#0c3247] text-[#17b1ea]"
            >
              <router-link
                :to="{
                  name: 'DetailProduct',
                  params: { categoryId: item.categoryId, productId: item.productId }
                }"
              >
                <Item
                  :price="item.productPrice"
                  :priceSale="item.productPriceSale"
                  :quantitySold="Number(item.productQuantity)"
                  :productName="item.productName"
                  :src="item.fileName !== null ? item.fileName : 'default.png'"
                  :alt="item.productName"
                  :key="item.productId"
                />
              </router-link>
            </div>
          </div>
          <div class="w-full my-3 text-center">
            <button class="bg-[#0c3247] hover:bg-[#135070] text-[#17b1ea]" @click="loadMore">
              {{ api.size === api.totalElements ? 'Collapse' : 'Load more' }}
            </button>
          </div>
        </div>
      </div>
    </template>
  </BasePage>
</template>
<script setup>
import { ref, reactive, onMounted } from 'vue'
import BasePage from '../auth/BasePage.vue'
import Item from '@/components/common/product/Item.vue'
import Input from '@/components/common/input/Input.vue'
import axios from 'axios'
import ProductService from '@/stores/modules/ProductService'
import CategoryService from '@/stores/modules/CategoryService'
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
    color: 'white',
    selected: false
  },
  {
    color: 'black',
    selected: false
  },
  {
    color: 'blue',
    selected: false
  },
  {
    color: 'red',
    selected: false
  },
  {
    color: 'green',
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
const active = ref(1)
const state = reactive({
  range: [500000, 2000000],
  number: 100000,
  search: '',
  sizes: [],
  colors: []
})
const api = reactive({
  data: [],
  last: '',
  pageNo: 0,
  pageSize: 0,
  totalElements: 0,
  totalPages: '',
  renderProduct: 2,
  categoryList: {},
  totalSize: 0
})
const onSelectedCategory = async (event, categoryId) => {
  active.value = categoryId
  try {
    const res = await ProductService.getAll('/init-category/' + categoryId, 0)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
  } catch (error) {
    console.log(error)
  }
}
onMounted(async () => {
  await getAllData(api, 0)
  await getAllCategory(api)
})
async function getAllData(api, pageNo) {
  try {
    const res = await ProductService.getAllUser('/init/pageable', pageNo, 12, '', '', '')

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
  } catch (error) {
    api = formatResponse(error.response?.data) || error
  }
}
function fetchData(result, api) {
  api.data = result.productModelList
  api.last = result.last
  api.pageNo = result.pageNo
  api.pageSize = result.pageSize
  api.totalElements = result.totalElements
  api.totalPages = result.totalPages
}

function formatResponse(res) {
  return JSON.stringify(res, null, 2)
}
async function loadMore() {
  try {
    api.size =
      api.renderProduct * 12 >= api.totalElements ? api.totalElements : api.renderProduct * 12

    let res
    if (api.size === api.pageSize) {
      res = await ProductService.getAllUser('/init/pageable', 0, 12, '', '', '')
      api.renderProduct = 2
    } else {
      res = await ProductService.getAllUser('/init/pageable', 0, api.size, '', '', '')
      api.renderProduct += 1
    }

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
  } catch (error) {
    console.log(error)
  }
}
async function getAllCategory(api) {
  try {
    const res = await CategoryService.getAll('/init')

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    api.categoryList = result.data
  } catch (error) {
    console.log(error)
  }
}
const filter = async () => {
  let filterSearch = {
    pageNo: 0,
    pageSize: 12,
    sortBy: 'product_id',
    sortDirection: 'ASC',
    searchValue: '',
    categoryId: '',
    priceMin: 0,
    priceMax: 0,
    sizeList: {},
    colorList: {}
  }
  filterSearch.priceMin = state.range[0]
  filterSearch.priceMax = state.range[1]
  filterSearch.sizeList = sizes.filter((size) => size.selected === true).map((size) => size.size)
  filterSearch.colorList = colors
    .filter((color) => color.selected === true)
    .map((color) => color.color)
  filterSearch.categoryId = active.value
  filterSearch.searchValue = state.search
  let formData = new FormData()
  formData.append('filterProduct', JSON.stringify(filterSearch))
  axios
    .post('http://localhost:8088/api/products/search-filter', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    .then((res) => {
      const result = {
        status: res.status + '-' + res.statusText,
        headers: res.headers,
        data: res.data
      }

      fetchData(result.data, api)
    })
    .catch((error) => {
      console.log(error)
    })
}
</script>
<style lang="css">
.active {
  background-color: #0c3247;
  color: #17b1ea;
  border-left: 4px solid #17b1ea;
}
</style>
