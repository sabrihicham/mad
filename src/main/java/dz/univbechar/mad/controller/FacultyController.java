package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Faculty;
import dz.univbechar.mad.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Faculty")
public class FacultyController implements CrudController<Faculty, Long>{

    @Autowired
    FacultyRepository facultyRepository;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Faculty> get(Long id) {

        Optional<Faculty> faculty = facultyRepository.findById(id);

        return faculty.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping()
    public ResponseEntity<Iterable<Faculty>> getAll() {
        Iterable<Faculty> faculties = facultyRepository.findAll();

        if(faculties.iterator().hasNext())
            return new ResponseEntity<>(faculties, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PostMapping()
    public ResponseEntity<Faculty> create(Faculty body) {
        try {
            return new ResponseEntity<>(facultyRepository.save(body), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Faculty> update(Long id, Faculty body) {
        try {
            Optional<Faculty> optional = facultyRepository.findById(id);

            if (optional.isPresent()) {
                Faculty saculty = optional.get();

                if (body.getName() != null) saculty.setName(body.getName());
                if (body.getDepartments() != null) saculty.setDepartments(body.getDepartments());

                return new ResponseEntity<>(facultyRepository.save(saculty), HttpStatus.OK);
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
            facultyRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            facultyRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
