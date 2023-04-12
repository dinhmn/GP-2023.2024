import http from "./http-common";
import {API_AUTH} from "../api/index"

class AuthSevice {

  login(user) {
    return http
      .post(API_AUTH + '/signin', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user")
  }

  register(user) {
    return http.post(API_AUTH + '/signup', {
      username: user.username,
      email: user.email,
      password: user.password
    });
  }

}

export default new AuthSevice(); 
