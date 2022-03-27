package dz.univbechar.mad.repository;

import dz.univbechar.mad.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
