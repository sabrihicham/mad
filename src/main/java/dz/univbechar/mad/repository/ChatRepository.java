package dz.univbechar.mad.repository;

import dz.univbechar.mad.entity.Chat;
import dz.univbechar.mad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByUserContaining(User user);
}
