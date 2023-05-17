<template lang="">
  <div class="w-full text-white min-h-[100vh]">
    <h2 class="w-full my-2 mb-4 font-bold">
      <span class="text-xl">Dashboard</span> <strong> > </strong>
      <span class="text-[#17b1ea] text-xl">{{ props.title }}</span>
    </h2>
    <div class="flex items-center justify-between p-4 bg-[#1a1e30] rounded-md">
      <slot name="search" />
      <div v-if="props.actionNew != 'false'">
        <router-link :to="{ name: props.actionNew }">
          <Button
            type="button"
            :text="'Thêm ' + props.title"
            id="addCategory"
            className="bg-[#0c3247] text-[#17b1ea] hover:bg-[#10405a] hover:text-white"
          />
        </router-link>
      </div>
    </div>
    <div class="w-full mt-5 rounded-md p-4 bg-[#1a1e30]">
      <div v-if="errorMessage == true" class="w-full text-lg font-bold text-center text-red-500">
        The connection to the network failed. Please check again.
      </div>
      <table class="w-full">
        <slot name="thead" />
        <slot name="tbody" />
      </table>
    </div>
    <div class="w-full mt-3 text-right">
      <slot name="page" />
    </div>
  </div>
</template>
<script setup>
import { ref } from 'vue'
import Button from '@/components/common/button/Button.vue'
const errorMessage = ref(false)
const props = defineProps({
  title: {
    type: String,
    default: 'Dashboard'
  },
  page: {
    type: Number,
    default: 0
  },
  actionNew: {
    type: String,
    default: '/'
  }
})
</script>
<style lang="css" scoped>
ul li {
  padding: 4px 20px;
  text-align: center;
  display: flex;
  align-items: center;
  justify-content: center;
  border-radius: 3px;
  cursor: pointer;
}
ul li.active {
  background-color: #0c3247;
  color: #17b1ea;
}
table,
tr,
td,
th {
  padding: 5px 10px;
  text-align: center;
}

table tbody tr {
  border-bottom: 2px solid #464b53;
}
table tbody tr td {
  padding: 5px;
}
</style>
