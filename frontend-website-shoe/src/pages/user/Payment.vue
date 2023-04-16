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
              <input-increment
                :name="'quantity-' + product.id"
                v-model="product.quantity"
                :value="product.quantity"
              />
            </div>
            <button class="bg-red-600" @click="handleDeleteItem($event, product)">Delete</button>
          </div>
        </div>
        <!-- Total money a lot of product -->
        <div class="after-cart flex items-center font-bold justify-between mt-[30px]">
          <span>Tạm tính ({{ data.product.length }} sản phẩm):</span>
          <span>{{ formatPrice(moneyOfOneProduct) }} đ</span>
        </div>
      </div>
      <!-- Form use to order -->
      <div class="order w-[40%] bg-slate-600 p-5 rounded-lg">
        <h2 class="my-2 text-xl font-bold uppercase text-brown">Thanh toán và giao hàng</h2>
        <!-- Order -->
        <form class="w-full">
          <!-- Form Payment -->
          <div class="w-fill">
            <!-- Form full name. -->
            <div class="mb-4">
              <span class="text-sm"
                >Họ và tên <span class="text-lg align-middle text-brown">*</span>
                <span class="ml-1 text-xs leading-4 text-red-600">
                  {{
                    validate.fullName.$error && error.fullName == true
                      ? validate.fullName.$errors[0].$message
                      : ''
                  }}
                </span>
              </span>
              <input-component
                v-model="data.fullName"
                type="text"
                name="fullName"
                placeholder="Họ tên của bạn"
                @keyup="onPressText($event, 'fullName')"
                :classChild="
                  validate.fullName.$error && error.fullName == true
                    ? 'border-red-600'
                    : 'border-transparent'
                "
              />
            </div>
            <!-- Form phone. -->
            <div class="w-full mb-3">
              <span class="text-sm"
                >Số điện thoại <span class="text-lg align-middle text-brown">*</span>
                <span class="ml-1 text-xs leading-4 text-red-600">
                  {{
                    validate.phone.$error && error.phone == true
                      ? validate.phone.$errors[0].$message
                      : ''
                  }}
                </span>
              </span>
              <input-component
                v-model="data.phone"
                type="text"
                name="phone"
                placeholder="0982211xxx"
                @keyup="onPressText($event, 'phone')"
                :classChild="
                  validate.phone.$error && error.phone == true
                    ? 'border-red-600'
                    : 'border-transparent'
                "
              />
            </div>
            <!-- Form email. -->
            <div class="mb-4">
              <span class="text-sm"
                >Email: <span class="text-lg align-middle text-brown">*</span>
                <span class="ml-1 text-xs leading-4 text-red-600">
                  {{
                    validate.email.$error && error.email == true
                      ? validate.email.$errors[0].$message
                      : ''
                  }}
                </span>
              </span>
              <input-component
                v-model="data.email"
                type="email"
                name="email"
                placeholder="abc@gmail.com"
                @keyup="onPressText($event, 'email')"
                :classChild="
                  validate.email.$error && error.email == true
                    ? 'border-red-600'
                    : 'border-transparent'
                "
              />
            </div>
            <!-- Form city and country. -->
            <div class="flex items-center justify-between w-full gap-2">
              <div class="w-full mb-3 mr-3">
                <span class="text-sm"
                  >Tỉnh/Thành phố <span class="text-lg align-middle text-brown">*</span>
                </span>
                <SelectComponent name="city" v-model="data.city">
                  <template v-slot:option>
                    <option v-for="item in count" :selected="item === 1" :key="item" :value="item">
                      Option {{ item }}
                    </option>
                  </template>
                </SelectComponent>
              </div>
              <div class="w-full mb-3 ml-3">
                <span class="text-sm"
                  >Quận/Huyện <span class="text-lg align-middle text-brown">*</span>
                </span>
                <select-component name="country" v-model="data.country">
                  <template v-slot:option>
                    <option v-for="item in count" :key="item" :value="item">
                      Option {{ item }}
                    </option>
                  </template>
                </select-component>
              </div>
            </div>
            <!-- Form address. -->
            <div class="mb-3">
              <span class="text-sm"
                >Địa chỉ <span class="text-lg align-middle text-brown">*</span>
                <span class="ml-1 text-xs leading-4 text-red-600">
                  {{
                    validate.address.$error && error.address == true
                      ? validate.address.$errors[0].$message
                      : ''
                  }}
                </span>
              </span>
              <input-component
                v-model="data.address"
                type="text"
                name="address"
                placeholder="Ví dụ: Số 20, Ngõ 90"
                @keyup="onPressText($event, 'address')"
                :classChild="
                  validate.address.$error && error.address == true
                    ? 'border-red-600'
                    : 'border-transparent'
                "
              />
            </div>
            <!-- Form note detail. -->
            <div class="mb-3">
              <span class="text-sm">Ghi chú đơn hàng(nếu có) </span>
              <textarea-component
                v-model="data.note"
                placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay địa chỉ chi tiết hơn."
                name="note"
              />
            </div>
          </div>
          <!-- Total Payment -->
          <div class="flex flex-col gap-2 total">
            <div class="flex items-center justify-between">
              <span>Tạm tính::</span>
              <span class="text-sm">{{ formatPrice(moneyOfOneProduct) }} đ</span>
            </div>
            <div class="flex items-center justify-between">
              <span>Giao hàng:</span>
              <span class="text-sm">Giao hàng miễn phí</span>
            </div>
            <div class="flex items-center justify-between">
              <span>Tổng:</span>
              <span class="text-sm">{{ formatPrice(moneyOfOneProduct) }} đ</span>
            </div>
          </div>
          <!-- Payments. -->
          <div>
            <radio-component
              modelValue="cod"
              v-model="data.method"
              id="receivePayment"
              text="Nhận hàng thanh toán (COD)"
            />
            <radio-component
              modelValue="bank"
              v-model="data.method"
              id="bankTransfer"
              text="Chuyển khoản ngân hàng"
            />
          </div>
          <!-- Button submit order. -->
          <button-component
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
<script>
import { ref, reactive, computed } from 'vue'
import useValidate from '@vuelidate/core'
import { required, minLength, email } from '@vuelidate/validators'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Radio from '@/components/common/input/Radio.vue'
import Textarea from '@/components/common/input/Textarea.vue'
import Select from '@/components/common/input/Select.vue'
import InputIncrement from '@/components/common/input/InputIncrement.vue'
export default {
  name: 'PaymentPage',
  components: {
    InputComponent: Input,
    ButtonComponent: Button,
    RadioComponent: Radio,
    TextareaComponent: Textarea,
    SelectComponent: Select,
    InputIncrement: InputIncrement
  },
  setup(props) {
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
      email: '',
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
    const rules = computed(() => {
      return {
        email: { required, email },
        fullName: { required },
        phone: { required, minLength: minLength(10) },
        address: { required }
      }
    })
    const error = reactive({
      fullName: false,
      email: false,
      phone: false,
      address: false
    })
    const validate = useValidate(rules, data)
    const success = ref(false)

    return { props, product, data, count, validate, success, error }
  },
  methods: {
    onSubmit() {
      console.log(this.data)
      this.validate.$validate()
      if (!this.validate.$error) {
        // if ANY fail validation
        this.success = true
        console.log(this.state)
      } else {
        alert('Form failed validation')
      }
    },
    formatPrice(value) {
      let val = (value / 1).toFixed(0).replace('.', ',')
      return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
    },
    handleDeleteItem(event, index) {
      this.data.product = this.data.product.filter((x) => x.id !== index)
    },
    onPressText(event, type) {
      if (type == 'email') {
        this.validate.$validate()
        this.error.email = true
      } else if (!this.error.email) {
        this.error.email = false
      }
      if (type == 'fullName') {
        this.validate.$validate()
        this.error.fullName = true
      } else if (!this.error.fullName) {
        this.error.fullName = false
      }
      if (type == 'address') {
        this.validate.$validate()
        this.error.address = true
      } else if (!this.error.address) {
        this.error.address = false
      }
      if (type == 'phone') {
        this.validate.$validate()
        this.error.phone = true
      } else if (!this.error.phone) {
        this.error.phone = false
      }
    }
  },
  computed: {
    moneyOfOneProduct() {
      let sum = 0
      this.data.product.map((x) => (sum += x.price * x.quantity))
      return sum
    }
  }
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
