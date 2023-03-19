<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <section
    class="mt-3 text-white m-auto 2xl:w-[1280px] xl:w-[100%] container flex items-start justify-start flex-col min-h-[100vh]"
  >
    <div class="flex items-start justify-between w-full gap-10 py-2 my-1 rounded-[4px]">
      <!-- Order in Cart -->
      <div class="cart bg-slate-600 w-[60%] p-5 rounded-lg">
        <!-- Item in Cart -->
        <div
          class="flex items-center justify-between mb-5"
          v-for="(product, index) in data.product"
          :key="index"
        >
          <!-- Information of product. -->
          <div class="flex items-start gap-5 align-middle">
            <img
              src="../../assets/images/default.png"
              class="h-[100px] w-[100px] object-cover"
              alt=""
            />
            <div class="flex flex-col items-start justify-start h-full gap-2">
              <h2 class="text-xl font-bold min-w-[200px]">{{ product.productName }}</h2>
              <div class="text-sm text-gray-200">
                <span>Size: 36 - Màu: </span>
                <p>{{ formatPrice(product.price * product.quantity) }} đ</p>
              </div>
            </div>
          </div>
          <!-- Increment, decrement, or delete product. -->
          <div class="flex items-center justify-between">
            <div>
              <InputIncrement
                :name="'quantity-' + product.id"
                v-model="product.quantity"
                :value="product.quantity"
              />
            </div>
            <button class="bg-red-600" @click="handleDeleteItem($event, product.id)">Delete</button>
          </div>
        </div>
        <!-- Total money a lot of product -->
        <div class="after-cart flex items-center font-bold justify-between mt-[30px]">
          <span>Tạm tính ({{ product.length }} sản phẩm):</span>
          <span>{{ formatPrice(moneyOfOneProduct) }} đ</span>
        </div>
      </div>
      <!-- Form use to order -->
      <div class="order w-[40%] bg-slate-600 p-5 rounded-lg">
        <h2 class="my-2 text-xl font-bold uppercase text-brown">Thanh toán và giao hàng</h2>
        <!-- Order -->
        <form leave-to-class="w-full post">
          <!-- Form Payment -->
          <div class="w-fill">
            <!-- Form full name. -->
            <div class="mb-4">
              <span class="text-sm"
                >Họ và tên <span class="text-lg align-middle text-brown">*</span>
              </span>
              <Input
                v-model="data.fullName"
                type="text"
                name="fullName"
                placeholder="Họ tên của bạn"
              />
            </div>
            <!-- Form phone. -->
            <div class="w-full mb-3">
              <span class="text-sm"
                >Số điện thoại <span class="text-lg align-middle text-brown">*</span>
              </span>
              <Input v-model="data.phone" type="text" name="phone" placeholder="Số điện thoại" />
            </div>
            <!-- Form city and country. -->
            <div class="flex items-center justify-between w-full gap-2">
              <div class="w-full mb-3 mr-3">
                <span class="text-sm"
                  >Tỉnh/Thành phố <span class="text-lg align-middle text-brown">*</span>
                </span>
                <Select name="city" v-model="data.city" :value="1">
                  <template v-slot:option>
                    <option v-for="item in count" :selected="item === 1" :key="item" :value="item">
                      Option {{ item }}
                    </option>
                  </template>
                </Select>
              </div>
              <div class="w-full mb-3 ml-3">
                <span class="text-sm"
                  >Quận/Huyện <span class="text-lg align-middle text-brown">*</span>
                </span>
                <Select name="country" v-model="data.country" :value="1">
                  <template v-slot:option>
                    <option v-for="item in count" :key="item" :selected="item === 1" :value="item">
                      Option {{ item }}
                    </option>
                  </template>
                </Select>
              </div>
            </div>
            <!-- Form address. -->
            <div class="mb-3">
              <span class="text-sm"
                >Địa chỉ <span class="text-lg align-middle text-brown">*</span>
              </span>
              <Input
                v-model="data.address"
                type="text"
                name="address"
                placeholder="Ví dụ: Số 20, Ngõ 90"
              />
            </div>
            <!-- Form note detail. -->
            <div class="mb-3">
              <span class="text-sm">Ghi chú đơn hàng(nếu có) </span>
              <Textarea
                v-model="data.note"
                placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay địa chỉ chi tiết hơn."
                name="note"
              />
            </div>
          </div>
          <!-- Total Payment -->
          <div class="flex flex-col gap-2 total">
            <div
              v-for="(item, index) in information"
              :key="index"
              class="flex items-center justify-between"
            >
              <span>{{ item.title }}:</span>
              <span class="text-sm">{{ item.value }}</span>
            </div>
          </div>
          <!-- Payments. -->
          <div>
            <Radio
              modelValue="cod"
              v-model="data.method"
              id="receivePayment"
              text="Nhận hàng thanh toán (COD)"
            />
            <Radio
              modelValue="bank"
              v-model="data.method"
              id="bankTransfer"
              text="Chuyển khoản ngân hàng"
            />
          </div>
          <!-- Button submit order. -->
          <Button
            @click.prevent="onSubmit"
            type="button"
            className="bg-brown text-white w-full m-0"
            name="login"
            text="Đặt hàng"
          />
        </form>
        <!-- Form Payment -->
      </div>
    </div>
  </section>
</template>
<script setup>
import { reactive, computed } from 'vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Radio from '@/components/common/input/Radio.vue'
import Textarea from '@/components/common/input/Textarea.vue'
import Select from '@/components/common/input/Select.vue'
import InputIncrement from '@/components/common/input/InputIncrement.vue'
const information = [
  {
    title: 'Tạm tính',
    value: Number(750000)
  },
  {
    title: 'Giao hàng',
    value: 'Giao hàng miễn phí'
  },
  {
    title: 'Tổng',
    value: Number(750000)
  }
]
const product = reactive([
  {
    id: 1,
    productName: 'Giày Nike',
    quantity: 1,
    price: 750000
  },
  {
    id: 2,
    productName: 'Giày Nike',
    quantity: 2,
    price: 900000
  },
  {
    id: 3,
    productName: 'Giày Adidas',
    quantity: 3,
    price: 800000
  }
])
const data = reactive({
  fullName: '',
  phone: '',
  city: '',
  country: '',
  address: '',
  note: '',
  product: product,
  method: ''
})
const count = 5
const moneyOfOneProduct = computed(() => {
  let sum = 0
  data.product.map((x) => (sum += x.price * x.quantity))
  return sum
})

const onSubmit = () => {
  console.log(data)
}
const formatPrice = (value) => {
  let val = (value / 1).toFixed(0).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
}
const handleDeleteItem = (event, index) => {
  data.product = product.filter((x) => x.id !== index)
}
</script>
<style lang="scss">
.after-cart {
  position: relative;
  padding-top: 20px;
  &::after {
    position: absolute;
    content: '';
    height: 1px;
    width: 100%;
    background-color: white;
    top: 0px;
  }
}
.total {
  width: 100%;
  &::before,
  &::after {
    content: '';
    margin: 8px auto;
    height: 1px;
    width: 450px;
    background-color: white;
  }
}
</style>
