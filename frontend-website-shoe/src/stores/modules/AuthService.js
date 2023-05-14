import http from "./http-common";
import {API_AUTH} from "../api/index"

class AuthSevice {

  login(user) {
    
    return http
      .post(API_AUTH + '/sign-in', {
        username: user.username,
        password: user.password
      })
      .then(response => {
        if (response.data.accessToken) {
          localStorage.setItem('user', JSON.stringify(response.data));
        }
        return response.data;
      })
      .catch(error => {
        return error;
      });
  }

  logout() {
    localStorage.removeItem("user")
  }

  register(user) {
    return http.post(API_AUTH + '/sign-up', {
      username: user.username,
      email: user.email,
      password: user.password,
      roleIdList: ['1'],
      firstName: user.firstName,
      lastName: user.lastName
    });
  }

}

export default new AuthSevice(); 
