<!-- eslint-disable vue/multi-word-component-names -->
<template lang="">
  <BasePage
    class="m-auto 2xl:w-[1280px] xl:w-[100%] container flex items-start justify-start flex-col min-h-[100vh]"
  >
    <template v-slot:body>
      <div v-if="loading" class="flex items-center justify-center w-full h-[100vh]">
        <div class="lds-facebook">
          <div></div>
          <div></div>
          <div></div>
        </div>
      </div>
      <div class="w-full" v-else>
        <div class="flex items-center justify-center w-full my-4">
          <img
            src="../../assets/images/banner.png"
            alt="Banner"
            class="object-cover w-full h-[500px]"
          />
        </div>
        <div
          class="flex items-center justify-around w-full py-2 my-1 rounded-[4px] bg-[#F8F8F8] text-cyan-800"
        >
          <div
            class="relative flex flex-col items-center justify-center transition-all cursor-pointer -z-1 hover:text-brown"
            v-for="(item, index) in trademark"
            :key="index"
          >
            <img class="w-[80px]" :src="getImageUrl(item.img)" :alt="item.title" />
            <p class="mt-2 text-base">Giày {{ item.title }}</p>
          </div>
        </div>
        <BoxCommon>
          <template v-slot:title>
            <div class="w-full px-4 py-2 text-cyan-800">
              <h3
                class="text-2xl font-bold leading-9 tracking-tight uppercase border-b-cyan-800 border-b-[2px] border-solid"
              >
                Sản phẩm được giảm giá
              </h3>
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
                  >
                    <template v-slot:imageChild>
                      <img
                        class="object-cover w-full h-full max-h-[230px]"
                        :src="getImageUrl(product.fileName)"
                        :alt="product.productName"
                      />
                    </template>
                  </Item>
                </router-link>
              </div>
            </div>
          </template>
        </BoxCommon>
        <div class="my-4 rounded-[4px] w-full bg-[#F8F8F8] shadow-custom">
          <div
            class="flex flex-col items-start justify-between"
            v-for="(item, index) in dataNew"
            :key="index"
          >
            <div class="flex items-center justify-between w-full px-4 py-2">
              <h3
                class="text-2xl font-bold leading-9 tracking-widest uppercase text-cyan-800 border-b-cyan-800 border-b-[2px] border-solid"
              >
                Sản phẩm mới
              </h3>
              <h3
                class="flex text-lg font-bold align-middle transition-all cursor-pointer text-cyan-800 hover:text-cyanBlue"
              >
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
                    :src="product.fileName"
                    :alt="product.productName"
                    :srcImage="product.fileName"
                  >
                    <template v-slot:imageChild>
                      <img
                        class="object-cover w-full h-[230px]"
                        :src="getImageUrl(product.fileName)"
                        :alt="product.productName"
                      />
                    </template>
                  </Item>
                </router-link>
              </div>
            </div>
          </div>
        </div>
        <BoxCommon>
          <template v-slot:title>
            <div class="flex items-center justify-between w-full px-4 py-2 rounded">
              <h3
                class="text-2xl font-bold leading-9 tracking-widest uppercase text-cyan-800 border-b-cyan-800 border-b-[2px] border-solid"
              >
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
                <router-link
                  :to="{
                    name: 'Article',
                    params: { articleId: item.articleId }
                  }"
                >
                  <Article
                    :title="item.articleName"
                    :src="item.fileName === null ? 'item.png' : item.fileName"
                    :alt="item.articleName"
                    classTitle="text-center"
                  >
                    <template v-slot:articleChild>
                      <img
                        class="object-cover w-full h-[200px]"
                        :src="getImageUrl(item.fileName === null ? 'item.png' : item.fileName)"
                        :alt="item.articleName"
                      />
                    </template>
                  </Article>
                </router-link>
              </div>
            </div>
          </template>
        </BoxCommon>
      </div>
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
const dataNew = ref([])
const dataSale = ref([])
const article = reactive([])
const loading = ref(true)
onMounted(async () => {
  await getAllDataSale(10, loading)
  await getAllDataNew(15, loading)
  await getAllDataArticle(loading)
})

async function getAllDataSale(limitItem, loading) {
  loading.value = true
  try {
    await ProductService.getProductSaleByCategoryId('/init-sale', limitItem)
      .then((res) => {
        const result = {
          status: res.status + '-' + res.statusText,
          headers: res.headers,
          data: res.data
        }

        dataSale.value.push(result.data)
      })
      .catch((error) => {
        console.log(error)
      })
      .finally(() => (loading.value = false))
  } catch (error) {
    console.log(error)
  }
}

async function getAllDataNew(limitItem, loading) {
  loading.value = true
  try {
    await ProductService.getProductSaleByCategoryId('/init-new', limitItem)
      .then((res) => {
        const result = {
          status: res.status + '-' + res.statusText,
          headers: res.headers,
          data: res.data
        }

        dataNew.value.push(result.data)
      })
      .catch((error) => {
        console.log(error)
      })
      .finally(() => (loading.value = false))
  } catch (error) {
    console.log(error)
  }
}

async function getAllDataArticle(loading) {
  loading.value = true
  try {
    axios
      .get('http://localhost:8088/api/article/init/pageable?page_no=0&page_size=4')
      .then((response) => {
        article.push(response.data.articleDTOList)
      })
      .catch((error) => {
        console.log(error)
      })
      .finally(() => (loading.value = false))
  } catch (error) {
    console.log(error)
  }
}
const getImageUrl = (root) => {
  return new URL(`../../../../../image/api-image/${root}`, import.meta.url).href
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
  }
}
ul {
  li.selected {
    padding: 2px 10px;
    background-color: #cc334d;
    border-radius: 4px;
  }
}
.lds-facebook > div {
  background: black;
}
</style>
