<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Order" actionNew="false">
    <template v-slot:search>
      <form class="flex w-[600px] gap-2 items-center justify-center">
        <Input
          type="text"
          placeholder="Search..."
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
          <option value="order_id">Order</option>
          <option value="created_date">Date</option>
        </select>
        <Button
          type="button"
          text="Search"
          id="search"
          @click="onSearch"
          className="bg-[#0c3247] text-[#17b1ea] hover:bg-[#10405a] hover:text-white"
        />
      </form>
    </template>
    <template v-slot:thead>
      <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
        <tr class="rounded-tl-md">
          <th>No.</th>
          <th>Full name</th>
          <th>Full name</th>
          <th>Order code</th>
          <th>Created date</th>
          <th>Status</th>
          <th class="w-[150px]">Action</th>
        </tr>
      </thead>
    </template>
    <template v-slot:tbody>
      <tbody>
        <tr v-for="(item, index) in api.data" :key="item.orderId">
          <td>{{ index + 1 }}</td>
          <td>
            {{
              item.customerLastName !== null
                ? item.customerLastName
                : '' + ' ' + item.customerFirstName !== null
                ? item.customerFirstName
                : ''
            }}
          </td>
          <td>{{ item.customerAddress }}</td>
          <td>{{ item.orderCode }}</td>
          <td>{{ item.createdDate }}</td>
          <td>{{ item.status === 'true' ? 'Complete' : 'Wait' }}</td>
          <td class="flex items-center justify-around gap-2">
            <router-link to="/">
              <button
                className="min-w-[60px] px-2 text-sm bg-green-700 hover:bg-green-600 block text-center m-0 hover:text-white"
                name="edit"
                @click="onView($event, item.orderId)"
              >
                View
              </button>
            </router-link>

            <button
              class="block min-w-[60px] px-2 m-0 text-sm text-center bg-blue-700 hover:bg-blue-600 mr-3"
              name="delete"
              @click.prevent="onConfirm($event, item.orderId)"
            >
              Confirm
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
import Button from '@/components/common/button/Button.vue'
import Input from '@/components/common/input/Input.vue'
import axios from 'axios'
import OrderService from '@/stores/modules/OrderService'
const searchData = reactive({
  searchValue: '',
  sortBy: 'order_id'
})
const api = reactive({
  data: [],
  last: '',
  pageNo: 0,
  pageSize: 0,
  totalElements: 0,
  totalPages: 0
})
const active = ref(1)
onMounted(async () => {
  let page = {
    pageNo: 0,
    pageSize: 10,
    sortDirection: 'ASC',
    sortBy: 'order_id',
    value: ''
  }
  await getAllData(api, page)
})

async function getAllData(api, page) {
  try {
    const res = await OrderService.getAllOrder('/get-all', page)
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

async function onConfirm(event, orderId) {
  try {
    OrderService.update('/update', orderId, '1')
    let page = {
      pageNo: 0,
      pageSize: 10,
      sortDirection: 'ASC',
      sortBy: 'order_id',
      value: ''
    }
    await getAllData(api, page)
  } catch (error) {
    console.log(error)
  }
}

async function onSetPage(pageNo) {
  try {
    let page = {
      pageNo: pageNo - 1,
      pageSize: 10,
      sortDirection: 'ASC',
      sortBy: 'order_id',
      value: ''
    }
    await getAllData(api, page)
    active.value = pageNo
  } catch (err) {
    console.log(err)
  }
}

async function onPreviousPage() {
  try {
    if (active.value <= api.totalPages && active.value > 1) {
      active.value -= 1
    }
    let page = {
      pageNo: active.value - 1,
      pageSize: 10,
      sortDirection: 'ASC',
      sortBy: 'order_id',
      value: ''
    }
    console.log(page)
    await getAllData(api, page)
    console.log(api)
  } catch (err) {
    console.log(err)
  }
}

async function onNextPage() {
  try {
    if (active.value < api.totalPages) {
      active.value += 1
    }
    let page = {
      pageNo: active.value + 1,
      pageSize: 10,
      sortDirection: 'ASC',
      sortBy: 'order_id',
      value: ''
    }
    await getAllData(api, page)
  } catch (err) {
    console.log(err)
  }
}

function fetchData(result, api) {
  api.data = result.orderList
  api.last = result.last
  api.pageNo = result.pageNo
  api.pageSize = result.pageSize
  api.totalElements = result.totalElements
  api.totalPages = result.totalPages
}

function formatResponse(res) {
  return JSON.stringify(res, null, 2)
}
const onView = async (event, orderId) => {
  try {
    console.log(event)
    const pdf = await OrderService.exportPdf('/export-pdf', orderId)
    console.log(pdf)
  } catch (error) {
    console.log(error)
  }
}
function onSearch() {}
// const sendMail = () => {
//   let requestModel = {
//     mailName: 'dinhmn',
//     mailTo: 'ngocdinh2k1@gmail.com',
//     mailFrom: 'ngocdinh11052001@gmail.com',
//     mailSubject: 'chao ban'
//   }
//   axios
//     .post('http://localhost:8088/api/send-mail/post', requestModel)
//     .then((response) => console.log(response))
//     .catch((error) => console.log(error))
// }
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
