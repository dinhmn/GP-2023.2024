import http from "./http-common";
import { API_ORDER_PREFIX } from "../api";

class OrderService {

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

}
export default new OrderService();
