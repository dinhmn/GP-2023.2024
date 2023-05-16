import http from "./http-common";
import { API_PRODUCT_PREFIX } from "../api";
import {INSERT, UPDATE} from "../../constants/index"

class ProductService {

  getAll(url, pageNo) {
    return http.get(API_PRODUCT_PREFIX + url + "?page_no=" + pageNo);
  }

  getProductByCategoryId(url, categoryId, limitItem) {
    return http.get(API_PRODUCT_PREFIX + url + "/" + categoryId + "/" + limitItem);
  }

  getProductSaleByCategoryId(url, limitItem) {
    return http.get(API_PRODUCT_PREFIX + url + "/" + limitItem);
  }

  getAllUser(url, pageNo, pageSize, sortBy, sortDirection, searchValue) {
    return http.get(API_PRODUCT_PREFIX + url + "?page_no=" + pageNo + "&page_size=" + pageSize
    + "&sort_direction=" + sortDirection + "&sort_by=" + sortBy + "&search_value=" + searchValue);
  }

  getProductById(url, categoryId, productId) {
    console.log(API_PRODUCT_PREFIX + url + `/${categoryId}/${productId}`);
    return http.get(API_PRODUCT_PREFIX + url + `/${categoryId}/${productId}`);
  }

  insertOrUpdate(files, product, url, type) {
    let formData = new FormData();
    for (const key of Object.keys(files)) {
      formData.append("files", files[key], files[key].name);
    }
    formData.append("product", JSON.stringify(product));
    console.log(formData)
    if (INSERT === type) {
      return http.post(API_PRODUCT_PREFIX + url, formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        },
        // onUploadProgress
      });
    }
    if (UPDATE === type) {
      console.log(formData);
      return http.put(API_PRODUCT_PREFIX + url + "/" + product.categoryId + "/" + product.productId, formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        },
        // onUploadProgress
      });
    }
  }
  
  search(URL, searchData) {
    return http.get(API_PRODUCT_PREFIX + URL + "?page_no=" + searchData.pageNo + "&page_size="
    + searchData.pageSize + "&sort_direction=" + searchData.sortDirection + "&sort_by=" + searchData.sortBy + "&search_value=" + searchData.searchValue)
  }

  delete(url,categoryId, productId) {
    return http.delete(API_PRODUCT_PREFIX + url + "/" + categoryId + "/" + productId)
  }
}
  export default new ProductService();
