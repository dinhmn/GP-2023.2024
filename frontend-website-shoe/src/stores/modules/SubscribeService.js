import http from "./http-common";
import { API_SUBSCRIBE_PREFIX } from "../api";

class SubscribeService {

  getAll(url, pageNo) {
    return http.get(API_SUBSCRIBE_PREFIX + url + "?page_no=" + pageNo);
  }

  insert(subscribe) {
    return http.post(API_SUBSCRIBE_PREFIX + "/register", subscribe, {
      headers: {
        "Content-Type": "application/json"
      },
    });
  }
}
export default new SubscribeService();
