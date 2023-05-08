package com.kh.learnovation.domain.chatbot.repository;

import com.kh.learnovation.domain.chatbot.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByName(String roomName);

    int deleteByName(String roomName);
}
