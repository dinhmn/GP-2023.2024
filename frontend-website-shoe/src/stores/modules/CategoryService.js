import http from "./http-common";
import { API_CATEGORY_PREFIX } from "../api";
// import {INSERT, UPDATE} from "../../constants/index"

class CategoryService {

    getAll(url) {
      return http.get(API_CATEGORY_PREFIX + url);
    }
  
    getAllPageable(url, page) {
      console.log(page);
      return http.get(API_CATEGORY_PREFIX + url + "?page_no=" + page.pageNo + "&page_size=" + page.pageSize
      + "&sort_direction=" + page.sortDirection + "&sort_by=" + page.sortBy + "&search_value=" + page.searchValue)
    }
    
}
  export default new CategoryService();
