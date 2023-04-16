import http from "./http-common";
import { API_PRODUCT_PREFIX } from "../api";
import {INSERT, UPDATE} from "../../constants/index"

class ProductService {

    getAll(url, pageNo) {
      return http.get(API_PRODUCT_PREFIX + url + "?page_no=" + pageNo);
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
        return http.put(API_PRODUCT_PREFIX + url, formData, {
          headers: {
            "Content-Type": "multipart/form-data"
          },
          // onUploadProgress
        });
      }
    }
  
  update(file, article) {
    let formData = new FormData();

    formData.append("file", file);
    formData.append("article", JSON.stringify(article));

    return http.post("/api/article/register", formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      },
      // onUploadProgress
    });
  }

  delete(url) {
    return http.delete(API_PRODUCT_PREFIX + url)
  }
}
  export default new ProductService();
