<template lang="">
  <!-- <QuillEditor theme="snow" toolbar="#custom-toolbar">
    <template #toolbar>
      <div id="custom-toolbar">
        <select class="ql-size">
          <option value="small"></option>
          <option selected></option>
          <option value="large"></option>
          <option value="huge"></option>
        </select>
      </div>
    </template>
  </QuillEditor> -->
  <div class="bg-white">
    <quill-editor theme="snow" @change="onChange($event)"></quill-editor>
  </div>
</template>
<script setup>
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
const onChange = (event) => {
  console.log(event)
}
</script>
<style lang=""></style>
