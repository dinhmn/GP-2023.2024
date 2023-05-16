<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Tài khoản" actionNew="false" class="h-[100vh]">
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
          <option value="username">Username</option>
          <option value="last_name">Tên</option>
          <option value="created_date">Ngày tạo</option>
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
          <th>Tên người dùng</th>
          <th>Họ và tên</th>
          <th>Email</th>
          <th>Ngày tạo</th>
          <th>Quyền</th>
          <th>Trạng thái</th>
          <!-- <th class="w-[150px]">Hành động</th> -->
        </tr>
      </thead>
    </template>
    <template v-slot:tbody>
      <tbody>
        <tr v-for="(item, index) in api.data" :key="item.userId">
          <td>{{ index }}</td>
          <td>{{ item.username }}</td>
          <td>
            {{
              (item.lastName !== null ? item.lastName : '') +
              ' ' +
              (item.firstName !== null ? item.firstName : '')
            }}
          </td>
          <td>{{ item.userEmail }}</td>
          <td>{{ new Date(item.createdDate).toLocaleDateString().replaceAll('/', '-') }}</td>
          <td>
            <select
              name="role"
              class="w-[100px] px-3 py-1 text-gray-800 rounded outline-none cursor-pointer"
              :value="item.roleId"
              @change="onChangeRole($event, item.userId)"
            >
              <option v-for="role in api.roleList" :key="role.roleId" :value="role.roleId">
                {{ role.roleName }}
              </option>
            </select>
          </td>
          <td>{{ item.status === true ? 'Hoạt động' : 'Không hoạt động' }}</td>
          <!-- <td class="flex items-center justify-around gap-2">
            {{ item.roleId }}
          </td> -->
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
// import moment from 'moment'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'

import CommonAdmin from '@/components/common/CommonAdmin.vue'
import UserService from '@/stores/modules/UserService'
import { useRoute } from 'vue-router'
const api = reactive({
  data: [],
  roleList: [],
  last: '',
  pageNo: 0,
  pageSize: 0,
  totalElements: 0,
  totalPages: ''
})
const searchData = reactive({
  pageNo: '',
  pageSize: '',
  sortDirection: 'ASC',
  sortBy: 'username',
  searchValue: ''
})
const route = useRoute()
const active = ref(1)
onMounted(async () => {
  await getAllData(api, 0)
})

async function getAllData(api, pageNo) {
  try {
    const res = await UserService.getAll('/get-all', pageNo)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
    console.log(api)
  } catch (error) {
    console.log(error)
  }
}

function fetchData(result, api) {
  api.data = result.userEntityList
  api.roleList = result.roleEntityList
  api.last = result.last
  api.pageNo = result.pageNo
  api.pageSize = result.pageSize
  api.totalElements = result.totalElements
  api.totalPages = result.totalPages
}

function onChangeRole(event, userId) {
  try {
    UserService.update('/update-role', userId, event.target.value)
  } catch (error) {
    route.push({ name: '404' })
  }
}

async function onSetPage(pageNo) {
  try {
    await getAllData(api, pageNo - 1)
    active.value = pageNo
  } catch (err) {
    route.push({ name: '404' })
  }
}

function onPreviousPage() {
  try {
    if (active.value <= api.totalPages && active.value > 1) {
      active.value -= 1
    }
    getAllData(api, active.value - 1)
  } catch (err) {
    route.push({ name: '404' })
  }
}

function onNextPage() {
  try {
    if (active.value < api.totalPages) {
      active.value += 1
    }
    getAllData(api, active.value - 1)
  } catch (err) {
    route.push({ name: '404' })
  }
}

async function search() {
  try {
    let page = {
      pageNo: searchData.pageNo,
      pageSize: 10,
      sortDirection: searchData.sortBy === 'created_date' ? 'DESC' : searchData.sortDirection,
      sortBy: searchData.sortBy,
      value: searchData.searchValue
    }
    const res = await UserService.getAllPage('/get-all', page)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
    console.log(api)
  } catch (error) {
    console.log(error)
  }
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
