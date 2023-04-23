package com.graduationproject.backend.backendwebsiteshoe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduationproject.backend.backendwebsiteshoe.entity.CommentEntity;
import com.graduationproject.backend.backendwebsiteshoe.helper.CommentHelper;
import com.graduationproject.backend.backendwebsiteshoe.model.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

/**
 * Implement controller of comment.
 *
 * @author Mai Ngoc Dinh.
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/comments")
public class CommentController {

  @Autowired
  CommentHelper commentHelper;

  /**
   * Update comment into database.
   *
   * @param comment comment
   * @return response entity.
   */
  @PostMapping(value = "/insert")
  public ResponseEntity<CommentEntity> register(@RequestPart("commentModel") String comment)
      throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    CommentModel commentModel = mapper.readValue(comment, CommentModel.class);
    return new ResponseEntity<>(commentHelper.insert(commentModel), HttpStatus.OK);
  }

}
