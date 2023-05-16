import http from "./http-common";
import { API_ORDER_PREFIX, API_EXPORT_PDF_PREFIX } from "../api";

class OrderService {
  
  get(url) {
    return http.get(API_ORDER_PREFIX + url)
  }

  getAllOrder(url, page) {
    console.log(page);
    return http.get(API_ORDER_PREFIX + url + "?page_no=" + page.pageNo
    + "&page_size=" + page.pageSize + "&sort_direction=" + page.sortDirection
    + "&sort_by=" + page.sortBy + "&search_value=" + page.value);
  }

  exportPdf(url, orderId) {
    return http.post(API_EXPORT_PDF_PREFIX + url + "?code=" + orderId, {headers: {
      "Content-type": "application/json"
    }});
  }

  insert(url, orderJasperModel) {
    // let formData = new FormData();

    // formData.append("orderJasperModel", JSON.stringify(order));

    return http.post(API_ORDER_PREFIX + url, orderJasperModel)
    .then((response) => {
      console.log(response);
    })
    .catch((error) => {
      console.log(error);
    });
  }

  update(url, orderId, orderStatus) {
    return http.put(API_ORDER_PREFIX + url + "/" + orderId + "/" + orderStatus);
  }

  getOrderByUserId(userId) {
    return http.get(API_ORDER_PREFIX + "/get-order/" + userId);
  }

  delete(orderId, cancelOrder) {
    return http.put(API_ORDER_PREFIX + "/delete/" + orderId, cancelOrder);
  }
  
}
export default new OrderService();
