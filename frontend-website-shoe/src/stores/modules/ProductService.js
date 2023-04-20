import http from "./http-common";
import { API_PRODUCT_PREFIX } from "../api";
import {INSERT, UPDATE} from "../../constants/index"

export default class ProductService {

    getAll(url) {
      return http.get(API_PRODUCT_PREFIX + url);
    }
  
    insertOrUpdate(file, article, url, type) {
      let formData = new FormData();
  
      formData.append("file", file);
      formData.append("article", JSON.stringify(article));
  
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
