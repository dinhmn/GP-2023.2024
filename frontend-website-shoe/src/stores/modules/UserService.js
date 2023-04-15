import http from "./http-common";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8088/api/test/"

class UserService {
  getPublicContent() {
    return http.get(API_URL + 'all');
  }

  getUserBoard() {
    return http.get(API_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return http.get(API_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return http.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();
