<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Article" :page="api.totalPages" actionNew="ArticleRegisterAdmin">
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
          <td>{{ item.createdDate }}</td>
          <td>{{ item.updatedDate }}</td>
          <td>{{ item.articleStatus }}</td>
          <td class="flex items-center justify-around gap-2">
            <router-link to="/">
              <button
                className="min-w-[60px] px-2 text-sm bg-green-700 hover:bg-green-600 block text-center m-0 hover:text-white"
                name="edit"
                :to="{ name: 'ArticleUpdateAdmin', params: { articleId: item.articleId } }"
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
  </CommonAdmin>
</template>
<script setup>
import { onMounted, reactive } from 'vue'
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

onMounted(async () => {
  getAllData(api)
  console.log(api)
})

async function getAllData(api) {
  try {
    const res = await ArticleService.getAll('/init/pageable')

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    fetchData(result.data, api)
    console.log(api)
  } catch (error) {
    api = fortmatResponse(error.response?.data) || error
    console.log(api)
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

    result.deleteResult = fortmatResponse(result)
  } catch (err) {
    result.deleteResult = fortmatResponse(err.response?.data) || err
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

function fortmatResponse(res) {
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
