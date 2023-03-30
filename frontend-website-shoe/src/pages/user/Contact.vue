<template lang="">
  <BasePage class="flex flex-col items-center justify-center gap-5 text-white">
    <template v-slot:body>
      <div class="grid w-full grid-cols-2 gap-5">
      <div class="col-span-1">
        <VMap style="height: 400px;">
          <VMapOsmTileLayer />
        </VMap>
      </div>
      <div class="w-full h-full col-span-1">
        <div>
          <h1 class="font-[400] text-2xl leading-10 m-auto py-2 -tracking-tighter">Get in touch with us</h1>
        </div>
        <div>
          <span></span>
        </div>
        <form class="w-full ">
          <!-- Form Payment -->
          <div class="flex flex-col w-full gap-2 ">
            <!-- Form full name. -->
            <div class="mb-4">
              <span class="text-sm"
                >Họ và tên <span class="text-lg align-middle text-brown">*</span>
              </span>
              <Input
                v-model="state.fullName"
                type="text"
                name="fullName"
                placeholder="Họ tên của bạn"
              />
            </div>
            <!-- Form email address. -->
            <div class="mb-4">
              <span class="text-sm"
                >Email <span class="text-lg align-middle text-brown">*</span>
              </span>
              <Input
                type="email"
                name="address"
                placeholder="abc@gmail.com"
                v-model="state.email"
              />
              <span v-if="v$.email.$error">
              {{v$.value.email.email.$message}}
              </span>
            </div>
            <!-- Form note detail. -->
            <div class="mb-4">
              <span class="text-sm">Tin nhắn(nếu có)</span>
              <Textarea
                placeholder="Ghi chú về thông tin đơn hàng..."
                name="note"
                v-model="state.message"
              />
              <span v-if="v$.message.$error">
              {{v$.value.message.message.$message}}
              </span>
            </div>
          </div>
          <!-- Button submit order. -->
          <Button
            @click="onSubmit"
            type="button"
            className="bg-[#6088EE] hover:bg-[#6892ff] text-white w-full m-0"
            name="sendMyMessage"
            text="Liên hệ"
          />
        </form>
      </div>
    </div>
    </template>
  </BasePage>
</template>
<script setup>
import { reactive, computed } from 'vue';
import useValidate from '@vuelidate/core'
import { required, email, minLength, sameAs } from '@vuelidate/validators'
import BasePage from '../auth/BasePage.vue';
import Button from '@/components/common/button/Button.vue';
import Input from '@/components/common/input/Input.vue';
import Textarea from '@/components/common/input/Textarea.vue';
import { VMap, VMapOsmTileLayer } from 'vue-map-ui';

const state = reactive({
  fullName: '',
  email: '',
  message: ''
});
const rules =  computed(() => {
return {
    fullName: {required, minLength: minLength(6)},
  email: {required, email},
  message: {required}
  }})

const v$ = useValidate(rules, state);

const onSubmit = () => {
  console.log(state);
  console.log(v$.value.message);
console.log(v$.value.email);
console.log(v$.value.fullName);
// console.log(v$.value.email.$error);
console.log(v$.value.fullName.$error);
  if (!v$.value.email.$error) {
        // if ANY fail validation
        alert('Form successfully submitted.')
      } else {
        alert('Form failed validation')
      }
}
</script>
<style lang=""></style>
