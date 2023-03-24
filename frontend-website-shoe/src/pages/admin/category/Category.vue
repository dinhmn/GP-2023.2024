<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="w-full text-white">
    <h2 class="w-full my-2 mb-4 font-bold">
      <span class="text-xl">Dashboard</span> <strong> > </strong>
      <span class="text-[#17b1ea] text-xl">Category</span>
    </h2>
    <div class="flex items-center justify-between p-4 bg-[#1a1e30] rounded-md">
      <div class="flex w-[600px] gap-2 items-center">
        <Input
          type="text"
          placeholder="Search..."
          name="search"
          id="search"
          classChild="min-w-[200px] py-[6px] rounded-sm max-w-[400px] max-h-[40px]"
        />
        <Button
          type="button"
          text="Search"
          id="search"
          className="bg-[#0c3247] text-[#17b1ea] hover:bg-[#10405a] hover:text-white"
        />
      </div>
      <div>
        <router-link :to="{ name: 'CategoryRegisterAdmin', params: {} }">
          <Button
            type="button"
            text="New Category"
            id="addCategory"
            className="bg-[#0c3247] text-[#17b1ea] hover:bg-[#10405a] hover:text-white"
          />
        </router-link>
      </div>
    </div>
    <div class="w-full mt-5 rounded-md p-4 bg-[#1a1e30]">
      <table class="w-full">
        <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
          <tr class="rounded-tl-md">
            <th>No.</th>
            <th>ID</th>
            <th>Category Name</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="item in count" :key="item">
            <td>1</td>
            <td>1</td>
            <td>SB-ML</td>
            <td>2022-09-05 02:32:05</td>
            <td>2023-11-04 07:59:54</td>
            <td>True</td>
            <td class="flex items-center justify-around gap-2">
              <router-link :to="{ name: 'CategoryEditAdminParam', params: { id: item } }">
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
      </table>
    </div>
    <div class="w-full mt-3 text-right">
      <ul class="flex items-center justify-end gap-1">
        <li>Prev</li>
        <li class="active">1</li>
        <li>2</li>
        <li>3</li>
        <li>Next</li>
      </ul>
    </div>
  </div>
</template>
<script setup>
import Button from '@/components/common/button/Button.vue'
import Input from '@/components/common/input/Input.vue'
const count = 5
</script>
<style lang="css" scoped>
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

tbody tr:nth-child(2n + 1) {
}
tr th {
  padding: 10px 0;
}
thead {
  line-height: 30px;
  font-weight: bold;
}
th:first-child,
th:nth-child(2) {
  width: 100px;
}
th:nth-child(3) {
  width: 500px;
}
th:nth-child(4),
th:nth-child(5) {
  width: 200px;
}
th:nth-child(6) {
  width: 150px;
}
</style>
