<template lang="">
  <div class="relative">
    <div
      :class="modal.isDisplay == true && modal.isDisplay !== 'none' ? 'opacity-5 select-none' : ''"
    >
      <div class="text-white">
        <router-link to="/">
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
        >Add new product</strong
      >
      <!-- {{ param == null ? 'Add new product' : 'Edit product' }} -->
      <form class="w-full post" method="post" enctype="multipart/form-data">
        <!-- Form Payment -->
        <div class="w-full">
          <!-- Form city and country. -->
          <div class="flex items-center justify-between w-full gap-2">
            <div class="w-full mb-3">
              <span class="text-base">Category name</span>
              <select
                name="categoryId"
                class="w-full p-2 mt-1 rounded-sm outline-none"
                id="categoryId"
                v-model="data.categoryId"
                @change="switchSelectTrademark($event)"
              >
                <option value="1" selected>1</option>
                <option value="2">2</option>
                <option value="9">9</option>
              </select>
            </div>
          </div>
          <!-- Form full name. -->
          <div class="mb-4">
            <span class="text-base">Product name</span>
            <Input
              type="text"
              name="categoryName"
              v-model="data.productName"
              placeholder="Name..."
              classChild="mt-2"
            />
          </div>
          <!-- Form note detail. -->
          <div class="mb-3">
            <span class="text-base">Product description</span>
            <!-- <Textarea name="categoryDescription" placeholder="Mô tả sản phẩm" /> -->
            <div class="bg-white">
              <quill-editor v-model:content="data.productDescription" theme="snow"></quill-editor>
            </div>
          </div>
          <!-- Form product price. -->
          <div class="mb-3">
            <span class="text-base">Product price</span>
            <Input
              type="text"
              name="productPrice"
              v-model="data.productPrice"
              placeholder="Price..."
              classChild="mt-2"
            />
          </div>
          <div class="mb-3">
            <span class="text-base">Product price sale</span>
            <Input
              type="text"
              name="productPriceSale"
              placeholder="Price sale..."
              classChild="mt-2"
              v-model="data.productPriceSale"
            />
          </div>
          <div class="mb-3">
            <span class="text-base">Quantity</span>
            <Input
              type="text"
              name="productPriceSale"
              placeholder="Quantity..."
              classChild="mt-2"
              v-model="data.quantity"
            />
          </div>
          <div class="mb-3">
            <span class="text-base">Upload file</span>
            <div class="">
              <label for="file" class="grid grid-cols-8 mt-2">
                <input
                  type="text"
                  class="col-span-7 rounded-tr-none rounded-br-none disabled:bg-white max-h-[40px]"
                  name="fileName"
                  for="file"
                  disabled
                  :value="files"
                  placeholder="Choose file..."
                />
                <span
                  class="text-[#17b1ea] block text-center rounded-tl-none rounded-bl-none col-span-1 rounded cursor-pointer bg-[#0c3247] py-2 px-3"
                  >Upload file</span
                >
              </label>
              <input
                @change="onChangeFile($event)"
                type="file"
                id="file"
                class="hidden w-full"
                multiple
              />
            </div>
          </div>
          <div class="grid grid-cols-2 gap-5 mb-3">
            <p
              class="text-[#17b1ea] block text-center col-span-1 rounded-tl-none rounded-bl-none rounded cursor-pointer bg-[#0c3247] py-2 px-3"
              @click="onPropsModalSize($event, 'size')"
            >
              Change size
            </p>
            <p
              class="text-[#17b1ea] block text-center col-span-1 rounded-tl-none rounded-bl-none rounded cursor-pointer bg-[#0c3247] py-2 px-3"
              @click="onPropsModalSize($event, 'color')"
            >
              Change color
            </p>
          </div>
        </div>
        <!-- Form address. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3">
            <span class="text-base">Status</span>
            <select
              name="categoryStatus"
              class="w-full p-2 mt-1 rounded-sm outline-none"
              id="status"
              @change="switchSelectStatus($event)"
            >
              <option value="1" selected>Active</option>
              <option value="0">In-Active</option>
            </select>
          </div>
        </div>
        <!-- Button submit order. -->
        <Button
          @click.prevent="onSubmit"
          type="button"
          className="bg-brown hover:bg-brown-hover text-white w-full m-0 mt-3"
          name="login"
          text="Register"
        />
        <!-- :text="param == null ? 'Register' : 'Update'" -->
      </form>
    </div>
    <form
      v-if="modal.isDisplay == true && modal.isType == 'size'"
      class="absolute top-[10%] right-[50%] m-auto bg-[#171B2D] py-5 px-5 flex flex-col items-center justify-center"
    >
      <h1 class="text-[#17b1ea] block text-center text-lg uppercase mb-2 font-bold">
        Product size
      </h1>
      <h5 class="text-[#17b1ea] block text-center text-xs uppercase mb-2 font-thin">
        Total quantity: {{ data.quantity }}
      </h5>
      <h5 class="block mb-2 text-xs font-thin text-center text-red-500 uppercase">
        {{ errorSize !== null ? errorSize : '' }}
      </h5>
      <div v-for="(item, index) in productSize" :key="index">
        <div class="flex mb-3 title">
          <p class="text-[#17b1ea] bg-[#0c3247] text-center py-2 px-3 w-[100px]">
            {{ item.productSize }}
          </p>
          <input
            @change="onChangeSize($event, item.productId)"
            :name="item.productId"
            type="text"
            :value="item.productQuantity"
            class="text-center rounded-none"
          />
        </div>
      </div>
      <Button
        type="button"
        text="OK"
        id="addCategory"
        @click="submitSize($event, 'size')"
        className="bg-green-700 hover:bg-green-600 min-w-[200px] -ml-[2px] text-white font-bold"
      />
    </form>
    <form
      v-if="modal.isDisplay == true && modal.isType == 'color'"
      class="rounded absolute top-[10%] right-[40%] m-auto bg-[#31395f] py-5 px-5 flex flex-col items-center justify-center"
    >
      <h1 class="text-[#17b1ea] block text-center text-lg uppercase mb-2 font-bold">
        Product color
      </h1>
      <h5 class="text-[#17b1ea] block text-center text-xs uppercase mb-2 font-thin">
        Total quantity: {{ data.quantity }}
      </h5>
      <h5 class="block mb-2 text-xs font-thin text-center text-red-500 uppercase">
        {{ errorSize !== null ? errorSize : '' }}
      </h5>
      <div class="flex mb-3 title">
        <p class="text-[#17b1ea] bg-[#0c3247] text-center py-2 px-3 w-[100px]">Size</p>
        <p class="text-[#17b1ea] bg-[#0c3247] text-center py-2 px-3 w-[100px] border">Quantity</p>
        <p class="text-[#17b1ea] bg-[#0c3247] text-center py-2 px-3 w-[200px] border">Color</p>
      </div>

      <div v-for="(item, indexColor) in productSize" :key="indexColor">
        <div class="flex mb-3 title">
          <p class="text-[#17b1ea] bg-[#0c3247] text-center py-2 px-3 w-[100px]">
            {{ item.productSize }}
          </p>
          <p class="text-[#17b1ea] bg-[#0c3247] text-center py-2 px-3 w-[100px] border">
            {{ item.productQuantity }}
          </p>
          <input
            @change="onChangeColor($event, item.productId)"
            :name="item.productId"
            :value="item.productColor"
            :disabled="item.productQuantity == 0"
            type="text"
            class="text-left rounded-none text-[#17b1ea] bg-[#0c3247]"
            :placeholder="item.productQuantity != 0 ? 'Color...' : ''"
          />
        </div>
      </div>
      <Button
        type="button"
        text="OK"
        id="addCategory"
        @click="submitSize($event, 'color')"
        className="bg-green-700 hover:bg-green-600 min-w-[200px] -ml-[2px] text-white font-bold"
      />
    </form>
  </div>
</template>
<script setup>
import { reactive, ref } from 'vue'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
const productSize = reactive([
  {
    productId: 1,
    productSize: 36,
    productColor: '',
    productQuantity: 0
  },
  {
    productId: 2,
    productSize: 37,
    productColor: '',
    productQuantity: 0
  },
  {
    productId: 3,
    productSize: 38,
    productColor: '',
    productQuantity: 0
  },
  {
    productId: 4,
    productSize: 39,
    productColor: '',
    productQuantity: 0
  },
  {
    productId: 5,
    productSize: 40,
    productColor: '',
    productQuantity: 0
  },
  {
    productId: 6,
    productSize: 41,
    productColor: '',
    productQuantity: 0
  },
  {
    productId: 7,
    productSize: 42,
    productColor: '',
    productQuantity: 0
  },
  {
    productId: 8,
    productSize: 43,
    productColor: '',
    productQuantity: 0
  }
])
const productColor = reactive({})
addProductColor(productSize)
const errorSize = ref('')
const modal = ref({
  isDisplay: false,
  isType: 'none'
})
const data = reactive({
  categoryId: '',
  productName: '',
  productId: '',
  productDescription: '',
  productPrice: '',
  productPriceSale: '',
  quantity: 36,
  status: '',
  sourceImageModelList: [],
  productColorModelList: [],
  productSizeModelList: []
})
const files = ref([])
console.log(data, files)
const onPropsModalSize = (event, type) => {
  modal.value.isDisplay = true
  modal.value.isType = type
}
const onChangeSize = (event, productId) => {
  productSize.forEach((element) => {
    if (element.productId === productId) {
      element.productQuantity = event.target.value
      if (event.target.value > data.quantity) {
        errorSize.value = 'The quantity does not exceed: ' + data.quantity
      }
      if (event.target.value <= data.quantity) {
        data.quantity = data.quantity - Number(event.target.value)
        errorSize.value = null
      }
    }
  })
}
const onChangeColor = (event, productId) => {
  productSize.forEach((element) => {
    if (element.productId === productId) {
      element.productColor = event.target.value
    }
  })
}
const submitSize = ($event, type) => {
  modal.value.isDisplay = false
  modal.value.isType = 'none'
  if (type === 'size') {
    data.productSizeModelList.value = productSize
    addProductColor(productSize)
  }
  if (type === 'color') {
    data.productColorModelList.value = productColor
  }
}
const switchSelectStatus = (event) => {
  data.status = event.target.value
}
const switchSelectTrademark = (event) => {
  data.categoryId = event.target.value
}
const onChangeFile = (event) => {
  files.value = event.target.files
}
const onSubmit = () => {
  console.log(data)
  console.log(files)
}
function addProductColor(productSize) {
  let array = []
  for (let index = 0; index < productSize.length; index++) {
    let color = productSize[index]
    color.productColor = ''
    array.push(color)
  }
  productColor.value = array
}
</script>
<style lang="css" scoped>
span {
  color: white;
}
.title p,
.title input {
  border-right: 2px #17b1ea solid;
}
.title p:first-child {
  border-left: 2px #17b1ea solid;
}
</style>
