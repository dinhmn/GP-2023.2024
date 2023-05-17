<template lang="">
  <div class="min-h-[100vh]">
    <div class="text-white">
      <router-link :to="{ name: 'RoleAdmin' }">
        <Button
          type="button"
          text="< Back"
          id="addRole"
          className="bg-green-700 hover:bg-green-600 -ml-[2px] text-white font-bold"
        />
      </router-link>
    </div>
    <strong
      class="block w-full py-2 my-3 text-xl text-center uppercase rounded-md bg-[#0c3247] text-[#17b1ea]"
      >{{ pathName == 'RoleRegisterAdmin' ? 'Add role' : 'Edit role' }}</strong
    >
    <form class="w-full post" method="post">
      <!-- Form Payment -->
      <div class="w-full">
        <!-- Form full name. -->
        <div class="mb-4">
          <span class="text-base">Role name</span>
          <Input
            type="text"
            v-model="role.roleName"
            name="roleName"
            placeholder="Tên"
            classChild="mt-2"
          />
        </div>
        <!-- Form note detail. -->
        <div class="mb-3">
          <span class="text-base">Role description</span>
          <Textarea
            name="roleDescription"
            v-model="role.roleDescription"
            placeholder="Mô tả phạm vi quản lí"
          />
        </div>
      </div>
      <!-- Form address. -->
      <div class="flex items-center justify-between w-full gap-2">
        <div class="w-full mb-3">
          <span class="text-base">Status</span>
          <select
            name="status"
            v-model="role.status"
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
        :text="pathName == 'RoleRegisterAdmin' ? 'Register' : 'Update'"
      />
    </form>
  </div>
</template>
<script setup>
import { reactive } from 'vue'
import { useRoute } from 'vue-router'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Textarea from '@/components/common/input/Textarea.vue'
const route = useRoute()
const role = reactive({
  roleName: '',
  roleDescription: '',
  status: '1'
})

const switchSelectStatus = (event) => {
  role.status = event.target.value
}

const pathName = route.matched[0].name
// if (pathName === CATEGORY_EDIT) {
//   apiGetByPrimaryKey(param.trademarkId, param.categoryId)
// }
// let onSubmit = () => {
//   if (pathName === CATEGORY_EDIT) {
//     apiUpdate(category, param.trademarkId, param.categoryId)
//   } else {
//     apiPost(category)
//     category.trademarkId = ''
//     category.categoryName = ''
//     category.categoryDescription = ''
//     category.categoryStatus = ''
//   }
// }

// function apiPost(category) {
//   axios
//     .post(API_CATEGORY_POST, category)
//     .then((response) => {
//       console.log('Success: ' + response)
//     })
//     .catch((error) => {
//       console.log('Error: ' + error)
//     })
// }

// function apiUpdate(category, trademarkId, categoryId) {
//   axios
//     .put(API_CATEGORY_UPDATE + trademarkId + '/' + categoryId, category)
//     .then((response) => {
//       console.log(response.data.status)
//       category.categoryName = response.data.categoryName
//       category.categoryDescription = response.data.categoryDescription
//       category.categoryStatus = response.data.status == true ? '1' : '0'
//       category.trademarkId = response.data.trademarkId
//     })
//     .catch((error) => {
//       console.log('Error: ' + error)
//     })
// }

// function apiGetByPrimaryKey(trademarkId, categoryId) {
//   axios
//     .get(API_CATEGORY_BY_KEY + trademarkId + '/' + categoryId)
//     .then((response) => {
//       category.categoryName = response.data.categoryName
//       category.categoryDescription = response.data.categoryDescription
//       category.categoryStatus = response.data.categoryStatus == true ? '1' : '0'
//       category.trademarkId = response.data.trademarkId
//     })
//     .catch((error) => {
//       console.log('Error: ' + error)
//     })
// }
</script>
<style lang="css" scoped>
span {
  color: white;
}
</style>
