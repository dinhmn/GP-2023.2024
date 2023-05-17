<template lang="">
  <div class="min-h-[100vh]">
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
      >Update role of user</strong
    >
    <form class="w-full post" method="post">
      <!-- Form Payment -->
      <div class="w-full">
        <!-- Form user name -->
        <div class="mb-4">
          <span class="text-base">User name</span>
          <Input type="text" v-model="user.username" name="username" classChild="mt-2" disabled />
        </div>
        <!-- Form city and country. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3">
            <span class="text-base">Role</span>
            <select
              name="trademarkId"
              class="w-full p-2 mt-1 rounded-sm outline-none"
              id="trademarkId"
              v-model="user.roleId"
              @change="switchSelectRole($event)"
            >
              <option value="1">Admin</option>
              <option value="2">User</option>
              <option value="3">Manager</option>
            </select>
          </div>
        </div>
        <!-- Form address. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3">
            <span class="text-base">Status</span>
            <select
              name="categoryStatus"
              v-model="user.status"
              class="w-full p-2 mt-1 rounded-sm outline-none"
              id="status"
              @change="switchSelectStatus($event)"
            >
              <option value="1" selected>Active</option>
              <option value="0">In-Active</option>
            </select>
          </div>
        </div>
        <div class="grid w-full grid-cols-2 mt-4">
          <div
            class="col-span-1 flex items-center justify-center gap-5 w-[700px] bg-[#0c3247] px-2 py-4"
          >
            <div>
              <img
                src="../../../assets/images/default.png"
                class="object-cover w-[250px] h-full"
                alt=""
              />
            </div>
            <table class="w-full text-white">
              <tr>
                <td>Full name</td>
                <td>xxxx</td>
              </tr>
              <tr>
                <td>Phone</td>
                <td>xxxx</td>
              </tr>
              <tr>
                <td>Email</td>
                <td>xxxx</td>
              </tr>
              <tr>
                <td>Address</td>
                <td>xxxx</td>
              </tr>
              <tr>
                <td>Age</td>
                <td>18</td>
              </tr>
            </table>
          </div>
          <div class="w-full col-span-1 bg-[#0c3247] px-2 py-4">
            <table class="w-full text-white">
              <tr>
                <td>Order</td>
                <td>xxxx</td>
              </tr>
              <tr>
                <td>Quantity</td>
                <td>xxxx</td>
              </tr>
              <tr>
                <td>Price</td>
                <td>xxxx</td>
              </tr>
            </table>
          </div>
        </div>
        <!-- Button submit order. -->
        <Button
          @click.prevent="onSubmit"
          type="button"
          className="bg-brown hover:bg-brown-hover text-white w-full m-0 mt-3"
          name="login"
          text="Update"
        />
      </div>
    </form>
  </div>
</template>
<script setup>
import { reactive } from 'vue'
import Button from '@/components/common/button/Button.vue'
import Input from '@/components/common/input/Input.vue'
const user = reactive({
  roleId: '1',
  status: '1',
  username: 'Itachi'
})

const switchSelectRole = (event) => {
  user.roleId = event.target.value
}
const switchSelectStatus = (event) => {
  user.status = event.target.value
}
</script>
<style lang="css" scoped>
span {
  color: white;
}
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
  text-align: left;
}

table tbody tr {
  border-bottom: 2px solid #464b53;
}
table tbody tr td {
  padding: 5px;
}
</style>
