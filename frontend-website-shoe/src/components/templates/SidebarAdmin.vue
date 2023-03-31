<template lang="">
  <div class="bg-[#171B2D] h-full">
    <div class="flex flex-col text-lg font-bold">
      <router-link :to="{ name: 'CategoryAdmin' }">
        <span>Category</span>
      </router-link>
      <router-link :to="{ name: 'ProductAdmin' }">
        <span>Product</span>
      </router-link>
      <!-- <span>Article</span>
      <span>Contact</span>
      <span>Cart</span>
      <span>User</span>
      <span>404 Page</span> -->
    </div>
  </div>
</template>
<script></script>
<style lang="scss" scoped>
div {
  div {
    & > a {
      padding: 5px 20px;
      transition: all 0.2s;
      border-radius: 3px;
      border-left: 4px solid transparent;
      &:hover {
        color: #17b1ea;
      }
      &.router-link-active {
        background: #0c3247;
        color: #17b1ea;
        border-left: 4px solid #17b1ea;
      }
    }
    span {
      font-size: 16px;
      font-weight: 500;
      padding: 8px 40px;
      color: white;
      cursor: pointer;
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-left: 3px transparent solid;
      &:hover {
        color: #17b1ea;
      }
    }
  }
}
</style>
