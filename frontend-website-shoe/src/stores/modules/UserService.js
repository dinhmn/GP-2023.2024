import http from "./http-common";
import { API_USER_PREFIX, API_ROLE_PREFIX } from "../api";


class UserService {

  getAll(url, pageNo) {
    return http.get(API_USER_PREFIX + url + "?page_no=" + pageNo);
  }
  
  getAllPage(url, page) {
    return http.get(API_USER_PREFIX + url + "?page_no=" + page.pageNo
    + "&page_size=" + page.pageSize + "&sort_direction=" + page.sortDirection
    + "&sort_by=" + page.sortBy + "&search_value=" + page.value);
  }

  update(url, userId, roleId) {
    return http.put(API_USER_PREFIX + url + "/" + userId + "/" + roleId);
  }

  getByUserId(userId) {
    return http.get(API_USER_PREFIX + "/get-user/" + userId);
  }

  getRole() {
    return http.get(API_ROLE_PREFIX + "/get");
  }
}

export default new UserService();
