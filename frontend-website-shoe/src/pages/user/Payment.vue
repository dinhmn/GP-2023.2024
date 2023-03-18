<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <section
    class="mt-3 text-white m-auto 2xl:w-[1280px] xl:w-[100%] container flex items-start justify-start flex-col min-h-[100vh]"
  >
    <div class="flex items-start justify-between w-full gap-10 py-2 my-1 rounded-[4px]">
      <div class="cart bg-slate-600 w-[60%] p-5 rounded-lg">
        <div>
          <img src="../../assets/images/default.png" alt="" />
          <div>
            <title>Giày nike</title>
            <h5>Size: 36</h5>
            <span>Màu: </span>
            <p>750.000đ</p>
          </div>
          <div>
            <input class="text-gray-800" type="quantity" name="quantity" value="1" />
          </div>
        </div>
        <div class="flex items-center justify-between">
          <span>Tạm tính</span>
          <span>750.000đ</span>
        </div>
      </div>
      <div class="order w-[40%] bg-slate-600 p-5 rounded-lg">
        <h2 class="my-2 text-xl font-bold uppercase text-brown">Thanh toán và giao hàng</h2>
        <!-- Order -->
        <form action="post w-full">
          <!-- Form Payment -->
          <div>
            <div class="mb-4">
              <span class="text-sm"
                >Họ và tên <span class="text-lg align-middle text-brown">*</span>
              </span>
              <Input type="text" name="fullName" placeholder="Họ tên của bạn" classChild="" />
            </div>
            <div class="mb-3">
              <span class="text-sm"
                >Số điện thoại <span class="text-lg align-middle text-brown">*</span>
              </span>
              <Input type="text" name="phone" placeholder="Số điện thoại" />
            </div>
            <div class="flex items-center justify-between gap-3">
              <div class="mb-3">
                <span class="text-sm"
                  >Tỉnh/Thành phố <span class="text-lg align-middle text-brown">*</span>
                </span>
                <Input type="text" name="city" placeholder="Option" />
              </div>
              <div class="mb-3">
                <span class="text-sm"
                  >Quận/Huyện <span class="text-lg align-middle text-brown">*</span>
                </span>
                <Input type="text" name="country" placeholder="Option" />
              </div>
            </div>
            <div class="mb-3">
              <span class="text-sm"
                >Địa chỉ <span class="text-lg align-middle text-brown">*</span>
              </span>
              <Input type="text" name="address" placeholder="Ví dụ: Số 20, Ngõ 90" />
            </div>
            <div class="mb-3">
              <span class="text-sm">Ghi chú đơn hàng(nếu có) </span>
              <Textarea
                placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay địa chỉ chi tiết hơn."
                name="note"
              />
            </div>
          </div>
          <!-- Total Payment -->
          <div class="flex flex-col gap-2 total">
            <div
              v-for="(item, index) in information"
              :key="index"
              class="flex items-center justify-between"
            >
              <span>{{ item.title }}:</span>
              <span class="text-sm">{{ item.value }}</span>
            </div>
          </div>
          <!-- Payments. -->
          <div>
            <Radio id="receivePayment" text="Nhận hàng thanh toán (COD)" />
            <Radio id="bankTransfer" text="Chuyển khoản ngân hàng" />
            <p class="relative mb-2">Text</p>
          </div>

          <Button
            type="submit"
            className="bg-black text-white w-full m-0"
            name="login"
            text="Đặt hàng"
          />
        </form>
        <!-- Form Payment -->
      </div>
    </div>
  </section>
</template>
<script setup>
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Radio from '@/components/common/input/Radio.vue'
import Textarea from '@/components/common/input/Textarea.vue'
const information = [
  {
    title: 'Tạm tính',
    value: '750.000đ'
  },
  {
    title: 'Giao hàng',
    value: 'Giao hàng miễn phí'
  },
  {
    title: 'Tổng',
    value: '750.000đ'
  }
]
</script>
<style lang="scss">
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
