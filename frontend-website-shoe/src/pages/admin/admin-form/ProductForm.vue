<template lang="">
  <div class="relative">
    <div
      :class="modal.isDisplay == true && modal.isDisplay !== 'none' ? 'opacity-5 select-none' : ''"
    >
      <div class="text-white">
        <router-link :to="{ name: 'ProductAdmin' }">
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
      >
        {{ pathName !== 'ProductEditAdmin' ? 'Thêm sản phẩm' : 'Sửa sản phẩm' }}
      </strong>
      <form
        class="w-full post"
        method="post"
        @submit.prevent="onSubmit(pathName)"
        enctype="multipart/form-data"
      >
        <!-- Form Payment -->
        <div class="w-full">
          <!-- Form city and country. -->
          <div class="flex items-center justify-between w-full gap-2">
            <div class="w-full mb-3">
              <span class="text-base">Hãng sản xuất</span>
              <select
                name="categoryId"
                class="w-full p-2 mt-1 rounded-sm outline-none"
                id="categoryId"
                v-model="data.categoryId"
                @change="switchSelectTrademark($event)"
              >
                <option
                  v-for="item in category.data"
                  :key="item.categoryId"
                  :value="item.categoryId"
                  :selected="data.categoryId == item"
                >
                  {{ item.categoryName }}
                </option>
              </select>
            </div>
          </div>
          <!-- Form full name. -->
          <div class="mb-4">
            <span class="text-base">Tên sản phẩm</span>
            <Input
              type="text"
              name="categoryName"
              v-model="data.productName"
              placeholder="Tên sản phẩm..."
              classChild="mt-2"
            />
          </div>
          <!-- Form note detail. -->
          <div class="mb-3">
            <span class="text-base">Mô tả chi tiết sản phẩm</span>
            <!-- <Textarea name="categoryDescription" placeholder="Mô tả sản phẩm" /> -->
            <div class="bg-white">
              <quill-editor
                v-model:content="data.productDescription"
                contentType="html"
                theme="snow"
              ></quill-editor>
            </div>
          </div>
          <!-- Form product price. -->
          <div class="mb-3">
            <span class="text-base">Giá sản phẩm</span>
            <Input
              type="text"
              name="productPrice"
              v-model="data.productPrice"
              placeholder="Giá..."
              classChild="mt-2"
            />
          </div>
          <div class="mb-3">
            <span class="text-base">Giá giảm sản phẩm</span>
            <Input
              type="text"
              name="productPriceSale"
              placeholder="Giá giảm..."
              classChild="mt-2"
              v-model="data.productPriceSale"
            />
          </div>
          <div class="mb-3">
            <span class="text-base">Số lượng</span>
            <Input
              type="text"
              name="productPriceSale"
              placeholder="Số lượng..."
              classChild="mt-2"
              v-model="data.quantity"
              @change="changeQuantity($event)"
            />
          </div>
          <div class="mb-3">
            <span class="text-base">Tải ảnh</span>
            <div class="">
              <label for="file" class="grid grid-cols-8 mt-2">
                <input
                  type="text"
                  class="col-span-7 rounded-tr-none rounded-br-none disabled:bg-white max-h-[40px]"
                  name="fileName"
                  for="file"
                  disabled
                  :value="files"
                  placeholder="Chọn ảnh..."
                />
                <span
                  class="text-[#17b1ea] block text-center rounded-tl-none rounded-bl-none col-span-1 rounded cursor-pointer bg-[#0c3247] py-2 px-3"
                  >Chọn ảnh</span
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
              Thêm kích cỡ
            </p>
            <p
              class="text-[#17b1ea] block text-center col-span-1 rounded-tl-none rounded-bl-none rounded cursor-pointer bg-[#0c3247] py-2 px-3"
              @click="onPropsModalSize($event, 'color')"
            >
              Thêm màu
            </p>
          </div>
        </div>
        <!-- Form address. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3">
            <span class="text-base">Trạng thái</span>
            <select
              name="categoryStatus"
              class="w-full p-2 mt-1 rounded-sm outline-none"
              id="status"
              v-model="data.status"
              @change="switchSelectStatus($event)"
            >
              <option value="1" selected>Hoạt động</option>
              <option value="0">Không hoạt động</option>
            </select>
          </div>
        </div>
        <!-- Button submit order. -->
        <Button
          type="submit"
          className="bg-brown
        hover:bg-brown-hover text-white w-full m-0 mt-3"
          name="login"
          :text="pathName !== 'ProductEditAdmin' ? 'Đăng kí' : 'Cập nhật'"
        />
      </form>
    </div>
    <form
      v-if="modal.isDisplay == true && modal.isType == 'size'"
      class="fixed top-[10%] right-[50%] m-auto bg-[#171B2D] py-5 px-5 flex flex-col items-center justify-center"
    >
      <h1 class="text-[#17b1ea] block text-center text-lg uppercase mb-2 font-bold">Kích cỡ</h1>
      <h5 class="text-[#17b1ea] block text-center text-xs uppercase mb-2 font-thin">
        Số lượng: {{ totalQuantity }}
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
      class="rounded fixed top-[10%] translate-x-[100px] m-auto bg-[#31395f] py-5 px-5 flex flex-col items-center justify-center"
    >
      <h1 class="text-[#17b1ea] block text-center text-lg uppercase mb-2 font-bold">Màu sắc</h1>
      <h5 class="text-[#17b1ea] block text-center text-xs uppercase mb-2 font-thin">
        Số lượng: {{ totalQuantity }}
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
            {{ totalQuantity }}
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
import { reactive, ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import ProductService from '@/stores/modules/ProductService'
import CategoryService from '@/stores/modules/CategoryService'
import { PRODUCT_EDIT, PRODUCT_NEW, INSERT, UPDATE } from '@/constants/index'
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
const category = reactive({
  data: []
})
const route = useRoute()
const pathName = useRoute().matched[0].name
onMounted(async () => {
  await getAllDataCategory(category)
  if (pathName === 'ProductEditAdmin') {
    await getById(route.params.categoryId, route.params.productId, data)
  }
})

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
  quantity: '',
  status: '1',
  productColorModelList: [],
  productSizeModelList: []
})
const backupQuantity = ref(0)
const totalQuantity = ref(0)
const form = reactive({ ...data })
const files = ref([])
const onPropsModalSize = (event, type) => {
  modal.value.isDisplay = true
  modal.value.isType = type
}
const onChangeSize = (event, productId) => {
  productSize.forEach((element) => {
    if (element.productId === productId) {
      element.productQuantity = event.target.value
      if (event.target.value > totalQuantity.value) {
        errorSize.value = 'The quantity does not exceed: ' + totalQuantity.value
      }
      if (event.target.value <= totalQuantity.value) {
        totalQuantity.value = totalQuantity.value - Number(event.target.value)
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
const onSubmit = (pathName) => {
  try {
    if (PRODUCT_NEW === pathName) {
      ProductService.insertOrUpdate(files.value, data, '/register', INSERT)
    }

    if (PRODUCT_EDIT === pathName) {
      ProductService.insertOrUpdate(files.value, data, '/update', UPDATE)
    }
    Object.assign(form, data)
    files.value = ''
  } catch (e) {
    console.log(e)
  }
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

function changeQuantity(event) {
  backupQuantity.value = event.target.value
  totalQuantity.value = event.target.value
}

async function getAllDataCategory(api) {
  try {
    const res = await CategoryService.getAll('/init')

    const result = {
      status: res.status,
      headers: res.headers,
      data: res.data
    }

    api.data = result.data
    data.categoryId = category.data[0].categoryId
  } catch (error) {
    api = formatResponse(error.response?.data) || error
  }
}

async function getById(categoryId, productId, data) {
  try {
    const res = await ProductService.getProductById('/init', categoryId, productId)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }

    Object.assign(data, result.data)
    data.productSizeModelList.forEach((element) => {
      productSize
        .filter((condition) => condition.productId === element.productSizeId)
        .map((item) => {
          item.productSize = element.productSize
          item.productQuantity = element.productSizeQuantity
        })
    })
    data.productColorModelList.forEach((element) => {
      productSize
        .filter((condition) => condition.productId === element.productSizeId)
        .map((item) => {
          item.productSize = element.productColor
          item.productColor = element.productColorQuantity
        })
    })
    // totalQuantity = res.data.$event
  } catch (error) {
    formatResponse(error.response?.data) || error
  }
}
function formatResponse(res) {
  return JSON.stringify(res, null, 2)
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
