package com.graduationproject.backend.backendwebsiteshoe.entity;

import jakarta.persistence.Column;
import lombok.Data;

import java.io.Serializable;

@Data
public class CommentEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "comment_id", nullable = false)
    private Long commentId;

    @Column(name = "userId", nullable = false)
    private Long userId;

    public CommentEntityKey setPk(Long commentId, Long userId) {
        setCommentId(commentId);
        setUserId(userId);
        return this;
    }
}
