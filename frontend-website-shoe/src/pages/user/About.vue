<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage class="text-white">
    <template v-slot:body>
      <h1 class="block w-full py-3 text-3xl font-bold text-center text-cyan-900">
        Giới thiệu về LOGO.
      </h1>
      <div
        class="flex flex-col items-center justify-start pb-3 border-b-2 border-gray-600 border-solid"
      >
        <h2 class="w-full mb-2 text-2xl font-bold text-left">Về chúng tôi</h2>
        <div class="flex items-start justify-center w-full h-full text-lg leading-8 text-black">
          Sneaker Daily là một trong những điểm bán lẻ uy tín, các sản phẩm thời trang chính hãng
          tịa Việt Na. Được thành lập vào năm 2022, Sneaker Daily mang sứ mệnh đưa người trẻ sneaker
          đến gần hơn với thế giới về giày, cũng như lan tỏa đam mê thời trang đến với cộng đồng.
          Bên cạnh tiêu chí vì khách hàng, chúng tôi luôn cố gắng mang đến sự đa dạng về sản phẩm và
          thương hiệu. Cũng như cải thiện chất lựn dịch vụ để mang lại sự an tâm, tin cậy cho khách
          hàng. Sneaker Daily cam kết luôn đưa đến Quý khách nhưng xsanr phẩm chính hãng, tôn trọng
          với công sức của nhà sản xuất.
        </div>
      </div>
      <div class="my-3">
        <h2 class="w-full text-2xl font-bold text-left text-cyan-900">Video giới thiệu sản phẩm</h2>
        <div class="mt-4">
          <video class="w-full h-full rounded" controls>
            <source src="../../assets/videos/video-2.mp4" type="video/mp4" />
          </video>
        </div>
      </div>
      <div class="grid items-center justify-center w-full gap-5 mb-3">
        <img
          src="../../assets/images/banner-3.png"
          alt="banner 3"
          class="object-cover w-full h-full"
        />
        <img
          src="../../assets/images/banner-4.png"
          alt="banner 4"
          class="object-cover w-full h-full"
        />
        <img
          src="../../assets/images/banner-2.png"
          alt="banner 2"
          class="object-cover w-full h-full"
        />
        <img
          src="../../assets/images/banner-5.png"
          alt="banner 5"
          class="object-cover w-full h-full"
        />
        <img
          src="../../assets/images/banner-1.png"
          alt="banner 1"
          class="object-cover w-full h-full"
        />
      </div>
    </template>
  </BasePage>
</template>

<script setup>
import BasePage from '../auth/BasePage.vue'
</script>

<style scoped></style>
