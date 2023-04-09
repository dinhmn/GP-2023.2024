import http from "./http-common";
import { API_CHAT_PREFIX } from "../api";

class ChatService {

  async getAll(messageFrom, messageTo) {

    return http.get(API_CHAT_PREFIX + "/" + messageFrom + "/" + messageTo);
  }

  async getAllMessage() {
    return http.get(API_CHAT_PREFIX)
  }
}
export default new ChatService();
