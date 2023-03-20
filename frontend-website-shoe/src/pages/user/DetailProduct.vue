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
        class="flex items-start justify-between w-full gap-10 px-6 py-5 my-1 text-lg font-bold text-white rounded-[4px] bg-slate-600"
      >
        <div class="flex flex-col items-center justify-start w-[40%]">
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
        <div class="flex flex-col items-start gap-5 w-[60%] h-full">
          <h2>Giày Gucci Men’s Screener GG Sneaker Like Auth</h2>
          <div class="text-base">
            <p>Mã sản phẩm : N/A</p>
            <p>Đã bán: 02</p>
          </div>
          <div>Tình trạng: Còn hàng</div>
          <div class="flex flex-col w-full gap-3 px-3 py-3 text-base rounded-lg bg-brown">
            <span>Giá sale: <spanc class="line-through">129500đ</spanc></span>
            <span>Giá gốc: 647000đ</span>
            <span>Tiết kiệm: 647000đ</span>
          </div>
          <div class="flex items-center justify-start gap-5">
            Size:
            <ul class="flex items-center justify-around gap-3">
              <li class="active">36</li>
              <li>37</li>
              <li>38</li>
              <li>39</li>
              <li>40</li>
              <li>41</li>
              <li>42</li>
              <li>43</li>
              <li>44</li>
            </ul>
          </div>
          <div class="flex">
            <span>Chọn số lượng: </span>
            <InputIncrement
              classChild="text-sm p-0 "
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
    </template>
  </BasePage>
</template>
<script setup>
import { ref } from 'vue'
import BasePage from '../auth/BasePage.vue'
import InputIncrement from '@/components/common/input/InputIncrement.vue'
import Button from '@/components/common/button/Button.vue'
const quantity = ref(0)
</script>
<style lang="scss">
.active {
  color: #ff2347;
}

.image {
  width: 100%;
  height: 100%;
  object-fit: cover;
  cursor: pointer;
}
ul {
  li {
    font-size: 14px;
    padding: 0px 8px;
    border-radius: 5px;
    background-color: #ff23484d;
    &.active {
      background-color: #ff2347;
      color: white;
    }
  }
}
</style>
