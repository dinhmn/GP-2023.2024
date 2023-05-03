<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Contact" actionNew="false">
    <template v-slot:search>
      <form class="flex w-[600px] gap-2 items-center justify-center -ml-7">
        <Input
          type="text"
          placeholder="Search..."
          name="search"
          id="search"
          classChild="min-w-[200px] py-[6px] rounded-sm max-w-[400px] max-h-[40px]"
          v-model="searchData.searchValue"
        />
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
          <th>Contact name</th>
          <th>Contact message</th>
          <th>Contact email</th>
          <th>Created date</th>
          <th>Status</th>
          <th class="w-[150px]">Action</th>
        </tr>
      </thead>
    </template>
    <template v-slot:tbody>
      <tbody>
        <tr v-for="(item, index) in api.data" :key="index">
          <td>{{ index }}</td>
          <td>{{ item.contactName }}</td>
          <td>{{ item.contactMessage }}</td>
          <td>{{ item.contactEmail }}</td>
          <td>{{ new Date(item.createdDate).toLocaleDateString() }}</td>
          <td>
            <strong
              v-if="item.status === '0'"
              class="px-[20px] py-[4px] text-xs text-yellow-700 bg-yellow-400 rounded-full"
              >Wait</strong
            >
            <strong
              v-if="item.status === '1'"
              class="px-[20px] py-[4px] text-xs min-w-[100px] text-green-700 bg-green-400 rounded-full"
              >Confirm</strong
            >
          </td>
          <td class="flex items-center justify-around gap-2">
            <button
              class="block min-w-[60px] px-2 m-0 text-sm text-center bg-green-700 hover:bg-green-600 mr-3"
              name="delete"
              v-if="item.status == null"
            >
              Checked
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
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import ContactService from '@/stores/modules/ContactService'
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
const searchData = reactive({
  pageNo: 0,
  pageSize: 10,
  sortDirection: 'ASC',
  sortBy: 'contact_id',
  searchValue: ''
})
async function getAllData(api, pageNo) {
  try {
    const res = await ContactService.getAll('/init', pageNo)

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

async function onSearch() {
  try {
    const res = await ContactService.getAllPage('/init/pageable', searchData)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
    api.data = result.data.contactList
  } catch (error) {
    console.log(error)
  }
}

function fetchData(result, api) {
  api.data = result.contactModelList
  api.last = result.last
  api.pageNo = result.pageNo
  api.pageSize = result.pageSize
  api.totalElements = result.totalElements
  api.totalPages = result.totalPages
}

function formatResponse(res) {
  return JSON.stringify(res, null, 2)
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
