<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="w-full text-white">
    <h2 class="text-2xl font-bold uppercase text-brown">Category</h2>
    <div class="flex items-center justify-between">
      <div class="flex w-[600px] gap-2 items-center">
        <Input
          type="text"
          placeholder="Search..."
          name="search"
          id="search"
          classChild="min-w-[200px] py-1 max-w-[400px] max-h-[40px]"
        />
        <Button
          type="button"
          text="Search"
          id="search"
          className="bg-green-700 hover:bg-green-600"
        />
      </div>
      <div>
        <router-link :to="{ name: 'CategoryRegisterAdmin', params: {} }">
          <Button
            type="button"
            text="New Category"
            id="addCategory"
            className="bg-green-700 hover:bg-green-600"
          />
        </router-link>
      </div>
    </div>
    <div class="w-full mt-5">
      <table clss="w-full">
        <thead class="w-full bg-brown-hover">
          <tr class="rounded-tl-md">
            <th>STT</th>
            <th>ID</th>
            <th>Category Name</th>
            <th>Created Date</th>
            <th>Updated Date</th>
            <th>Status</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th>1</th>
            <th>1</th>
            <th>SB-ML</th>
            <th>2022-09-05 02:32:05</th>
            <th>2023-11-04 07:59:54</th>
            <th>True</th>
            <th class="flex items-center justify-around gap-2">
              <button
                className="min-w-[60px] px-2 text-sm bg-green-700 hover:bg-green-600 block text-center m-0 hover:text-white"
                name="eidt"
              >
                Edit
              </button>
              <button
                class="block min-w-[60px] px-2 m-0 text-sm text-center bg-red-700 hover:bg-red-600 mr-3"
                name="delete"
              >
                Delete
              </button>
            </th>
          </tr>
          <tr>
            <th>1</th>
            <th>1</th>
            <th>SB-ML</th>
            <th>2022-09-05 02:32:05</th>
            <th>2023-11-04 07:59:54</th>
            <th>True</th>
            <th class="flex items-center justify-around gap-2">
              <button
                className="min-w-[60px] px-2 text-sm bg-green-700 hover:bg-green-600 block text-center m-0 hover:text-white"
                name="eidt"
              >
                Edit
              </button>
              <button
                class="block min-w-[60px] px-2 m-0 text-sm text-center bg-red-700 hover:bg-red-600 mr-3"
                name="delete"
              >
                Delete
              </button>
            </th>
          </tr>
          <tr>
            <th>1</th>
            <th>1</th>
            <th>SB-ML</th>
            <th>2022-09-05 02:32:05</th>
            <th>2023-11-04 07:59:54</th>
            <th>True</th>
            <th class="flex items-center justify-around gap-2">
              <button
                className="min-w-[60px] px-2 text-sm bg-green-700 hover:bg-green-600 block text-center m-0 hover:text-white"
                name="eidt"
              >
                Edit
              </button>
              <button
                class="block min-w-[60px] px-2 m-0 text-sm text-center bg-red-700 hover:bg-red-600 mr-3"
                name="delete"
              >
                Delete
              </button>
            </th>
          </tr>
          <tr>
            <th>1</th>
            <th>1</th>
            <th>SB-ML</th>
            <th>2022-09-05 02:32:05</th>
            <th>2023-11-04 07:59:54</th>
            <th>True</th>
            <th class="flex items-center justify-around gap-2">
              <button
                className="min-w-[60px] px-2 text-sm bg-green-700 hover:bg-green-600 block text-center m-0 hover:text-white"
                name="eidt"
              >
                Edit
              </button>
              <button
                class="block min-w-[60px] px-2 m-0 text-sm text-center bg-red-700 hover:bg-red-600 mr-3"
                name="delete"
              >
                Delete
              </button>
            </th>
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
</script>
<style lang="css" scoped>
ul li {
  padding: 4px 20px;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 3px;
}
ul li.active {
  background-color: slategray;
}
table,
tr,
td,
th {
  padding: 5px 10px;
}
tbody tr:nth-child(2n + 1) {
  background-color: #6b7280;
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
