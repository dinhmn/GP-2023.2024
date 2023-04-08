package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Implement repository of chat.
 *
 * @author Mai Ngoc Dinh
 */
@Repository
public interface ChatRepository extends JpaRepository<ChatEntity, Long> {

}
