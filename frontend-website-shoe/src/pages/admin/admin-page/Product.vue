<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Sản phẩm" actionNew="ProductRegisterAdmin">
    <template v-slot:search>
      <form class="flex w-[600px] gap-2 items-center justify-center">
        <Input
          type="text"
          placeholder="Tìm kiếm..."
          name="search"
          id="search"
          classChild="min-w-[200px] py-[6px] rounded-sm max-w-[400px] max-h-[40px]"
          v-model="searchData.searchValue"
        />
        <select
          name="categoryId"
          class="w-full p-2 text-black rounded-sm outline-none"
          id="categoryId"
          v-model="searchData.sortBy"
        >
          <option value="product_name">Tên sản phẩm</option>
          <option value="category_name">Tên hãng sản xuất</option>
          <option value="product_price">Giá</option>
        </select>
        <Button
          type="button"
          text="Tìm kiếm"
          id="search"
          @click="search"
          className="bg-[#0c3247] w-[300px] text-[#17b1ea] hover:bg-[#10405a] hover:text-white"
        />
      </form>
    </template>
    <template v-slot:thead>
      <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
        <tr class="rounded-tl-md">
          <th>STT.</th>
          <th>Hãng sản xuất</th>
          <th>Tên sản phẩm</th>
          <th>Giá niêm yết</th>
          <th>Số lượng</th>
          <th class="w-[150px]">Hành động</th>
        </tr>
      </thead>
    </template>
    <template v-slot:tbody>
      <tbody>
        <tr v-for="(item, index) in api.data" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ item.categoryName }}</td>
          <td>{{ item.productName }}</td>
          <td>
            {{
              formatPrice(item.productPriceSale) !== null
                ? formatPrice(item.productPriceSale)
                : formatPrice(item.productPrice)
            }}
          </td>
          <td>{{ item.productQuantity }}</td>
          <td class="flex items-center justify-around gap-2">
            <router-link
              :to="{
                name: 'ProductEditAdmin',
                params: { categoryId: item.categoryId, productId: item.productId }
              }"
            >
              <button
                className="min-w-[60px] px-2 text-sm bg-green-700 hover:bg-green-600 block text-center m-0 hover:text-white"
                name="edit"
              >
                Edit
              </button>
            </router-link>

            <button
              class="block min-w-[60px] px-2 m-0 text-sm text-center bg-red-700 hover:bg-red-600 mr-3"
              name="delete"
              @click.prevent="onDelete($event, item.categoryId, item.productId)"
            >
              Delete
            </button>
          </td>
        </tr>
      </tbody>
    </template>
    <template v-slot:page>
      <ul class="flex items-center justify-end gap-1">
        <li class="active hover:opacity-80" @click="onPreviousPage">Sau</li>
        <li
          @click="onSetPage(item)"
          v-for="item in api.totalPages"
          :key="item"
          :class="active == item ? 'active' : ''"
        >
          {{ item }}
        </li>
        <li class="active hover:opacity-80" @click="onNextPage">Tiếp</li>
      </ul>
    </template>
  </CommonAdmin>
</template>
<script setup>
import { onMounted, reactive, ref } from 'vue'
import CommonAdmin from '@/components/common/CommonAdmin.vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import axios from 'axios'
import ProductService from '@/stores/modules/ProductService'
const api = reactive({
  data: [],
  last: '',
  pageNo: 0,
  pageSize: 0,
  totalElements: 0,
  totalPages: ''
})
const result = reactive({
  putResult: null,
  deleteResult: null
})
const searchData = reactive({
  pageNo: '',
  pageSize: '',
  sortDirection: 'ASC',
  sortBy: 'product_name',
  searchValue: ''
})
const active = ref(1)
onMounted(async () => {
  await getAllData(api, 0)
})

async function getAllData(api, pageNo) {
  try {
    const res = await ProductService.getAll('/init/pageable', pageNo)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
    searchData.pageNo = api.pageNo
    searchData.pageSize = api.pageSize
  } catch (error) {
    api = formatResponse(error.response?.data) || error
  }
}

async function search() {
  try {
    const res = await ProductService.search('/init/pageable', searchData)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
    searchData.pageNo = api.pageNo
    searchData.pageSize = api.pageSize
  } catch (error) {
    formatResponse(error.response?.data) || error
  }
}

async function onSetPage(pageNo) {
  try {
    await getAllData(api, pageNo - 1)
    active.value = pageNo
  } catch (err) {
    result.deleteResult = formatResponse(err.response?.data) || err
  }
}

function onPreviousPage() {
  try {
    if (active.value <= api.totalPages && active.value > 1) {
      active.value -= 1
    }
    getAllData(api, active.value - 1)
  } catch (err) {
    result.deleteResult = formatResponse(err.response?.data) || err
  }
}

function onNextPage() {
  try {
    if (active.value < api.totalPages) {
      active.value += 1
    }
    getAllData(api, active.value - 1)
  } catch (err) {
    result.deleteResult = formatResponse(err.response?.data) || err
  }
}

async function onDelete(event, categoryId, productId) {
  try {
    ProductService.delete('/delete', categoryId, productId)
    axios
      .get('http://localhost:8088/api/products/init?page_no=0')
      .then((response) => {
        api.data = response.data
      })
      .catch((error) => {
        console.log(error)
      })
  } catch (error) {
    console.log(error)
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
function formatPrice(value) {
  let val = (value / 1).toFixed(0).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
}
</script>
<style lang="css">
ul li {
  padding: 4px 20px;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 3px;
  cursor: pointer;
}
ul li.active {
  background-color: #0c3247;
  color: #17b1ea;
}
table,
tr,
td,
th {
  padding: 5px 10px;
  text-align: center;
}

table tbody tr {
  border-bottom: 2px solid #464b53;
}
table tbody tr td {
  padding: 5px;
}
</style>
