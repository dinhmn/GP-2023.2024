import http from "./http-common";
import { API_PRODUCT_PREFIX } from "../api";
import {INSERT, UPDATE} from "../../constants/index"

class ProductService {

  getAll(url, pageNo) {
    return http.get(API_PRODUCT_PREFIX + url + "?page_no=" + pageNo);
  }

  getProductById(url, categoryId, productId) {
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
