import http from "./http-common";
import { API_ARTICLE_PREFIX } from "../api";
class ArticleService {

  getAll(url) {
    return http.get(API_ARTICLE_PREFIX + url)
  }

  getAllPageable(url) {
    return http.get(API_ARTICLE_PREFIX + url);
  }

  upload(file, article) {
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
