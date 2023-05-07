<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage class="text-white">
    <template v-slot:body>
      <div
        class="flex items-center justify-between w-full px-3 py-3 mb-2 text-cyan-800 rounded-[2px] bg-[#F8F8F8]"
      >
        <div class="flex items-center justify-between gap-2 text-xl font-bold">
          <h3>Trang chủ</h3>
          <vue-feather type="chevron-right"></vue-feather>
          <h3>Tin tức</h3>
        </div>
      </div>
      <div>
        <div class="w-full p-3 text-center bg-[#F4F4F4]">
          <h3 class="text-[#17b1ea] w-full font-bold text-2xl">{{ data.articleName }}</h3>
        </div>
        <div
          class="w-full gap-2 p-4 rounded-[2px] bg-[#F8F8F8] text-cyan-800"
          v-html="data.articleDescription"
        ></div>
      </div>
    </template>
  </BasePage>
</template>
<script setup>
import { onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import BasePage from '../auth/BasePage.vue'
import ArticleService from '@/stores/modules/ArticleService'
const route = useRoute()
const router = useRouter()
console.log(route.params.articleName)
const data = ref({})
onMounted(async () => {
  await fetchData(data)
})
const fetchData = async (data) => {
  try {
    const res = await ArticleService.getById('/init', route.params.articleId)
    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }
    data.value = result.data
    console.log(result.data)
  } catch (error) {
    router.push({ name: '404' })
  }
}
</script>
<style lang=""></style>
