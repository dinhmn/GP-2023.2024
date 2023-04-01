<template lang="">
  <BasePage class="flex flex-col items-center justify-center gap-5 text-white">
    <template v-slot:body>
      <div class="grid w-full grid-cols-2 gap-5 px-10 py-10 bg-gray-700 rounded-lg">
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
              src="../../assets/images/default.png"
              alt="Ảnh đại diện"
              class="w-[50px] h-[50px] object-cover rounded-full mr-2 ml-5"
            />
            <p
              class="px-4 py-2 border-2 border-gray-200 border-solid rounded-md"
              :class="success ? 'text-green-500' : ''"
            >
              {{
                success
                  ? 'Hi, I’m Amanda. Chúng tôi sẽ liên hệ trong thời gian sớm nhất'
                  : 'Hi, I’m Amanda. Need help? Use the form below or email me at hello@california_golfclub.org.'
              }}
            </p>
          </div>
          <form class="w-full">
            <!-- Form Payment -->
            <div class="flex flex-col w-full gap-2">
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
                />
              </div>
              <!-- Form email address. -->
              <div class="mb-4">
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
                    v$.email.$error && error.emailError == true
                      ? 'border-[2px] border-red-600 border-solid'
                      : 'border-transparent'
                  "
                />
                <!-- <span v-if="v$.email.$error" :class="v$.email.$error ? 'text-xs text-red-500' : 'hidden'"> {{ v$.email.$errors[0].$message }} </span>
              <span v-if="v$.email.$error == false" >  </span> -->
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
                />
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
      nameError: false,
      emailError: false,
      messageError: false
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
        phone: { required }
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
      this.v$.$validate()
      if (type == 'email') {
        this.error.emailError = true
      } else {
        this.error.emailError = false
      }
    }
  }
}
</script>
<style lang="css" scoped>
button {
  background-color: #0c3247;
  color: #17b1ea;
}
button:hover {
  background-color: #124a68;
  color: #17b1ea;
}
</style>
