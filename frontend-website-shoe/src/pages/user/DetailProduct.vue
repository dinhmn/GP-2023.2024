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
            Giày Gucci Men’s Screener GG Sneaker Like Auth
          </h2>
          <div class="flex items-start justify-between w-full text-sm">
            <div>
              <p class="text-lg">Mã sản phẩm : <span class="text-sm"> 001</span></p>
              <p class="mt-2 text-lg">Tình trạng: <span class="text-sm"> Còn hàng</span></p>
            </div>
            <p class="text-lg">Đã bán: <span class="text-sm"> 12</span></p>
          </div>
          <div class="text-base">
            <span>Giá:</span> <span class="ml-3 mr-2 text-sm line-through text-brown">1200000đ</span
            ><span class="text-2xl">1000000đ</span>
          </div>
          <div class="w-full mb-3 mr-3">
            <span class="text-lg"
              >Size <span class="text-lg align-middle text-brown">*</span>
            </span>
            <Select name="city" :value="1">
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
              v-model="quantity"
              :value="quantity"
            />
          </div>
          <div class="flex w-full gap-10">
            <Button
              @click.prevent="onSubmit"
              type="button"
              className="bg-brown text-white w-full m-0"
              name="addCart"
              text="Thêm vào giỏ hàng"
            />
            <Button
              @click.prevent="onSubmit"
              type="button"
              className="bg-brown text-white w-full m-0"
              name="buyNow"
              text="Mua ngay"
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
                <td class="opacity-80 bg-gray bg-slate-500">Quà tặng:</td>
                <td class="opacity-80 bg-slate-700">Full box + tax + bill, Tặng tất</td>
              </tr>
              <tr>
                <td class="opacity-80 bg-gray bg-slate-500">Thương hiệu:</td>
                <td class="opacity-80 bg-slate-700">Gucci</td>
              </tr>
              <tr>
                <td class="rounded-bl-lg opacity-80 bg-gray bg-slate-500">Loại hàng:</td>
                <td class="rounded-br-lg opacity-80 bg-slate-700">Like auth, Pk Gold</td>
              </tr>
            </table>
          </CommonItem>
          <CommonItem title="Mô tả sản phẩm" class="mt-5 h-[350px] w-full">
            <div class="p-3 text-sm">
              Giày Gucci Men’s Screener GG Sneaker Like Auth với thiết kế đẹp, tinh tế & màu sắc vô
              cùng dễ phối đồ. Vậy nên đôi giày này trở nên phổ biến, mang tính biểu tượng và được
              rất nhiều giới trẻ yêu thích. Và nếu bạn cũng là một người đam mê dòng sneaker dễ
              mang, dễ phố đồ thì không nên bỏ qua mẫu giày siêu phẩm này đâu nhé! Dưới đây là một
              số hình ảnh của đôi Giày Gucci Men’s Screener GG Sneaker Like Auth tại TyHi Sneaker
              (hàng chuẩn Auth bản xịn nhất thị trường). – Cam kết bản Fake tốt nhất từ nhà máy tốt
              nhất Trung Quốc. NOTE: Đây là hàng VIP từ nhà máy Trung Quốc (giống 99,99% Auth, chất
              da thật như Auth) – các shop đang bán 3-4 triệu (bạn có thể check) – Tyhi Sneaker ôm
              được lô do Hải Quan thu nên Sale giá rẻ nhất. Size số có hạn nhé. Giày Gucci Men’s
              Screener GG Sneaker Like Auth với thiết kế đẹp, tinh tế & màu sắc vô cùng dễ phối đồ.
              Vậy nên đôi giày này trở nên phổ biến, mang tính biểu tượng và được rất nhiều giới trẻ
              yêu thích. Và nếu bạn cũng là một người đam mê dòng sneaker dễ mang, dễ phố đồ thì
              không nên bỏ qua mẫu giày siêu phẩm này đâu nhé! Dưới đây là một số hình ảnh của đôi
              Giày Gucci Men’s Screener GG Sneaker Like Auth tại TyHi Sneaker (hàng chuẩn Auth bản
              xịn nhất thị trường). – Cam kết bản Fake tốt nhất từ nhà máy tốt nhất Trung Quốc.
              NOTE: Đây là hàng VIP từ nhà máy Trung Quốc (giống 99,99% Auth, chất da thật như Auth)
              – các shop đang bán 3-4 triệu (bạn có thể check) – Tyhi Sneaker ôm được lô do Hải Quan
              thu nên Sale giá rẻ nhất. Size số có hạn nhé.
            </div>
            <div class="flex items-center justify-center w-full">
              <Button className="bg-brown w-[200px] text-white m-0" name="next" text="Xem tiếp" />
            </div>
          </CommonItem>
          <CommonItem title="Đánh giá" class="w-full mt-5">
            <div class="p-3 text-sm">Chưa có đánh giá nào.</div>
          </CommonItem>
          <CommonItem :title="'Đánh giá sản phẩm: ' + productName" class="w-full mt-5">
            <form
              @submit.prevent="onSubmit"
              action=""
              method="post"
              class="flex flex-col w-full mt-5"
            >
              <div class="mb-3">
                <span class="text-sm">Nhận xét của bạn về sản phẩm(nếu có) </span>
                <Textarea placeholder="" name="detail" />
              </div>
              <div class="flex items-center justify-between w-full gap-20">
                <div class="w-full mb-3">
                  <span class="text-sm"
                    >Tên <span class="text-lg align-middle text-brown">*</span>
                  </span>
                  <Input type="text" name="fullName" placeholder="" />
                </div>
                <div class="w-full mb-3">
                  <span class="text-sm"
                    >Email <span class="text-lg align-middle text-brown">*</span>
                  </span>
                  <Input type="email" name="email" placeholder="" />
                </div>
              </div>
              <div class="flex items-center justify-end">
                <Button
                  type="submit"
                  className="bg-brown text-white w-[200px] m-0"
                  name="comment"
                  text="Bình luận"
                />
              </div>
            </form>
          </CommonItem>
        </div>
        <div class="col-span-1">
          <h2 class="w-full mt-2 p-[12px] rounded text-base font-bold text-center bg-[#ff4a684d]">
            Thông tin hữu ích
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
import { ref } from 'vue'
import BasePage from '../auth/BasePage.vue'
import InputIncrement from '@/components/common/input/InputIncrement.vue'
import Button from '@/components/common/button/Button.vue'
import CommonItem from '@/components/common/CommonItem.vue'
import Input from '@/components/common/input/Input.vue'
import Textarea from '@/components/common/input/Textarea.vue'
import ArticleSmall from '@/components/common/ArticleSmall.vue'
import Select from '@/components/common/input/Select.vue'
const quantity = ref(0)
const count = 8
const productName = 'Giày Gucci Men’s Screener GG Sneaker Like Auth'
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
