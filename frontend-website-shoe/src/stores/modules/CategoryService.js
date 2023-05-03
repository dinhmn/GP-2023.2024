import http from "./http-common";
import { API_CATEGORY_PREFIX } from "../api";
// import {INSERT, UPDATE} from "../../constants/index"

class CategoryService {

    getAll(url) {
      return http.get(API_CATEGORY_PREFIX + url);
    }
  
    getAllPageable(url, pageNo, pageSize, sortBy, sortDirection, searchValue) {
      return http.get(API_CATEGORY_PREFIX + url + "?page_no=" + pageNo + "&page_size=" + pageSize
      + "&sort_direction=" + sortDirection + "&sort_by=" + sortBy + "&search_value=" + searchValue)
    }
}
  export default new CategoryService();
