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
          <img :src="this.getImageUrl(item.img)" :alt="item.title" />
          <p class="mt-2 text-xl">Giày {{ item.title }}</p>
        </div>
      </div>
      <box>
        <template v-slot:title>
          <div class="w-full px-4 py-2 rounded bg-brown">
            <h3 class="text-2xl font-bold leading-9 tracking-widest uppercase">New Sale</h3>
          </div>
        </template>
        <template v-slot:detail>
          <div class="grid grid-cols-5 gap-5 py-4 mx-3">
            <Item
              :price="item * 10 + 1100000"
              :priceSale="item * 9 + 1100000"
              :quantitySold="40 + item * 2"
              productName="Giày Nike"
              src="item.png"
              alt="Giày"
              v-for="item in count"
              :key="item"
            />
          </div>
        </template>
      </box>
      <div class="my-4 rounded-[4px] w-full bg-slate-600 shadow-custom">
        <div class="flex flex-col items-start justify-between" v-for="item in count" :key="item">
          <div class="flex items-center justify-between w-full px-4 py-2 rounded">
            <h3 class="text-2xl font-bold leading-9 tracking-widest uppercase text-brown">
              Giày NIKE
            </h3>
            <h3 class="flex text-lg font-bold align-middle cursor-pointer hover:text-brown">
              <span class="mr-2">Xem tất cả</span>
              <vue-feather class="w-6 h-6 translate-y-1" type="chevrons-right"></vue-feather>
            </h3>
          </div>
          <ul class="flex items-center gap-4 ml-4 uppercase">
            <li class="selected">Jordan 1</li>
            <li>Air Force 1</li>
            <li>Jordan 4</li>
            <li>Sb dunk</li>
          </ul>
          <div class="grid grid-cols-5 gap-5 py-4 mx-3">
            <Item
              :price="item * 16456 + 80000"
              :priceSale="item * 92340 + 70000"
              :quantitySold="40 + item * 3"
              productName="Giày Nike"
              src="item.png"
              alt="Giày"
              v-for="item in count"
              :key="item"
            />
          </div>
        </div>
      </div>
      <box>
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
            <article-item
              title="Đánh giá giày Jordan 1 Zoom Air PSG Paris Saint"
              src="item.png"
              alt="Giày"
              classTitle="text-center"
              v-for="item in count - 1"
              :key="item"
            />
          </div>
        </template>
      </box>
    </template>
  </BasePage>
</template>
<script>
import Item from '@/components/common/product/Item.vue'
import BoxCommon from '@/components/common/BoxCommon.vue'
import Article from '@/components/common/product/Article.vue'
import BasePage from '../auth/BasePage.vue'
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
export default {
  name: 'HomePage',
  components: {
    Item: Item,
    Box: BoxCommon,
    ArticleItem: Article,
    BasePage: BasePage
  },
  setup(props, context) {
    const count = 5
    const countMinus = 4
    const getImageUrl = (name) => {
      return new URL(`../../assets/images/trademark/${name}`, import.meta.url).href
    }
    return { props, context, getImageUrl, trademark, count, countMinus }
  }
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
