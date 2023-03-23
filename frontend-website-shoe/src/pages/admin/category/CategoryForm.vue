<template lang="">
  <div>
    <div class="text-white">
      <Button
        type="button"
        text="Back"
        id="addCategory"
        className="bg-green-700 hover:bg-green-600 -ml-[2px] text-white font-bold"
      />
    </div>
    <strong
      class="block w-full py-2 my-3 text-xl text-center text-white uppercase rounded-md bg-slate-500"
      >Add new category</strong
    >
    <form leave-to-class="w-full post">
      <!-- Form Payment -->
      <div class="w-fill">
        <!-- Form full name. -->
        <div class="mb-4">
          <span class="text-sm"
            >Họ và tên <span class="text-lg align-middle text-brown">*</span>
          </span>
          <Input type="text" name="fullName" placeholder="Họ tên của bạn" />
        </div>
        <!-- Form phone. -->
        <div class="w-full mb-3">
          <span class="text-sm"
            >Số điện thoại <span class="text-lg align-middle text-brown">*</span>
          </span>
          <Input type="text" name="phone" placeholder="Số điện thoại" />
        </div>
        <!-- Form city and country. -->
        <div class="flex items-center justify-between w-full gap-2">
          <div class="w-full mb-3 mr-3">
            <span class="text-sm"
              >Tỉnh/Thành phố <span class="text-lg align-middle text-brown">*</span>
            </span>
            <!-- <Select name="city" :value="1">
                <template v-slot:option>
                  <option v-for="item in count" :selected="item === 1" :key="item" :value="item">
                    Option {{ item }}
                  </option>
                </template>
              </Select> -->
          </div>
          <div class="w-full mb-3 ml-3">
            <span class="text-sm"
              >Quận/Huyện <span class="text-lg align-middle text-brown">*</span>
            </span>
            <!-- <Select name="country" v-model="data.country" :value="1">
                <template v-slot:option>
                  <option v-for="item in count" :key="item" :selected="item === 1" :value="item">
                    Option {{ item }}
                  </option>
                </template>
              </Select> -->
          </div>
        </div>
        <!-- Form address. -->
        <div class="mb-3">
          <span class="text-sm"
            >Địa chỉ <span class="text-lg align-middle text-brown">*</span>
          </span>
          <Input type="text" name="address" placeholder="Ví dụ: Số 20, Ngõ 90" />
        </div>
        <!-- Form note detail. -->
        <div class="mb-3">
          <span class="text-sm">Ghi chú đơn hàng(nếu có) </span>
          <Textarea
            placeholder="Ghi chú về đơn hàng, ví dụ: thời gian hay địa chỉ chi tiết hơn."
            name="note"
          />
        </div>
      </div>
      <!-- Button submit order. -->
      <Button
        @click.prevent="onSubmit"
        type="button"
        className="bg-brown text-white w-full m-0"
        name="login"
        text="Đặt hàng"
      />
    </form>
  </div>
</template>
<script setup>
import Input from '@/components/common/input/Input.vue'
import Button from '@/components/common/button/Button.vue'
import Textarea from '@/components/common/input/Textarea.vue'
</script>
<style lang=""></style>
