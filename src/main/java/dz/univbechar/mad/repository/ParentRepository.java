package dz.univbechar.mad.repository;

import dz.univbechar.mad.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {

}
