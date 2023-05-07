<template lang="">
  <div class="min-h-screen">
    <div class="text-white">
      <router-link :to="{ name: 'CategoryAdmin' }">
        <Button
          type="button"
          text="< Trở lại"
          id="addCategory"
          className="bg-green-700 hover:bg-green-600 -ml-[2px] text-white font-bold"
        />
      </router-link>
    </div>
    <strong
      class="block w-full py-2 my-3 text-xl text-center uppercase rounded-md bg-[#0c3247] text-[#17b1ea]"
      >{{ param !== null ? 'Thêm hãng mới' : 'Sửa hãng sản xuất' }}</strong
    >
    <form class="w-full post" method="post">
      <!-- Form Payment -->
      <div class="w-full">
        <!-- Form city and country. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3">
            <span class="text-base">Tên nhà cung cấp</span>
            <select
              name="trademarkId"
              class="w-full p-2 mt-1 rounded-sm outline-none"
              id="trademarkId"
              v-model="category.trademarkId"
              @change="switchSelectTrademark($event)"
            >
              <option
                :value="item.trademarkId"
                v-for="item in trademarkList"
                :key="item.trademarkId"
              >
                {{ item.trademarkName }}
              </option>
            </select>
          </div>
        </div>
        <!-- Form full name. -->
        <div class="mb-4">
          <span class="text-base">Tên hãng sản xuất</span>
          <Input
            type="text"
            v-model="category.categoryName"
            name="categoryName"
            placeholder="Tên hãng sản xuất"
            classChild="mt-2"
          />
        </div>
        <!-- Form note detail. -->
        <div class="mb-3">
          <span class="text-base">Mô tả hãng sản xuất</span>
          <div class="mt-2 bg-white">
            <quill-editor
              name="categoryDescription"
              v-model:content="category.categoryDescription"
              contentType="html"
              theme="snow"
            ></quill-editor>
          </div>
        </div>
      </div>
      <!-- Form address. -->
      <div class="flex items-center justify-between w-full gap-2">
        <div class="w-full mb-3">
          <span class="text-base">Trạng thái</span>
          <select
            name="categoryStatus"
            v-model="category.categoryStatus"
            class="w-full p-2 mt-1 rounded-sm outline-none"
            id="status"
            @change="switchSelectStatus($event)"
          >
            <option value="1">Hoạt động</option>
            <option value="0">Không hoạt động</option>
          </select>
        </div>
      </div>
      <!-- Button submit order. -->
      <Button
        @click.prevent="onSubmit"
        type="button"
        className="bg-brown hover:bg-brown-hover text-white w-full m-0 mt-3"
        name="login"
        :text="param !== null ? 'Thêm mới' : 'Cập nhật'"
      />
    </form>
  </div>
</template>
<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import { CATEGORY_EDIT } from '@/constants/index'
import axios from 'axios'
import {
  API_CATEGORY_POST,
  API_CATEGORY_BY_KEY,
  API_CATEGORY_UPDATE,
  API_TRADEMARK_GET
} from '@/stores/api'
const route = useRoute()
const category = reactive({
  trademarkId: 1,
  categoryName: '',
  categoryDescription: '',
  categoryStatus: '1'
})
const trademarkList = ref({})
onMounted(() => {
  apiGetTrademark(trademarkList)
  console.log(trademarkList)
})

const switchSelectTrademark = (event) => {
  category.trademarkId = event.target.value
}
const switchSelectStatus = (event) => {
  category.categoryStatus = event.target.value
}

const pathName = route.matched[0].name
const param = route.params
if (pathName === CATEGORY_EDIT) {
  apiGetByPrimaryKey(param.trademarkId, param.categoryId)
}
let onSubmit = () => {
  if (pathName === CATEGORY_EDIT) {
    apiUpdate(category, param.trademarkId, param.categoryId)
  } else {
    apiPost(category)
    category.trademarkId = ''
    category.categoryName = ''
    category.categoryDescription = ''
    category.categoryStatus = ''
  }
}

function apiGetTrademark(trademarkList) {
  axios
    .get(API_TRADEMARK_GET)
    .then((response) => {
      trademarkList.value = response.data
    })
    .catch((error) => {
      console.log('Error: ' + error)
    })
}

function apiPost(category) {
  axios
    .post(API_CATEGORY_POST, category)
    .then((response) => {
      console.log('Success: ' + response)
    })
    .catch((error) => {
      console.log('Error: ' + error)
    })
}

function apiUpdate(category, trademarkId, categoryId) {
  axios
    .put(API_CATEGORY_UPDATE + trademarkId + '/' + categoryId, category)
    .then((response) => {
      console.log(response.data.status)
      category.categoryName = response.data.categoryName
      category.categoryDescription = response.data.categoryDescription
      category.categoryStatus = response.data.status == true ? '1' : '0'
      category.trademarkId = response.data.trademarkId
    })
    .catch((error) => {
      console.log('Error: ' + error)
    })
}

function apiGetByPrimaryKey(trademarkId, categoryId) {
  axios
    .get(API_CATEGORY_BY_KEY + trademarkId + '/' + categoryId)
    .then((response) => {
      category.categoryName = response.data.categoryName
      category.categoryDescription = response.data.categoryDescription
      category.categoryStatus = response.data.categoryStatus == true ? '1' : '0'
      category.trademarkId = response.data.trademarkId
    })
    .catch((error) => {
      console.log('Error: ' + error)
    })
}
</script>
<style lang="css" scoped>
span {
  color: white;
}
</style>
