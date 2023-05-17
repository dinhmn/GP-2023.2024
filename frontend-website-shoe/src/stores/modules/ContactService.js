import http from "./http-common";
import { API_CONTACT_PREFIX } from "../api";

class ContactService {

  getAll(url, pageNo) {
    return http.get(API_CONTACT_PREFIX + url + "?page_no=" + pageNo);
  }

  getAllPage(url, page) {
    return http.get(API_CONTACT_PREFIX + url + "?page_no=" + page.pageNo
    + "&page_size=" + page.pageSize + "&sort_direction=" + page.sortDirection
    + "&sort_by=" + page.sortBy + "&search_value=" + page.searchValue);
  }

  insertOrUpdate(url, contactModel) {
    return http.post(API_CONTACT_PREFIX + url, contactModel, {
      headers: {
        "Content-Type": "application/json"
      },
    });
  }

  update(contactId) {
    return http.put(API_CONTACT_PREFIX + '/update/' + contactId)
  }
}
export default new ContactService();
