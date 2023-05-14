<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <div v-if="loading" class="h-[100vh] flex justify-center top-1/2">
    <div class="lds-facebook">
      <div></div>
      <div></div>
      <div></div>
    </div>
  </div>

  <div v-else class="h-[100vh]">
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
        @click="sendMail"
        className="bg-green-700 hover:bg-green-600 -ml-[2px] text-white font-bold"
      />
    </div>
    <div class="relative">
      <div
        class="absolute w-[400px] h-[150px] bg-white z-99 top-[30%] left-[40%] p-5 text-center rounded-md shadow-md flex flex-col items-center justify-between"
        :class="flagMail !== 200 ? 'hidden' : ''"
      >
        <div><vue-feather class="w-16 h-16 text-green-600" type="check-circle"></vue-feather></div>
        <div class="">Gửi mail xác nhận thành công!</div>
      </div>
      <div id="pdf" class="text-white bg-[#1a1e30] p-3 rounded mt-5">
        <div class="block w-full py-2 text-2xl font-bold text-center text-white">HÓA ĐƠN</div>
        <div class="block w-full py-2 text-lg font-bold text-center text-white">
          {{ data.customer.customerOrderCode }}
        </div>
        <div class="py-3 customer-bill">
          <table class="information-customer">
            <tr>
              <td>Họ và tên khách hàng:</td>
              <td>
                {{ data.customer.customerLastName + ' ' + data.customer.customerFirstName }}
              </td>
            </tr>
            <tr>
              <td>Địa chỉ nhận hàng:</td>
              <td>
                {{ data.customer.customerAddress }}
              </td>
            </tr>
            <tr>
              <td>Số điện thoại:</td>
              <td>{{ data.customer.customerPhone }}</td>
            </tr>
            <tr>
              <td>Email:</td>
              <td>{{ data.customer.customerEmail }}</td>
            </tr>
            <tr>
              <td>Mã hóa đơn:</td>
              <td>{{ data.customer.customerOrderCode }}</td>
            </tr>
            <tr>
              <td>Tổng tiền thanh toán:</td>
              <td>
                {{ formatPrice(data.totalPriceOfAllProduct.replace('.0 đ', '')) + ' đ' }}
              </td>
            </tr>
            <tr>
              <td>Ngày mua hàng:</td>
              <td>{{ data.customer.createdDate }}</td>
            </tr>
          </table>
        </div>
        <div>
          <table class="w-full text-center">
            <thead class="w-full bg-[#0c3247] text-[#17b1ea]">
              <tr class="rounded-tl-md">
                <td>Tên sản phẩm</td>
                <td>Giá sản phẩm</td>
                <td>Số lượng</td>
                <td>Thành tiền</td>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(product, index) in data.orderList" :key="index">
                <td>{{ product.productName }}</td>
                <td>{{ formatPrice(product.productPrice) + ' đ' }}</td>
                <td>{{ product.productQuantity }}</td>
                <td>{{ formatPrice(product.productTotalPrice) + ' đ' }}</td>
              </tr>
            </tbody>
            <tfoot>
              <tr>
                <td colspan="3">Tổng tiền</td>
                <td>
                  {{ formatPrice(data.totalPriceOfAllProduct.replace('.0 đ', '')) + ' đ' }}
                </td>
              </tr>
            </tfoot>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import Button from '@/components/common/button/Button.vue'
import { useRoute } from 'vue-router'
import OrderService from '@/stores/modules/OrderService'
const route = useRoute()
const orderId = route.params.orderId
const data = ref({})
const loading = ref(true)
const flagMail = ref(404)
onMounted(async () => {
  await fetchDataOrder(data, orderId)
})

async function fetchDataOrder(data) {
  loading.value = true
  await OrderService.get('/get/' + orderId)
    .then((resp) => {
      data.value = resp.data
    })
    .finally(() => (loading.value = false))
  console.log(data.value)
}

async function sendMail() {
  loading.value = true
  let requestModel = {
    mailName: 'Thank you has set the row.',
    // mailTo: item.customerEmail,
    mailTo: data.value.customer.customerEmail,
    mailFrom: 'ngocdinh11052001@gmail.com',
    mailSubject: `Hello ${
      data.value.customer.customerLastName !== null
        ? data.value.customer.customerLastName
        : data.value.customer.customerFirstName
    }, Confirm your order`
  }
  axios
    .post('http://localhost:8088/api/send-mail/post/' + data.value.orderId, requestModel)
    .then((response) => {
      flagMail.value = response.status
      if (flagMail.value === 200) {
        setTimeout(() => {
          flagMail.value = 500
        }, 3000)
      }
    })
    .catch((error) => console.log(error))
    .finally(() => (loading.value = false))
}
function formatPrice(value) {
  let val = (value / 1).toFixed(0).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
}
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
  text-align: center;
}
.information-customer,
.information-customer tr,
.information-customer td,
.information-customer th {
  text-align: left;
}
.information-customer tr {
  display: flex;
  gap: 40px;
}
.information-customer td:first-child {
  border-right: 1px solid gray;
}
table tbody tr td,
table tfoot tr td {
  border-bottom: 2px solid #464b53;
}
table tbody tr td {
  padding: 5px;
}
</style>
