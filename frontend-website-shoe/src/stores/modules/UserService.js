import http from "./http-common";
import { API_USER_PREFIX } from "../api";


class UserService {

  getAll(url, pageNo) {
    return http.get(API_USER_PREFIX + url + "?page_no=" + pageNo);
  }

  update(url, userId, roleId) {
    return http.put(API_USER_PREFIX + url + "/" + userId + "/" + roleId);
  }
}

export default new UserService();
