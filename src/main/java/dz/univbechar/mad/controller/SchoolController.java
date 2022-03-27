package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.School;
import dz.univbechar.mad.entity.Teacher;
import dz.univbechar.mad.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Scanner;

@RestController
@RequestMapping("/School")
public class SchoolController implements CrudController<School, Long>{

    @Autowired
    SchoolRepository schoolRepository;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<School> get(Long id) {

        Optional<School> schools = schoolRepository.findById(id);

        return schools.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping()
    public ResponseEntity<Iterable<School>> getAll() {
        Iterable<School> schools = schoolRepository.findAll();

        if(schools.iterator().hasNext())
            return new ResponseEntity<>(schools, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PostMapping()
    public ResponseEntity<School> create(School body) {
        try {
            return new ResponseEntity<>(schoolRepository.save(body), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<School> update(Long id, School body) {
        try {
            Optional<School> optional = schoolRepository.findById(id);

            if (optional.isPresent()) {
                School school = optional.get();

                if (body.getName() != null) school.setName(body.getName());
                if (body.getType() != null) school.setType(body.getType());
                if (body.getFaculties() != null) school.setFaculties(body.getFaculties());

                return new ResponseEntity<>(schoolRepository.save(school), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(Long id) {
        try {
            schoolRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            schoolRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
