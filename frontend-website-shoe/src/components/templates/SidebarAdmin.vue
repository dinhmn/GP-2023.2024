<template lang="">
  <div class="bg-[#171B2D] h-[100vh]">
    <ul class="text-lg font-bold">
      <li class="active">
        <span>Category</span>
      </li>
      <!-- <vue-feather class="w-6 h-6" type="chevron-down"></vue-feather> -->
      <li><span>Product</span></li>
      <li><span>Article</span></li>
      <li><span>Contact</span></li>
      <li><span>Cart</span></li>
      <li><span>User</span></li>
      <li><span>404 Page</span></li>
    </ul>
  </div>
</template>
<script></script>
<style lang="scss" scoped>
div {
  ul {
    li {
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
      &.active {
        border-left: 3px #17b1ea solid;
        background: #0c3247;
        color: #17b1ea;
      }
    }
  }
}
</style>
