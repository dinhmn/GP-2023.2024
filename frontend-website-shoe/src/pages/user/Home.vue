<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage
    class="m-auto 2xl:w-[1280px] xl:w-[100%] container flex items-start justify-start flex-col min-h-[100vh]"
  >
    <template v-slot:body>
      <div class="flex items-center justify-center w-full my-4">
        <img
          src="../../assets/images/banner.png"
          alt="Banner"
          class="object-cover w-full h-[500px]"
        />
      </div>
      <div class="flex items-center justify-around w-full py-2 my-1 rounded-[4px] bg-slate-600">
        <div
          class="flex flex-col items-center justify-center transition-all scale-75 cursor-pointer hover:text-brown hover:scale-90"
          v-for="(item, index) in trademark"
          :key="index"
        >
          <img :src="getImageUrl(item.img)" :alt="item.title" />
          <p class="mt-2 text-xl">Giày {{ item.title }}</p>
        </div>
      </div>
      <BoxCommon>
        <template v-slot:title>
          <div class="w-full px-4 py-2 rounded bg-brown">
            <h3 class="text-2xl font-bold leading-9 tracking-widest uppercase">New Sale</h3>
          </div>
        </template>
        <template v-slot:detail>
          <div class="grid grid-cols-5 gap-5 py-4 mx-3">
            <div v-for="product in dataSale[0]" :key="product.productId">
              <router-link
                :to="{
                  name: 'DetailProduct',
                  params: { categoryId: product.categoryId, productId: product.productId }
                }"
              >
                <Item
                  :price="product.productPrice"
                  :priceSale="product.productPriceSale"
                  :quantitySold="Number(product.productQuantity)"
                  :productName="product.productName"
                  :src="product.fileName === null ? 'item.png' : product.fileName"
                  :alt="product.productName"
                />
              </router-link>
            </div>
          </div>
        </template>
      </BoxCommon>
      <div class="my-4 rounded-[4px] w-full bg-slate-600 shadow-custom">
        <div
          class="flex flex-col items-start justify-between"
          v-for="(item, index) in dataList"
          :key="index"
        >
          <div class="flex items-center justify-between w-full px-4 py-2 rounded">
            <h3 class="text-2xl font-bold leading-9 tracking-widest uppercase text-brown">
              Giày
              {{ item[0].categoryName }}
            </h3>
            <h3 class="flex text-lg font-bold align-middle cursor-pointer hover:text-brown">
              <router-link :to="{ name: 'Product' }"
                ><span class="mr-2">Xem tất cả</span>
              </router-link>
              <vue-feather class="w-6 h-6 translate-y-1" type="chevrons-right"></vue-feather>
            </h3>
          </div>
          <div class="grid grid-cols-5 gap-5 py-4 mx-3">
            <div v-for="product in item" :key="product.productId">
              <router-link
                :to="{
                  name: 'DetailProduct',
                  params: { categoryId: product.categoryId, productId: product.productId }
                }"
              >
                <Item
                  :price="product.productPrice"
                  :priceSale="product.productPriceSale"
                  :quantitySold="Number(product.productQuantity)"
                  :productName="product.productName"
                  :src="product.fileName === null ? 'item.png' : product.fileName"
                  :alt="product.productName"
                />
              </router-link>
            </div>
          </div>
        </div>
      </div>
      <BoxCommon>
        <template v-slot:title>
          <div class="flex items-center justify-between w-full px-4 py-2 rounded">
            <h3 class="text-2xl font-bold leading-9 tracking-widest uppercase text-brown">
              Tư vấn chọn mua
            </h3>
            <h3 class="flex text-lg font-bold align-middle cursor-pointer hover:text-brown">
              <span class="mr-2">Xem thêm</span>
              <vue-feather class="w-6 h-6 translate-y-1" type="chevrons-right"></vue-feather>
            </h3>
          </div>
        </template>
        <template v-slot:detail>
          <div class="grid w-full grid-cols-4 gap-6 py-4 pr-5 mx-3">
            <div v-for="item in article[0]" :key="item.articleId">
              <router-link :to="{ name: 'Article', params: { articleId: item.articleId } }">
                <Article
                  :title="item.articleName"
                  :src="item.fileName === null ? 'item.png' : item.fileName"
                  :alt="item.articleName"
                  classTitle="text-center"
                />
              </router-link>
            </div>
          </div>
        </template>
      </BoxCommon>
    </template>
  </BasePage>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import Item from '@/components/common/product/Item.vue'
import BoxCommon from '@/components/common/BoxCommon.vue'
import Article from '@/components/common/product/Article.vue'
import BasePage from '../auth/BasePage.vue'
import ProductService from '@/stores/modules/ProductService'
import axios from 'axios'
const trademark = [
  {
    img: 'sale-item.png',
    title: 'Sales sốc'
  },
  {
    img: 'nike.png',
    title: 'Nike'
  },
  {
    img: 'adidas.png',
    title: 'Adidas'
  },
  {
    img: 'mlb.png',
    title: 'MLB'
  },
  {
    img: 'vans.png',
    title: 'Vans'
  },
  {
    img: 'convert.png',
    title: 'Convert'
  },
  {
    img: 'lv.png',
    title: 'LV'
  }
]
const dataNike = reactive()
const dataAdidas = reactive()
const dataNB = reactive()
const dataMLB = reactive()
const dataConvert = reactive()
const dataSale = ref([])
const dataList = reactive([])
const article = reactive([])
onMounted(async () => {
  await getAllData(dataNike, 1, 5)
  await getAllData(dataAdidas, 2, 5)
  await getAllData(dataNB, 3, 5)
  await getAllData(dataMLB, 4, 5)
  await getAllData(dataConvert, 5, 5)
  await getAllDataSale(10)
  await getAllDataArticle()
  console.log(dataList)
})

async function getAllData(data, categoryId, limitItem) {
  try {
    const res = await ProductService.getProductByCategoryId('/init-home', categoryId, limitItem)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }
    data = result.data
    dataList.push(data)
  } catch (error) {
    console.log(error)
  }
}

async function getAllDataSale(limitItem) {
  try {
    const res = await ProductService.getProductSaleByCategoryId('/init-sale', limitItem)

    const result = {
      status: res.status + '-' + res.statusText,
      headers: res.headers,
      data: res.data
    }
    dataSale.value.push(result.data)
  } catch (error) {
    console.log(error)
  }
}

async function getAllDataArticle() {
  try {
    axios
      .get('http://localhost:8088/api/article/init/pageable?page_no=0&page_size=4')
      .then((response) => {
        article.push(response.data.articleDTOList)
        console.log(article)
      })
      .catch((error) => {
        console.log(error)
      })
  } catch (error) {
    console.log(error)
  }
}
const getImageUrl = (name) => {
  return new URL(`../../assets/images/trademark/${name}`, import.meta.url).href
}
</script>
<style lang="scss">
section {
  color: white;
  .item {
    overflow: hidden;
    margin: 0 auto;
  }
  .item img {
    overflow: hidden;
    transition: 0.4s all ease-in-out;
    transform: scale(0.95);
  }
  .item:hover img {
    transform: scale(1);
  }
}
ul {
  li.selected {
    padding: 2px 10px;
    background-color: #cc334d;
    border-radius: 4px;
  }
}
</style>
