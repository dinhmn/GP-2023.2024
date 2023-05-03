<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Article" actionNew="ArticleRegisterAdmin">
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
          <th>Article name</th>
          <th>Article description</th>
          <th>Created date</th>
          <th>Updated date</th>
          <th>Status</th>
          <th class="w-[150px]">Action</th>
        </tr>
      </thead>
    </template>
    <template v-slot:tbody>
      <tbody>
        <tr v-for="(item, index) in api.data" :key="item.articleId">
          <td>{{ index }}</td>
          <td>{{ item.articleName }}</td>
          <td>{{ item.articleDescription }}</td>
          <td>{{ new Date(item.createdDate).toLocaleDateString() }}</td>
          <td>{{ new Date(item.updatedDate).toLocaleDateString() }}</td>
          <td>{{ item.articleStatus }}</td>
          <td class="flex items-center justify-around gap-2">
            <router-link
              :to="{ name: 'ArticleUpdateAdmin', params: { articleId: item.articleId } }"
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
              @click="onDelete($event, item.articleId)"
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
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import CommonAdmin from '@/components/common/CommonAdmin.vue'
import ArticleService from '@/stores/modules/ArticleService'
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
const searchData = reactive({
  pageNo: 0,
  pageSize: 10,
  sortDirection: 'ASC',
  sortBy: 'article_id',
  searchValue: ''
})
onMounted(async () => {
  await getAllData(api, 0)
})

async function getAllData(api, pageNo) {
  try {
    const res = await ArticleService.getAll('/init/pageable', pageNo)

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

async function onDelete(event, articleId) {
  try {
    const res = await ArticleService.delete(`/delete/${articleId}`)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    result.deleteResult = formatResponse(result)
    await getAllData(api)
  } catch (err) {
    result.deleteResult = formatResponse(err.response?.data) || err
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
    const res = await ArticleService.getAllInit('/init', searchData)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
    api.data = result.data.articleEntityList
  } catch (err) {
    result.deleteResult = formatResponse(err.response?.data) || err
  }
}

function fetchData(result, api) {
  api.data = result.articleDTOList
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
