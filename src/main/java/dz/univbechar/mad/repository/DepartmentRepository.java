package dz.univbechar.mad.repository;

import dz.univbechar.mad.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
