<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <CommonAdmin title="Quyền" page="5" actionNew="false">
    <template v-slot:thead>
      <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
        <tr class="rounded-tl-md">
          <th>Số thứ tự.</th>
          <th>Tên quyền</th>
          <th>Mô tả về quyền</th>
          <th>Ngày tạo mới</th>
          <th>Ngày cập nhật</th>
          <th>Trạng thái</th>
          <th class="w-[150px]">Action</th>
        </tr>
      </thead>
    </template>
    <template v-slot:tbody>
      <tbody>
        <tr v-for="(item, index) in roleList" :key="item.roleId">
          <td>{{ index + 1 }}</td>
          <td>{{ item.roleName }}</td>
          <td>{{ item.roleDescription }}</td>
          <td>{{ new Date(item.createdDate).toLocaleString() }}</td>
          <td>{{ new Date(item.updatedDate).toLocaleString() }}</td>
          <td>{{ item.status === true ? 'Hoạt động' : 'Không hoạt động' }}</td>
          <td class="flex items-center justify-around gap-2">
            <router-link to="/">
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
import CommonAdmin from '@/components/common/CommonAdmin.vue'
import UserService from '@/stores/modules/UserService'
import { onMounted, ref } from 'vue'
const roleList = ref({})
onMounted(async () => {
  await getAllData(roleList)
})

async function getAllData(roleList) {
  try {
    await UserService.getRole().then((res) => (roleList.value = res.data))

    console.log(roleList)
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
