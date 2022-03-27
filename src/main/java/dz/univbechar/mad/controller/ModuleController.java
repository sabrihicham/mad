package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Module;
import dz.univbechar.mad.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Module")
public class ModuleController implements CrudController<Module, Long> {

    @Autowired
    ModuleRepository moduleRepository;

    @Override
    public ResponseEntity<Module> get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Iterable<Module>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Module> create(Module body) {
        return null;
    }

    @Override
    public ResponseEntity<Module> update(Long id, Module body) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        return null;
    }
}
