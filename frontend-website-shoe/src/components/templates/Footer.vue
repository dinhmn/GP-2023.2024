<template lang="">
  <div class="w-full bg-[#425868] flex items-center justify-center">
    <footer class="2xl:w-[1280px] flex items-center justify-between h-[300px] text-white">
      <div class="flex flex-col gap-8">
        <h2 class="text-3xl font-bold">Shoes</h2>
        <p class="w-[300px] text-gray-300 text-base italic font-thin">
          4140 Parker Roas, Allentown, New Mexico 31123
        </p>
        <div>
          <h3 class="mb-2 text-lg font-bold text-brown">
            Đăng kí để nhận ngay thông tin về sản phẩm mới.
          </h3>
          <form @submit.prevent="onSubmit" class="flex items-center justify-center h-full">
            <input-common
              type="text"
              classChild="min-w-[500px] px-5 py-[10px] rounded-sm"
              name="email"
              placeholder="Email"
              v-model="email"
            />
            <button-common
              type="submit"
              text="Đăng ký"
              className="w-full px-5 py-3 m-0 ml-4 rounded-sm bg-brown hover:bg-brown-hover"
            />
          </form>
        </div>
      </div>
      <div class="flex items-start justify-around gap-8">
        <div v-for="(items, key) in footList" :key="key" class="flex flex-col gap-8 mx-6">
          <h3 class="text-lg font-bold">{{ items.title }}</h3>
          <ul class="flex flex-col gap-2">
            <li
              class="text-base italic font-thin text-gray-300"
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
import { ref } from 'vue'
const footList = [
  {
    title: 'About',
    items: ['Company', 'Career', 'Payment']
  },
  {
    title: 'Categories',
    items: ['Nike', 'NB', 'Adidas', 'Jordan']
  },
  {
    title: 'Important Links',
    items: ['Shop Page', 'FAQ', 'Blog']
  }
]
export default {
  name: 'FooterItem',
  components: {
    InputCommon: Input,
    ButtonCommon: Button
  },
  setup(props) {
    const email = ref('')
    return { props, footList, email }
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
