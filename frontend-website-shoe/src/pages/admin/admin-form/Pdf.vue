<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div class="h-[100vh]">
    <div class="text-white">
      <router-link :to="{ name: 'OrderAdmin' }">
        <Button
          type="button"
          text="< Back"
          id="addCategory"
          className="bg-green-700 hover:bg-green-600 -ml-[2px] text-white font-bold"
        />
      </router-link>
      <Button
        type="button"
        text="Send Mail"
        id="sendMail"
        className="bg-green-700 hover:bg-green-600 -ml-[2px] text-white font-bold"
      />
    </div>
    <div id="pdf" class="text-white">
      <div class="block w-full py-2 text-2xl font-bold text-center text-white">INVOICE</div>
      <div class="py-3 customer-bill">
        <table>
          <tr>
            <td>Customer name:</td>
            <td></td>
          </tr>
          <tr>
            <td>Address:</td>
            <td></td>
          </tr>
          <tr>
            <td>Phone number:</td>
            <td></td>
          </tr>
          <tr>
            <td>Email:</td>
            <td></td>
          </tr>
          <tr>
            <td>Bill no:</td>
            <td></td>
          </tr>
          <tr>
            <td>Total payment:</td>
            <td></td>
          </tr>
          <tr>
            <td>Bill no:</td>
            <td></td>
          </tr>
          <tr>
            <td>Created date:</td>
            <td></td>
          </tr>
        </table>
      </div>
      <div>
        <table class="w-full text-center">
          <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
            <tr class="rounded-tl-md">
              <td>Product Name</td>
              <td>Unit Price</td>
              <td>Quantity</td>
              <td>Tota</td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Product Name</td>
              <td>Unit Price</td>
              <td>Quantity</td>
              <td>Tota</td>
            </tr>
          </tbody>
          <tfoot>
            <tr>
              <td colspan="3">Order Total</td>
              <td>Total</td>
            </tr>
          </tfoot>
        </table>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import Button from '@/components/common/button/Button.vue'
import { useRoute } from 'vue-router'
const route = useRoute()
const data = ref({})
onMounted(() => {
  axios
    .get('http://localhost:8088/api/order/get/' + route.params.orderId)
    .then((response) => {
      data.value = response.data
      console.log(data)
    })
    .catch((error) => console.log(error))
})

// const sendMail = (event, item) => {
//   let requestModel = {
//     mailName: 'Thank you has set the row.',
//     // mailTo: item.customerEmail,
//     mailTo: 'ngocdinh11052001@gmail.com',
//     mailFrom: 'ngocdinh2k1@gmail.com',
//     mailSubject: `Hello ${
//       item.customerLastName !== null ? item.customerLastName : item.customerFirstName
//     }, Confirm your order`
//   }
//   axios
//     .post('http://localhost:8088/api/send-mail/post/' + item.orderId, requestModel)
//     .then((response) => console.log(response))
//     .catch((error) => console.log(error))
// }
</script>
<style lang="css" scoped>
span {
  color: white;
}
.customer-bill table tr td:first-child {
  width: 200px;
}
table,
tr,
td,
th {
  padding: 5px 10px;
}

table:nth-child(2) tbody tr {
  border-bottom: 2px solid #464b53;
}
table:nth-child(2) tbody tr td {
  padding: 5px;
}
</style>
