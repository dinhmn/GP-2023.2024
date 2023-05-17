<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Đơn hàng" actionNew="false" class="relative">
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
          <option value="order_id">Hóa đơn</option>
          <option value="created_date">Ngày tạo mới nhất</option>
        </select>
        <Button
          type="button"
          text="Tìm kiếm"
          id="search"
          @click="onSearch"
          className="bg-[#0c3247] text-[#17b1ea] hover:bg-[#10405a] hover:text-white w-[300px]"
        />
      </form>
    </template>
    <template v-slot:thead>
      <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
        <tr class="rounded-tl-md">
          <th>STT.</th>
          <th>Họ và tên</th>
          <th>Địa chỉ</th>
          <th>Mã hóa đơn</th>
          <th>Ngày tạo hóa đơn</th>
          <th>Lý do hủy</th>
          <th>Trạng thái</th>
          <th class="w-[150px]">Hành động</th>
        </tr>
      </thead>
    </template>
    <template v-slot:tbody>
      <tbody>
        <tr v-for="(item, index) in api.data" :key="item.orderId">
          <td>{{ index + 1 }}</td>
          <td>
            {{ item.fullName }}
          </td>
          <td>{{ item.customerAddress }}</td>
          <td>{{ item.orderCode }}</td>
          <td>{{ new Date(item.createdDate).toLocaleString().replaceAll('/', '-') }}</td>
          <td>
            <span>{{ item.customerNote }}</span>
          </td>
          <td>
            <select
              name="role"
              class="w-[120px] px-3 py-1 text-gray-800 rounded outline-none cursor-pointer"
              :value="item.orderStatus"
              :disabled="item.orderStatus === '1'"
              :class="item.orderStatus === '1' ? 'select-none pointer-events-none bg-gray-500' : ''"
              @change="onConfirm($event, item)"
            >
              <option value="0">Chờ</option>
              <option value="1">Xác nhận</option>
              <option value="2">Hủy</option>
            </select>
          </td>
          <td class="flex items-center justify-around gap-2">
            <router-link :to="{ name: 'OrderDetail', params: { orderId: item.orderId } }">
              <button
                className="min-w-[60px] px-2 text-sm bg-green-700 hover:bg-green-600 block text-center m-0 hover:text-white"
                name="edit"
                @click="onView($event, item.orderId)"
              >
                Xem
              </button>
            </router-link>
            <button
              class="block min-w-[100px] px-2 m-0 text-sm text-center bg-blue-700 hover:bg-blue-600 mr-3"
              name="delete"
              :class="item.status === '1' ? 'select-none pointer-events-none bg-gray-500' : ''"
              @click="sendMail($event, item)"
            >
              Gửi mail
            </button>
          </td>
        </tr>
      </tbody>
      <!-- </form> -->
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
import Button from '@/components/common/button/Button.vue'
import Input from '@/components/common/input/Input.vue'
import OrderService from '@/stores/modules/OrderService'
import { useRouter } from 'vue-router'
import axios from 'axios'
const searchData = reactive({
  pageNo: 0,
  pageSize: 10,
  searchValue: '',
  sortBy: 'order_id',
  sortDirection: 'ASC'
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
const route = useRouter()
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

function onConfirm(event, item) {
  try {
    if (Number(event.target.value) !== 2) {
      OrderService.update('/update', item.orderId, event.target.value)
      if (Number(event.target.value) === 1) {
        // OrderService.delete(Number(item.orderId))
        sendMail(event, item)
      }
    }
  } catch (error) {
    route.push({ name: '404' })
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
      pageNo: active.value - 1,
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
    const pdf = await OrderService.exportPdf('/export-pdf', orderId)
    console.log(pdf)
  } catch (error) {
    route.push({ name: '404' })
  }
}
async function onSearch() {
  try {
    let page = {
      pageNo: searchData.pageNo,
      pageSize: 10,
      sortDirection: searchData.sortBy === 'created_date' ? 'DESC' : searchData.sortDirection,
      sortBy: searchData.sortBy,
      value: searchData.searchValue
    }
    const res = await OrderService.getAllOrder('/get-all', page)
    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }
    fetchData(result.data, api)
    console.log(result.data)
  } catch (error) {
    console.log(error)
  }
}
const sendMail = (event, item) => {
  let requestModel = {
    mailName: 'Xác nhận đơn hàng',
    mailTo: 'ngocdinh2k1@gmail.com',
    mailFrom: 'ngocdinh11052001@gmail.com',
    mailSubject: `Chào bạn, Mail xác nhận đơn hàng`
  }
  axios
    .post('http://localhost:8088/api/send-mail/post/' + item.orderId, requestModel)
    .then((response) => console.log(response))
    .catch((error) => console.log(error))
}
</script>
<style lang="css" scoped>
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
