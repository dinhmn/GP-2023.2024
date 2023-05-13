<template lang="">
  <BasePage>
    <template v-slot:body>
      <div v-if="loading" class="flex items-center justify-center w-full h-[100vh]">
        <div class="lds-facebook">
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
      <div v-else>
        <div
          class="flex items-center justify-start w-full px-6 py-2 text-lg font-bold text-gray-800 rounded-[4px] bg-[#F8F8F8]"
        >
          <span class="text-xl">Trang chủ</span>
          <span
            ><vue-feather class="w-5 h-5 translate-y-1" type="chevron-right"></vue-feather
          ></span>
          <span>Sản phẩm</span>
        </div>
        <div
          class="grid grid-cols-5 w-full gap-10 px-6 py-5 my-1 text-lg font-bold text-gray-800 rounded-[4px] bg-[#F8F8F8]"
        >
          <div class="flex flex-col items-center justify-start col-span-2">
            <div class="image item-select">
              <img
                :active-image-id="activeImage.imageId"
                :src="getImageUrl(activeImage.fileName)"
                :alt="activeImage.fileName"
                class="w-[500px] h-[400px] object-cover"
              />
            </div>
            <div class="flex items-center justify-around gap-2 mt-3">
              <div
                v-for="item in product.productModel.sourceImageModelList"
                :key="item.imageId"
                class="image"
                @click="onActiveImage(event, item)"
              >
                <img
                  class="w-[100px] h-[100px] object-cover"
                  :src="getImageUrl(item.fileName)"
                  :alt="item.fileName"
                />
              </div>
            </div>
          </div>
          <div class="flex flex-col items-start w-full h-full col-span-3 gap-5">
            <h2 class="w-full text-2xl font-bold text-left rounded">
              {{ product.productModel.productName }}
            </h2>
            <div class="flex items-center justify-between w-full text-base font-normal">
              <div>
                <span class="text-2xl text-brown"
                  >{{ formatPrice(product.productModel.productPriceSale) }}đ</span
                >
                <span class="ml-3 mr-2 text-sm font-semibold text-gray-500 line-through"
                  >{{ formatPrice(product.productModel.productPrice) }}đ</span
                >
                <span class="p-1 text-sm text-white rounded-sm bg-brown">{{
                  (
                    ((product.productModel.productPrice - product.productModel.productPriceSale) /
                      product.productModel.productPrice) *
                    100
                  ).toFixed(0) + '%'
                }}</span>
              </div>
              <div class="text-sm">
                <span>{{ product.comment.length }} đánh giá</span>
              </div>
            </div>
            <div class="flex items-start justify-between w-full text-sm">
              <div>
                <p class="mt-2 text-lg">
                  Trạng thái:
                  <span class="text-sm">{{
                    product.productModel.quantity > 0 ? 'Còn hàng' : 'Hết hàng'
                  }}</span>
                </p>
              </div>
              <p class="text-lg">
                Số lượng: <span class="text-sm">{{ product.productModel.quantity }}</span>
              </p>
            </div>
            <div class="w-full mb-3 mr-3">
              <span class="text-lg"
                >Kích cỡ <span class="text-lg align-middle text-brown">*</span>
              </span>
              <select
                name="productSize"
                v-model="product.productSize"
                class="w-full p-2 mt-1 text-gray-800 rounded-sm outline-none border-cyan-900 border-[1px] border-solid"
                :value="Number(36)"
              >
                <option
                  class="text-gray-800"
                  v-for="item in count"
                  :selected="item === 1"
                  :key="item"
                  :value="35 + item"
                >
                  {{ item + 35 }}
                </option>
              </select>
            </div>
            <div class="flex h-[33px]">
              <span class="text-lg w-[150px]">Số lượng: </span>
              <InputIncrement
                classChild="text-sm p-0 w-full text-white "
                name="quantity"
                v-model="product.productQuantity"
                :value="quantity"
              />
            </div>
            <form class="flex w-full gap-10">
              <Button
                @click.prevent="onSubmit($event, 'add')"
                type="submit"
                className="bg-dark-blue hover:bg-dark-blue-hover text-white hover:text-cyanBlue w-full m-0"
                name="addCart"
                text="Thêm vào giỏ hàng"
              />
              <Button
                @click="onSubmit($event, 'buy')"
                type="submit"
                className="bg-dark-blue hover:bg-dark-blue-hover text-white hover:text-cyanBlue w-full m-0"
                name="buyNow"
                text="Mua ngay"
              />
            </form>
          </div>
        </div>
        <div
          class="grid grid-cols-5 mb-3 w-full gap-10 px-6 py-5 my-1 font-bold text-gray-800 rounded-[4px] bg-[#F8F8F8]"
        >
          <div class="col-span-4">
            <div class="grid grid-cols-3 gap-1 text-white">
              <span
                @click="onUserSelect($event, 'spec')"
                class="w-full col-span-1 py-2 text-center transition-all cursor-pointer bg-dark-blue hover:bg-dark-blue-hover hover:text-cyanBlue"
                :class="userSelect.spec === true ? 'bg-dark-blue-hover text-cyanBlue' : ''"
                >Thông số kỹ thuật</span
              >
              <span
                @click="onUserSelect($event, 'desc')"
                class="w-full col-span-1 py-2 text-center transition-all cursor-pointer bg-dark-blue hover:bg-dark-blue-hover hover:text-cyanBlue"
                :class="userSelect.desc === true ? 'bg-dark-blue-hover text-cyanBlue' : ''"
                >Mô tả sản phẩm</span
              >
              <span
                @click="onUserSelect($event, 'review')"
                class="w-full col-span-1 py-2 text-center transition-all cursor-pointer bg-dark-blue hover:bg-dark-blue-hover hover:text-cyanBlue"
                :class="userSelect.review === true ? 'bg-dark-blue-hover text-cyanBlue' : ''"
                >Đánh giá</span
              >
            </div>
            <CommonItem v-if="userSelect.spec === true">
              <table class="w-full gap-5 mt-3 font-semibold text-white">
                <tr>
                  <td class="opacity-80 bg-gray bg-slate-400">Kích cỡ:</td>
                  <td class="opacity-80 bg-slate-500">36, 37, 38, 39, 40, 41, 42, 43</td>
                </tr>
                <tr>
                  <td class="opacity-80 bg-gray bg-slate-400">Quà tặng kèm:</td>
                  <td class="opacity-80 bg-slate-500">Full box + tax + bill, Tặng tất</td>
                </tr>
                <tr>
                  <td class="opacity-80 bg-gray bg-slate-400">Hãng sản xuất:</td>
                  <td class="opacity-80 bg-slate-500">{{ product.productModel.categoryName }}</td>
                </tr>
              </table>
            </CommonItem>
            <CommonItem v-if="userSelect.desc === true" class="w-full mt-5">
              <div class="p-3 text-sm" v-html="product.productModel.productDescription"></div>
            </CommonItem>
            <div v-if="userSelect.review === true">
              <CommonItem class="w-full mt-5">
                <div class="p-3 text-sm" v-if="product.comment.length <= 0">
                  Chưa có đánh giá nào.
                </div>
                <div class="p-3 text-sm" v-if="product.comment.length > 0">
                  <ul>
                    <li
                      v-for="comment in product.comment"
                      :key="comment.commentId"
                      class="flex items-start justify-start gap-3"
                    >
                      <div>
                        <img
                          src="../../assets/images/default.png"
                          class="w-[60px] h-[60px] object-cover"
                          alt=""
                        />
                      </div>
                      <div class="flex flex-col items-start justify-start w-full">
                        <span class="-mt-1 text-cyanBlue">{{
                          comment.lastName +
                          ' ' +
                          (comment.firstName === null ? '' : comment.firstName)
                        }}</span>
                        <span class="text-sm">{{ comment.commentDetail }}</span>
                        <span class="w-full text-sm text-right cursor-pointer">Like</span>
                      </div>
                    </li>
                  </ul>
                </div>
              </CommonItem>
              <CommonItem class="w-full mt-5">
                <form action="" method="post" class="flex flex-col w-full mt-5">
                  <div class="mb-3">
                    <span class="text-sm">Ý kiến của bạn về sản phẩm</span>
                    <Textarea
                      class="border-solid border-[1px] border-gray-300"
                      placeholder=""
                      v-model="comment.commentDetail"
                      name="detail"
                    />
                  </div>
                  <div class="flex items-center justify-between w-full gap-3">
                    <div class="w-full mb-3">
                      <span class="text-sm"
                        >Họ tên của bạn <span class="text-lg align-middle text-brown">*</span>
                      </span>
                      <Input
                        type="text"
                        v-model="comment.commentName"
                        name="fullName"
                        placeholder=""
                      />
                    </div>
                    <div class="w-full mb-3">
                      <span class="text-sm"
                        >Email <span class="text-lg align-middle text-brown">*</span>
                      </span>
                      <Input
                        type="email"
                        v-model="comment.commentEmail"
                        name="email"
                        placeholder=""
                      />
                    </div>
                  </div>
                  <div class="flex items-center justify-end">
                    <Button
                      type="button"
                      @click.prevent="submitComment"
                      className="bg-dark-blue hover:bg-dark-blue-hover text-white hover:text-cyanBlue w-[200px] m-0"
                      name="comment"
                      text="Bình luận"
                    />
                  </div>
                </form>
              </CommonItem>
            </div>
          </div>
          <div class="col-span-1">
            <h2 class="w-full p-[8px] text-center bg-dark-blue text-white">Cách phối đồ</h2>
            <div class="flex flex-col gap-4 p-1 bg-white cursor-pointer">
              <ArticleSmall title="Phối đồ với " />
            </div>
          </div>
        </div>
      </div>
    </template>
  </BasePage>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import BasePage from '../auth/BasePage.vue'
import InputIncrement from '@/components/common/input/InputIncrement.vue'
import Button from '@/components/common/button/Button.vue'
import CommonItem from '@/components/common/CommonItem.vue'
import Input from '@/components/common/input/Input.vue'
import Textarea from '@/components/common/input/Textarea.vue'
import ArticleSmall from '@/components/common/ArticleSmall.vue'
import { useRoute, useRouter } from 'vue-router'
import render from '@/stores/modules/re-render'
import axios from 'axios'
const route = useRoute()
const router = useRouter()
const loading = ref(true)
const product = reactive({
  comment: [],
  productModel: {},
  productSize: 36,
  productQuantity: 1
})
const comment = reactive({
  productId: route.params.productId,
  commentDetail: '',
  commentName: '',
  commentEmail: ''
})
const activeImage = reactive({
  fileName: '',
  imageId: ''
})

const order = ref([])
const quantity = ref(0)
const count = 8
const userSelect = reactive({
  spec: true,
  desc: false,
  review: false
})
onMounted(async () => {
  await fetchData(route.params.categoryId, route.params.productId, product)
})

async function fetchData(categoryId, productId, product) {
  loading.value = true
  axios
    .get(`http://localhost:8088/api/products/detail/${categoryId}/${productId}`)
    .then((response) => {
      product.comment = response.data.commentList
      product.productModel = response.data.productModel
      activeImage.fileName = product.productModel.sourceImageModelList[0].fileName
      activeImage.imageId = product.productModel.sourceImageModelList[0].imageId
    })
    .catch((error) => {
      console.log(error)
    })
    .finally(() => (loading.value = false))
}
const productModel = reactive({
  productId: '',
  categoryId: '',
  productQuantity: 1,
  productSize: '',
  productName: '',
  productPrice: '',
  productPriceSale: ''
})

const onSubmit = (event, type) => {
  if (type == 'add') {
    let flag = false
    if (localStorage.getItem('order') !== null) {
      order.value = JSON.parse(localStorage.getItem('order'))
      order.value.forEach((element) => {
        if (element.productId === product.productModel.productId) {
          element.productQuantity += product.productQuantity
          flag = true
        }
      })
    }
    if (!flag) {
      productModel.categoryId = product.productModel.categoryId
      productModel.productId = product.productModel.productId
      productModel.productQuantity = product.productQuantity
      productModel.productSize = product.productSize
      productModel.productName = product.productModel.productName
      productModel.productPrice = product.productModel.productPrice
      productModel.productPriceSale = product.productModel.productPriceSale
      order.value.push(productModel)
    }

    localStorage.setItem('order', JSON.stringify(order.value))
    render()
  } else {
    if (product.productQuantity === null) {
      product.productQuantity = 1
    }
    if (product.productSize === null) {
      product.productSize = 36
    }
    order.value.push(product)
    localStorage.setItem('order', JSON.stringify(order.value))
    router.push({ name: 'Payment' })
    render()
  }
}
const submitComment = () => {
  let formData = new FormData()
  formData.append('commentModel', JSON.stringify(comment))
  axios
    .post('http://localhost:8088/api/comments/insert', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    })
    .then((response) => {
      console.log(response)
      comment.commentDetail = ''
      comment.commentName = ''
      comment.commentEmail = ''
      fetchData(route.params.categoryId, route.params.productId, product)
    })
    .catch((error) => {
      console.log(error)
    })
}
function formatPrice(value) {
  let val = (value / 1).toFixed(0).replace('.', ',')
  return val.toString().replace(/\B(?=(\d{3})+(?!\d))/g, '.')
}
const getImageUrl = (name) => {
  return new URL(`../../../../../image/api-image/${name}`, import.meta.url).href
}
const onActiveImage = (event, item) => {
  activeImage.fileName = item.fileName
  activeImage.imageId = item.imageId
}
function onUserSelect(event, userSelectStatus) {
  if (userSelectStatus === 'spec') {
    userSelect.spec = true
    userSelect.desc = false
    userSelect.review = false
  }
  if (userSelectStatus === 'desc') {
    userSelect.spec = false
    userSelect.desc = true
    userSelect.review = false
  }
  if (userSelectStatus === 'review') {
    userSelect.spec = false
    userSelect.desc = false
    userSelect.review = true
  }
}
</script>
<style lang="scss" scoped>
.active {
  color: #ff2347;
}

.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}
table {
  tr {
    td {
      padding-left: 20px;
      &.bg-gray {
      }
      line-height: 40px;
      &:first-child {
        width: 200px;
      }
    }
  }
}
ul {
  li {
    cursor: auto;
  }
}
.lds-facebook {
  div {
    background: black;
  }
}
</style>
