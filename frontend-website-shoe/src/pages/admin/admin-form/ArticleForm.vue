<template lang="">
  <div class="relative min-h-[100vh]">
    <div>
      <div class="text-white">
        <router-link :to="{ name: 'ArticleAdmin' }">
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
        >{{ pathName == 'ArticleRegisterAdmin' ? 'Thêm bài viết mới' : 'Sửa bài viết' }}</strong
      >
      <!-- {{ param == null ? 'Add new product' : 'Edit product' }} -->
      <form
        class="w-full post"
        @submit.prevent="onSubmitForm(pathName)"
        method="post"
        enctype="multipart/form-data"
        name="articleForm"
      >
        <!-- Form Payment -->
        <div class="w-full">
          <!-- Form full name. -->
          <div class="mb-4">
            <span class="text-base">Tên bài viết</span>
            <Input
              type="text"
              name="categoryName"
              v-model="state.articleName"
              placeholder="Tên bài viết..."
              classChild="mt-2"
            />
          </div>
          <!-- Form article description. -->
          <div class="mb-3">
            <span class="text-base">Mô tả chi tiết bài viết</span>
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
            <span class="text-base">Ảnh hiển thị</span>
            <div class="">
              <label for="file" class="grid grid-cols-8 mt-2">
                <input
                  type="text"
                  class="col-span-7 rounded-tr-none rounded-br-none disabled:bg-white max-h-[40px]"
                  name="fileName"
                  for="file"
                  disabled
                  :value="fileName"
                  placeholder="Chọn ảnh..."
                />
                <span
                  class="text-[#17b1ea] block text-center rounded-tl-none rounded-bl-none col-span-1 rounded cursor-pointer bg-[#0c3247] py-2 px-3"
                  >Chọn ảnh</span
                >
              </label>
              <input @change="onChangeFile($event)" type="file" id="file" class="hidden w-full" />
            </div>
          </div>
        </div>
        <!-- Form address. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3">
            <span class="text-base">Trạng thái</span>
            <select
              name="categoryStatus"
              class="w-full p-2 mt-1 rounded-sm outline-none"
              id="status"
              @change="switchSelectStatus(event)"
            >
              <option value="1" selected>Hoạt động</option>
              <option value="0">Không hoạt động</option>
            </select>
          </div>
        </div>
        <!-- Button submit order. -->
        <Button
          type="submit"
          className="bg-brown hover:bg-brown-hover text-white w-full m-0 mt-3"
          name="login"
          :text="pathName == 'ArticleRegisterAdmin' ? 'Thêm' : 'Cập nhật'"
        />
      </form>
    </div>
  </div>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import { useRoute, useRouter } from 'vue-router'
import ArticleService from '@/stores/modules/ArticleService'
import { INSERT, UPDATE, ARTICLE_NEW, ARTICLE_EDIT } from '../../../constants/index'
const state = ref({
  articleId: 1,
  articleName: '',
  articleDescription: '',
  productId: 1,
  articleStatus: '1'
})
const form = reactive({ ...state.value })
const fileName = ref('')
const file = ref()
const onChangeFile = (event) => {
  file.value = event.target.files[0]
  fileName.value = event.target.files[0].name
}
const pathName = useRoute().matched[0].name
const router = useRouter()
onMounted(() => {
  if (pathName === 'ArticleUpdateAdmin') {
    getById(useRoute().params.articleId)
  }
})
const switchSelectStatus = (event) => {
  state.value.articleStatus = event.target.value
}

async function onSubmitForm(pathName) {
  try {
    if (ARTICLE_NEW === pathName) {
      ArticleService.insertOrUpdate(file.value, state.value, '/register', INSERT)
    }

    if (ARTICLE_EDIT === pathName) {
      ArticleService.insertOrUpdate(file.value, state.value, '/update', UPDATE)
    }
    router.push({ name: 'ArticleAdmin' })
    Object.assign(state.value, form)
  } catch (e) {
    console.log(e)
  }
}
async function getById(articleId) {
  try {
    const res = await ArticleService.getById('/init', articleId)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }
    state.value = result.data
    fileName.value = result.data.fileName
  } catch (error) {
    fortmatResponse(error.response?.data) || error
  }
}
function fortmatResponse(res) {
  return JSON.stringify(res, null, 2)
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
