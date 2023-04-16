<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Product" page="5" actionNew="ProductRegisterAdmin">
    <template v-slot:thead>
      <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
        <tr class="rounded-tl-md">
          <th>No.</th>
          <th>Category Name</th>
          <th>Product Name</th>
          <th>Product Price</th>
          <th>Quantity</th>
          <th>Status</th>
          <th class="w-[150px]">Action</th>
        </tr>
      </thead>
    </template>
    <template v-slot:tbody>
      <tbody>
        <tr v-for="(item, index) in api.data" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ item.categoryName }}</td>
          <td>{{ item.productName }}</td>
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
        <li @click="onPreviousPage">Prev</li>
        <li
          @click="onSetPage(item)"
          v-for="item in api.totalPages"
          :key="item"
          :class="active == item ? 'active' : ''"
        >
          {{ item }}
        </li>
        <li @click="onNextPage">Next</li>
      </ul>
    </template>
  </CommonAdmin>
</template>
<script setup>
import { onMounted, reactive, ref } from 'vue'
import CommonAdmin from '@/components/common/CommonAdmin.vue'
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
  } catch (error) {
    api = formatResponse(error.response?.data) || error
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
