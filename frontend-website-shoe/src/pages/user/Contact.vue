<template lang="">
  <BasePage class="flex flex-col items-center justify-center gap-5 text-white">
    <template v-slot:body>
      <div class="grid w-full grid-cols-2 gap-5 px-10 py-10 bg-[#F8F8F8] text-cyan-800 rounded-lg">
        <div class="col-span-1">
          <VMap>
            <VMapOsmTileLayer />
          </VMap>
        </div>
        <div class="w-full h-full col-span-1">
          <div>
            <h1 class="font-[400] text-2xl leading-10 m-auto pb-2 -tracking-tighter">
              Liên hệ với chúng tôi..
            </h1>
          </div>
          <div class="flex items-center justify-between gap-3 mb-3">
            <img
              src="../../assets/images/logo.png"
              alt="Ảnh đại diện"
              class="w-[50px] h-[50px] object-cover rounded-full mr-2 ml-5"
            />
            <p
              class="px-4 py-2 border-2 border-gray-200 border-solid rounded-md"
              :class="success ? 'text-green-500' : ''"
            >
              {{
                success
                  ? 'Chào bạn, mình là Định. Chúng tôi sẽ liên hệ trong thời gian sớm nhất'
                  : 'Chào bạn, mình là Định. Bạn cần sự giúp đỡ? Hãy thêm ý kiến dưới đây và gửi cho tôi tại địa chỉ email ngocdinh2k1@gmail.com'
              }}
            </p>
          </div>
          <form class="w-full">
            <!-- Form Payment -->
            <div class="flex flex-col w-full">
              <!-- Form full name. -->
              <div class="mb-4">
                <span class="text-sm"
                  >Họ và tên <span class="text-lg align-middle text-brown">*</span>
                </span>
                <input-component
                  v-model="state.fullName"
                  type="text"
                  name="fullName"
                  placeholder="Họ tên của bạn"
                  @keyup="onPressText($event, 'fullName')"
                  :classChild="
                    v$.fullName.$error && error.fullName == true
                      ? 'border-red-600'
                      : 'border-transparent'
                  "
                />
                <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
                  {{
                    v$.fullName.$error && error.fullName == true
                      ? v$.fullName.$errors[0].$message
                      : ''
                  }}
                </span>
              </div>
              <!-- Form email address. -->
              <div class="mb-4 min-h-[40px]">
                <span class="text-sm"
                  >Email <span class="text-lg align-middle text-brown">*</span>
                </span>
                <input-component
                  type="email"
                  name="address"
                  placeholder="abc@gmail.com"
                  v-model="state.email"
                  @keyup="onPressText($event, 'email')"
                  :classChild="
                    v$.email.$error && error.email == true ? 'border-red-600' : 'border-transparent'
                  "
                />
                <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
                  {{ v$.email.$error && error.email == true ? v$.email.$errors[0].$message : '' }}
                </span>
              </div>
              <!-- Form phone. -->
              <div class="mb-4">
                <span class="text-sm"
                  >Điện thoại liên hệ <span class="text-lg align-middle text-brown">*</span>
                </span>
                <input-component
                  v-model="state.phone"
                  type="text"
                  name="phone"
                  placeholder="098xxxxxxx"
                  @keyup="onPressText($event, 'phone')"
                  :classChild="
                    v$.phone.$error && error.phone == true ? 'border-red-600' : 'border-transparent'
                  "
                />
                <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
                  {{ v$.phone.$error && error.phone == true ? v$.phone.$errors[0].$message : '' }}
                </span>
              </div>
              <!-- Form note detail. -->
              <div class="mb-4">
                <span class="text-sm">Tin nhắn(nếu có)</span>
                <textarea-component
                  placeholder="Ghi chú về thông tin đơn hàng..."
                  name="note"
                  v-model="state.message"
                />
              </div>
            </div>
            <!-- Button submit order. -->
            <button-component
              @click="onSubmit"
              type="button"
              className="bg-[#6088EE] hover:bg-[#6892ff] text-white w-full m-0"
              name="sendMyMessage"
              text="Liên hệ"
            />
          </form>
        </div>
      </div>
    </template>
  </BasePage>
</template>
<script>
import { ref, reactive, computed } from 'vue'
import useValidate from '@vuelidate/core'
import { required, email, minLength } from '@vuelidate/validators'
import BasePage from '../auth/BasePage.vue'
import Button from '@/components/common/button/Button.vue'
import Input from '@/components/common/input/Input.vue'
import Textarea from '@/components/common/input/Textarea.vue'
import { VMap, VMapOsmTileLayer } from 'vue-map-ui'
export default {
  name: 'ContactPage',
  components: {
    BasePage,
    ButtonComponent: Button,
    InputComponent: Input,
    TextareaComponent: Textarea,
    VMap,
    VMapOsmTileLayer
  },
  setup() {
    const error = reactive({
      fullName: false,
      email: false,
      phone: false
    })
    const state = reactive({
      fullName: '',
      email: '',
      message: '',
      phone: ''
    })
    const rules = computed(() => {
      return {
        fullName: { required, minLength: minLength(6) },
        email: { required, email },
        phone: { required, minLength: minLength(10) }
      }
    })
    const success = ref(false)
    const v$ = useValidate(rules, state)

    return { state, v$, error, success }
  },
  methods: {
    onSubmit() {
      this.v$.$validate()
      if (!this.v$.$error) {
        // if ANY fail validation
        this.success = true
        console.log(this.state)
      } else {
        alert('Form failed validation')
      }
    },
    onPressText(event, type) {
      if (type == 'email') {
        this.v$.$validate()
        this.error.email = true
      } else {
        this.error.email = false
      }
      if (type == 'fullName') {
        this.v$.$validate()
        this.error.fullName = true
      } else {
        this.error.fullName = false
      }
      if (type == 'phone') {
        this.v$.$validate()
        this.error.phone = true
      } else {
        this.error.phone = false
      }
    }
  }
}
</script>
<style lang="css" scoped>
button {
  background-color: #0c3247;
}
button:hover {
  background-color: #124a68;
}
</style>
