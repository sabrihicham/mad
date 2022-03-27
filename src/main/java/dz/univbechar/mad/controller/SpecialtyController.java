package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Speciality;
import dz.univbechar.mad.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Specialty")
public class SpecialtyController implements CrudController<Speciality, Long>{

    @Autowired
    SpecialityRepository specialityRepository;

    @Override
    public ResponseEntity<Speciality> get(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<Iterable<Speciality>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Speciality> create(Speciality body) {
        return null;
    }

    @Override
    public ResponseEntity<Speciality> update(Long aLong, Speciality body) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Long aLong) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        return null;
    }
}
