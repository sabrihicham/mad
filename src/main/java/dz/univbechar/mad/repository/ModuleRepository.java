package dz.univbechar.mad.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dz.univbechar.mad.entity.Module;

public interface ModuleRepository extends JpaRepository<Module, Long> {
}
