<template lang="">
  <div>
    <div class="text-white">
      <router-link :to="{ name: 'CategoryAdmin' }">
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
      >{{ param == null ? 'Add new category' : 'Edit category' }}</strong
    >
    <form class="w-full post">
      <!-- Form Payment -->
      <div class="w-full">
        <!-- Form city and country. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3">
            <span class="text-base">Trademark name</span>
            <select
              name="trademarkName"
              class="w-full p-2 mt-1 rounded-sm outline-none"
              id="trademark"
            >
              <option value="1">1</option>
              <option value="1">2</option>
            </select>
          </div>
        </div>
        <!-- Form full name. -->
        <div class="mb-4">
          <span class="text-base">Category name</span>
          <Input type="text" name="categoryName" placeholder="Họ tên của bạn" classChild="mt-2" />
        </div>
        <!-- Form note detail. -->
        <div class="mb-3">
          <span class="text-base">Category description</span>
          <Textarea name="categoryDescription" placeholder="Mô tả sản phẩm" />
        </div>
      </div>
      <!-- Form address. -->
      <div class="flex items-center justify-between w-full gap-2">
        <div class="w-full mb-3">
          <span class="text-base">Status</span>
          <select name="status" class="w-full p-2 mt-1 rounded-sm outline-none" id="status">
            <option value="1">Active</option>
            <option value="0">In-Active</option>
          </select>
        </div>
      </div>
      <!-- Button submit order. -->
      <Button
        @click.prevent="onSubmit"
        type="button"
        className="bg-brown hover:bg-brown-hover text-white w-full m-0 mt-3"
        name="login"
        :text="param == null ? 'Register' : 'Update'"
      />
    </form>
  </div>
</template>
<script setup>
import { useRoute } from 'vue-router'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Textarea from '@/components/common/input/Textarea.vue'
const route = useRoute()
const param = route.params.id
</script>
<style lang="css" scoped>
span {
  color: white;
}
</style>
