import http from "./http-common";
import { API_ARTICLE_PREFIX } from "../api";
import {INSERT, UPDATE} from "../../constants/index"
class ArticleService {

  getAll(url, pageNo) {
    return http.get(API_ARTICLE_PREFIX + url + "?page_no=" + pageNo);
  }

  getById(url, articleId) {
    return http.get(API_ARTICLE_PREFIX + url + '/' + articleId);
  }

  insertOrUpdate(file, article, url, type) {
    let formData = new FormData();

    formData.append("file", file);
    formData.append("article", JSON.stringify(article));

    if (INSERT === type) {
      return http.post(API_ARTICLE_PREFIX + url, formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        },
        // onUploadProgress
      });
    }
    if (UPDATE === type) {
      return http.put(API_ARTICLE_PREFIX + url, formData, {
        headers: {
          "Content-Type": "multipart/form-data"
        },
        // onUploadProgress
      });
    }
  }

  update(file, article) {
    let formData = new FormData();

    formData.append("file", file);
    formData.append("article", JSON.stringify(article));

    return http.post("/api/article/register", formData, {
      headers: {
        "Content-Type": "multipart/form-data"
      },
      // onUploadProgress
    });
  }

  delete(url) {
    return http.delete(API_ARTICLE_PREFIX + url)
  }


}
export default new ArticleService();
