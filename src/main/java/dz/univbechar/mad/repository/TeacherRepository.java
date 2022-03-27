package dz.univbechar.mad.repository;

import dz.univbechar.mad.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
}
