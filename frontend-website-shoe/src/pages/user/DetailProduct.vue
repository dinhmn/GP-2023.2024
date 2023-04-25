<template lang="">
  <BasePage>
    <template v-slot:body>
      <div
        class="flex items-center justify-start w-full px-6 py-2 my-1 text-lg font-bold text-white rounded-[4px] bg-slate-600"
      >
        <span class="pointer">Trang chủ</span>
        <span><vue-feather class="w-5 h-5 translate-y-1" type="chevron-right"></vue-feather></span>
        <span class="pointer active">Tên sản phẩm </span>
      </div>
      <div
        class="grid grid-cols-5 w-full gap-10 px-6 py-5 my-1 text-lg font-bold text-white rounded-[4px] bg-slate-600"
      >
        <div class="flex flex-col items-center justify-start col-span-2">
          <div class="image item-select">
            <img src="../../assets/images/default.png" alt="" />
          </div>
          <div class="flex items-center justify-around gap-2 mt-3">
            <div class="image">
              <img src="../../assets/images/default.png" alt="" />
            </div>
            <div class="image">
              <img src="../../assets/images/default.png" alt="" />
            </div>
            <div class="image">
              <img src="../../assets/images/default.png" alt="" />
            </div>
            <div class="image">
              <img src="../../assets/images/default.png" alt="" />
            </div>
          </div>
        </div>
        <div class="flex flex-col items-start w-full h-full col-span-3 gap-5">
          <h2 class="w-full p-[16px] rounded text-base font-bold text-left bg-[#ff4a684d]">
            {{ product.productModel.productName }}
          </h2>
          <div class="flex items-start justify-between w-full text-sm">
            <div>
              <p class="text-lg">
                Product number : <span class="text-sm"> {{ product.productModel.productId }}</span>
              </p>
              <p class="mt-2 text-lg">
                Status:
                <span class="text-sm">{{
                  product.productModel.quantity > 0 ? 'Stocking' : 'Out of stock'
                }}</span>
              </p>
            </div>
            <p class="text-lg">
              Quantity: <span class="text-sm">{{ product.productModel.quantity }}</span>
            </p>
          </div>
          <div class="text-base">
            <span>Price:</span>
            <span class="ml-3 mr-2 text-sm line-through text-brown"
              >{{ formatPrice(product.productModel.productPrice) }}đ</span
            ><span class="text-2xl">{{ formatPrice(product.productModel.productPriceSale) }}đ</span>
          </div>
          <div class="w-full mb-3 mr-3">
            <span class="text-lg"
              >Size <span class="text-lg align-middle text-brown">*</span>
            </span>
            <Select name="productSize" v-model="product.productSize" :modelValue="Number(38)">
              <template v-slot:option>
                <option
                  class="text-black"
                  v-for="item in count"
                  :selected="item === 1"
                  :key="item"
                  :value="35 + item"
                >
                  {{ item + 35 }}
                </option>
              </template>
            </Select>
          </div>
          <div class="flex h-[33px]">
            <span class="text-lg w-[150px]">Số lượng: </span>
            <InputIncrement
              classChild="text-sm p-0 w-full"
              name="quantity"
              v-model="product.productQuantity"
              :value="quantity"
            />
          </div>
          <div class="flex w-full gap-10">
            <Button
              @click.prevent="onSubmit($event, 'add')"
              type="button"
              className="bg-dark-blue hover:bg-dark-blue-hover text-cyanBlue w-full m-0"
              name="addCart"
              text="Add to cart"
            />
            <Button
              @click.prevent="onSubmit($event, 'buy')"
              type="button"
              className="bg-dark-blue hover:bg-dark-blue-hover text-cyanBlue w-full m-0"
              name="buyNow"
              text="Shop Now"
            />
          </div>
        </div>
      </div>
      <div
        class="grid grid-cols-5 mb-3 w-full gap-10 px-6 py-5 my-1 font-bold text-white rounded-[4px] bg-slate-600"
      >
        <div class="col-span-4">
          <CommonItem title="Thông số kĩ thuật">
            <table class="w-full gap-5 mt-3">
              <tr>
                <td class="rounded-tl-lg opacity-80 bg-gray bg-slate-500">Size:</td>
                <td class="rounded-tr-lg opacity-80 bg-slate-700">
                  36, 37, 38, 39, 40, 41, 42, 43
                </td>
              </tr>
              <tr>
                <td class="opacity-80 bg-gray bg-slate-500">Gift:</td>
                <td class="opacity-80 bg-slate-700">Full box + tax + bill, Tặng tất</td>
              </tr>
              <tr>
                <td class="opacity-80 bg-gray bg-slate-500">Trademark:</td>
                <td class="opacity-80 bg-slate-700">{{ product.productModel.categoryName }}</td>
              </tr>
            </table>
          </CommonItem>
          <CommonItem title="Mô tả sản phẩm" class="mt-5 h-[350px] w-full">
            <div class="p-3 text-sm">
              {{ product.productModel.productDescription }}
            </div>
          </CommonItem>
          <CommonItem title="Đánh giá" class="w-full mt-5">
            <div class="p-3 text-sm" v-if="product.comment.length <= 0">Chưa có đánh giá nào.</div>
            <div class="p-3 text-sm" v-if="product.comment.length > 0">
              <ul class="cursor-none">
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
                      comment.lastName + ' ' + (comment.firstName === null ? '' : comment.firstName)
                    }}</span>
                    <span class="text-sm">{{ comment.commentDetail }}</span>
                    <span class="w-full text-sm text-right">Like</span>
                  </div>
                </li>
              </ul>
            </div>
          </CommonItem>
          <CommonItem :title="'Đánh giá sản phẩm: ' + productName" class="w-full mt-5">
            <form
              @submit.prevent="onSubmit($event, 'comment')"
              action=""
              method="post"
              class="flex flex-col w-full mt-5"
            >
              <div class="mb-3">
                <span class="text-sm">Your comment(If any) </span>
                <Textarea placeholder="" v-model="comment.commentDetail" name="detail" />
              </div>
              <div class="flex items-center justify-between w-full gap-3">
                <div class="w-full mb-3">
                  <span class="text-sm"
                    >Name <span class="text-lg align-middle text-brown">*</span>
                  </span>
                  <Input type="text" v-model="comment.commentName" name="fullName" placeholder="" />
                </div>
                <div class="w-full mb-3">
                  <span class="text-sm"
                    >Email <span class="text-lg align-middle text-brown">*</span>
                  </span>
                  <Input type="email" v-model="comment.commentEmail" name="email" placeholder="" />
                </div>
              </div>
              <div class="flex items-center justify-end">
                <Button
                  type="button"
                  @click.prevent="submitComment"
                  className="bg-dark-blue hover:bg-dark-blue-hover text-cyanBlue w-[200px] m-0"
                  name="comment"
                  text="Comment"
                />
              </div>
            </form>
          </CommonItem>
        </div>
        <div class="col-span-1">
          <h2 class="w-full mt-2 p-[12px] rounded text-base font-bold text-center bg-dark-blue">
            Useful information
          </h2>
          <div class="flex flex-col gap-4 cursor-pointer">
            <ArticleSmall title="Phối đồ với " />
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
import Select from '@/components/common/input/Select.vue'
import { useRoute } from 'vue-router'
import render from '@/stores/modules/re-render'
import axios from 'axios'
const route = useRoute()
// const cart = reactive({
//   productName: '',
//   productSize: '36',
//   productQuantity: '',
//   productPrice: '',
//   productColor: ''
// })
const product = reactive({
  comment: [],
  productModel: {}
})
const comment = reactive({
  productId: route.params.productId,
  commentDetail: '',
  commentName: '',
  commentEmail: ''
})

const order = ref([])
const quantity = ref(0)
const count = 8
const productName = 'Giày Gucci Men’s Screener GG Sneaker Like Auth'
onMounted(async () => {
  await fetchData(route.params.categoryId, route.params.productId, product)
})

async function fetchData(categoryId, productId, product) {
  axios
    .get(`http://localhost:8088/api/products/detail/${categoryId}/${productId}`)
    .then((response) => {
      product.comment = response.data.commentList
      product.productModel = response.data.productModel
    })
    .catch((error) => {
      console.log(error)
    })
}

const onSubmit = (event, type) => {
  if (type == 'add') {
    if (window.localStorage.getItem('order') !== null) {
      order.value = JSON.parse(window.localStorage.getItem('order'))
    }
    order.value.push(product)
    localStorage.setItem('order', JSON.stringify(order.value))
    console.log(order)
  }
  render()
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
</style>
