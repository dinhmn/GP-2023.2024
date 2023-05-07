<template lang="">
  <div class="flex items-center justify-center w-full bg-cyan-bluish-gray">
    <footer class="2xl:w-[1280px] flex items-center justify-between h-[300px] text-gray-800">
      <div class="flex flex-col gap-8">
        <h2 class="text-3xl font-bold">Des Shoes</h2>
        <p class="w-[300px] text-gray-800 text-base italic font-medium">Bắc Từ Liêm, Hà Nội</p>
        <div>
          <h3 class="mb-2 text-lg font-bold text-gray-800">
            Đăng ký ngay để nhận thông tin mới nhất về sản phẩm
          </h3>
          <form @submit.prevent="onSubmit" class="flex items-center justify-center h-full">
            <input-common
              type="text"
              classChild="min-w-[500px] px-5 py-[10px] rounded-sm"
              name="email"
              placeholder="Email"
              v-model="data.email"
              @keyup="v$.$validate()"
            />
            <button-common
              type="submit"
              text="Đăng ký"
              className="w-full px-5 py-3 m-0 ml-4 rounded-sm bg-dark-blue hover:bg-dark-blue-hover text-white font-bold"
            />
          </form>
          <span class="h-[20px] px-1 text-xs leading-4 text-red-600">
            {{ v$.email.$error ? v$.email.$errors[0].$message : '' }}
          </span>
        </div>
      </div>
      <div class="flex items-start justify-around gap-8">
        <div v-for="(items, key) in footList" :key="key" class="flex flex-col gap-8 mx-6">
          <h3 class="text-lg font-bold">{{ items.title }}</h3>
          <ul class="flex flex-col gap-2">
            <li
              class="text-base italic font-thin text-gray-800"
              v-for="(item, index) in items.items"
              :key="index"
            >
              {{ item }}
            </li>
          </ul>
        </div>
      </div>
    </footer>
  </div>
</template>
<script>
import Input from '../common/input/Input.vue'
import Button from '../common/button/Button.vue'
import { reactive, computed } from 'vue'
import useValidate from '@vuelidate/core'
import { required, email } from '@vuelidate/validators'
const footList = [
  {
    title: 'Giới thiệu',
    items: ['Cửa hàng', 'Nhân viên', 'Hình thức thanh toán']
  },
  {
    title: 'Hãng bán chạy',
    items: ['Nike', 'NB', 'Adidas', 'Jordan']
  },
  {
    title: 'Trang quan trọng',
    items: ['Trang chủ', 'FAQ', 'Tin tức']
  }
]
export default {
  name: 'FooterItem',
  components: {
    InputCommon: Input,
    ButtonCommon: Button
  },
  setup(props) {
    const data = reactive({
      email: ''
    })
    const rules = computed(() => {
      return {
        email: { required, email }
      }
    })
    const v$ = useValidate(rules, data)
    return { props, footList, data, v$ }
  }
}
</script>
<style lang="scss">
ul {
  li {
    font-weight: 500;
    cursor: pointer;
  }
}
</style>
