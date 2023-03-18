<template lang="">
  <section
    class="m-auto 2xl:w-[1280px] xl:w-[100%] container flex items-start justify-start flex-col min-h-[100vh]"
  >
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
    <div
      class="flex flex-col items-start justify-between w-full my-4 rounded-[4px] bg-slate-600 px-1 py-4"
    >
      <div class="w-full px-4 py-2 rounded bg-brown">
        <h3 class="text-2xl font-bold leading-9 tracking-widest uppercase">Giá sốc hôm nay</h3>
      </div>
      <div class="grid grid-cols-5 gap-5 py-4 mx-3">
        <div
          class="flex flex-col w-full gap-4 py-4 px-1 rounded cursor-pointer h-[360px] item bg-slate-700"
          v-for="item in count"
          :key="item"
        >
          <img
            class="object-cover w-full h-full max-h-[230px]"
            src="../../assets/images/item.png"
            alt="Giày"
          />
          <div class="flex flex-col gap-2 ml-2">
            <span class="text-base">Giày Nike</span>
            <h4 class="text-base text-[#ff2347]">
              Giá: <span class="text-sm line-through">1.100.000 đ</span> 1.100.000 đ
            </h4>
            <h5 class="text-sm text-[#ff2347]">Đã bán: 40</h5>
          </div>
        </div>
      </div>
    </div>
    <div>Item</div>
  </section>
</template>
<script>
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
  setup(props, context) {
    const count = 5
    const getImageUrl = (name) => {
      return new URL(`../../assets/images/trademark/${name}`, import.meta.url).href
    }
    return { props, context, getImageUrl, trademark, count }
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
</style>
