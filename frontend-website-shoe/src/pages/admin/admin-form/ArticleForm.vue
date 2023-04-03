<template lang="">
  <div class="relative h-[100vh]">
    <div>
      <div class="text-white">
        <router-link to="/">
          <Button
            type="button"
            text="< Back"
            id="addCategory"
            className="bg-green-700 hover:bg-green-600 -ml-[2px] text-white font-bold"
          />
        </router-link>
      </div>
      <strong
        class="block w-full py-2 my-3 text-xl text-center uppercase rounded-md bg-[#0c3247] text-[#17b1ea]"
        >Add new article</strong
      >
      <!-- {{ param == null ? 'Add new product' : 'Edit product' }} -->
      <form
        class="w-full post"
        @submit.prevent="onSubmit"
        method="post"
        enctype="multipart/form-data"
        name="articleForm"
      >
        <!-- Form Payment -->
        <div class="w-full">
          <!-- Form full name. -->
          <div class="mb-4">
            <span class="text-base">Article name</span>
            <Input
              type="text"
              name="categoryName"
              v-model="state.articleName"
              placeholder="Name..."
              classChild="mt-2"
            />
          </div>
          <!-- Form article description. -->
          <div class="mb-3">
            <span class="text-base">Article description</span>
            <div class="bg-white">
              <quill-editor
                v-model:content="state.articleDescription"
                contentType="html"
                theme="snow"
              ></quill-editor>
            </div>
          </div>
          <!-- Form upload image. -->
          <div class="mb-3">
            <span class="text-base">Upload file</span>
            <div class="">
              <label for="file" class="grid grid-cols-8 mt-2">
                <input
                  type="text"
                  class="col-span-7 rounded-tr-none rounded-br-none disabled:bg-white max-h-[40px]"
                  name="fileName"
                  for="file"
                  disabled
                  :value="fileName"
                  placeholder="Choose file..."
                />
                <span
                  class="text-[#17b1ea] block text-center rounded-tl-none rounded-bl-none col-span-1 rounded cursor-pointer bg-[#0c3247] py-2 px-3"
                  >Upload file</span
                >
              </label>
              <input @change="onChangeFile($event)" type="file" id="file" class="hidden w-full" />
            </div>
          </div>
        </div>
        <!-- Form address. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3">
            <span class="text-base">Status</span>
            <select
              name="categoryStatus"
              class="w-full p-2 mt-1 rounded-sm outline-none"
              id="status"
              @change="switchSelectStatus(event)"
            >
              <option value="1" selected>Active</option>
              <option value="0">In-Active</option>
            </select>
          </div>
        </div>
        <!-- Button submit order. -->
        <Button
          type="submit"
          className="bg-brown hover:bg-brown-hover text-white w-full m-0 mt-3"
          name="login"
          text="Register"
        />
      </form>
    </div>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import axios from 'axios'
import { API_ARTICLE_POST } from '@/stores/api'
const state = reactive({
  articleId: 1,
  articleName: '',
  articleDescription: '',
  productId: 1,
  articleStatus: '1'
})
const fileName = ref('')
const file = ref()
const onChangeFile = (event) => {
  file.value = event.target.files[0]
  fileName.value = event.target.files[0].name
}
const switchSelectStatus = (event) => {
  state.articleStatus = event.target.value
}
async function onSubmit() {
  try {
    const formData = new FormData()
    formData.append('file', file.value)
    formData.append('article', JSON.stringify(state))
    await apiPost(formData)
    state.articleName = ''
    state.articleDescription = ''
    state.articleStatus = '1'
    fileName.value = ''
  } catch (e) {
    console.log(e)
  }
}
async function apiPost(formData) {
  axios
    .post(API_ARTICLE_POST, formData, { headers: { 'Content-Type': 'multipart/form-data' } })
    .then((response) => {
      console.log('Success: ' + response)
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
.title p,
.title input {
  border-right: 2px #17b1ea solid;
}
.title p:first-child {
  border-left: 2px #17b1ea solid;
}
</style>
