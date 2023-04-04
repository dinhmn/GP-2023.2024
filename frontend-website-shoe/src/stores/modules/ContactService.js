import http from "./http-common";
import { API_CONTACT_PREFIX } from "../api";

class ContactService {

  getAll(url, pageNo) {
    return http.get(API_CONTACT_PREFIX + url + "?page_no=" + pageNo);
  }

  getAllPage(url, pageNo, searchValue) {
    return http.get(API_CONTACT_PREFIX + url + '/' + "?search_value=" + searchValue + "&page_no=" + pageNo);
  }

  insertOrUpdate(url, contactModel) {
    return http.post(API_CONTACT_PREFIX + url, contactModel, {
      headers: {
        "Content-Type": "application/json"
      },
    });
  }
}
export default new ContactService();
