package dz.univbechar.mad.repository;

import dz.univbechar.mad.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {

}
