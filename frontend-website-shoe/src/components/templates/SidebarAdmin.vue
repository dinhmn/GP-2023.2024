<template lang="">
  <div class="bg-slate-600 h-[100vh]">
    <ul class="text-lg font-bold uppercase">
      <li class="bg-brown">
        <span>Category</span><vue-feather class="w-6 h-6" type="chevron-down"></vue-feather>
      </li>
      <li><span>Product</span><vue-feather class="w-6 h-6" type="chevron-down"></vue-feather></li>
      <li><span>Article</span><vue-feather class="w-6 h-6" type="chevron-down"></vue-feather></li>
      <li><span>Contact</span><vue-feather class="w-6 h-6" type="chevron-down"></vue-feather></li>
      <li><span>Cart</span><vue-feather class="w-6 h-6" type="chevron-down"></vue-feather></li>
      <li><span>User</span><vue-feather class="w-6 h-6" type="chevron-down"></vue-feather></li>
      <li><span>404 Page</span><vue-feather class="w-6 h-6" type="chevron-down"></vue-feather></li>
    </ul>
  </div>
</template>
<script></script>
<style lang="scss">
div {
  ul {
    li {
      font-size: 16px;
      font-weight: bold;
      padding: 10px 40px;
      color: white;
      cursor: pointer;
      display: flex;
      justify-content: space-between;
      align-items: center;
      &:hover {
        --tw-bg-opacity: 1;
        background-color: slategray;
      }
    }
  }
}
</style>
