package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Teacher;
import dz.univbechar.mad.repository.TeacherRepository;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Teacher")
public class TeacherController implements CrudController<Teacher, UUID>{

    @Autowired
    TeacherRepository repository;

    @Override
    @GetMapping("/{id}")
    @ApiResponse(responseCode = "404", description = "Teacher Not Found")
    public ResponseEntity<Teacher> get(@PathVariable("id") UUID id) {
        Optional<Teacher> teacher = repository.findById(id);

        return teacher.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping()
    @ApiResponse(responseCode = "204", description = "No Teachers on database")
    public ResponseEntity<Iterable<Teacher>> getAll() {
        Iterable<Teacher> teachers = repository.findAll();

        if(teachers.iterator().hasNext())
            return new ResponseEntity<>(teachers, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PostMapping()
    @ApiResponse(responseCode = "201", description = "Teacher created")
    @ApiResponse(responseCode = "500", description = "Server Issues")
    public ResponseEntity<Teacher> create(@RequestBody() Teacher body) {
        try {
            return new ResponseEntity<>(repository.save(body), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable("id") UUID id, @RequestBody() Teacher body) {
        try {
            Optional<Teacher> optional = repository.findById(id);

            if (optional.isPresent()) {
                Teacher teacher = optional.get();

                if (body.getName() != null) teacher.setName(body.getName());
                if (body.getLname() != null) teacher.setLname(body.getLname());
                if (body.getModules() != null) teacher.setModules(body.getModules());
                if (body.getDepartments() != null) teacher.setDepartments(body.getDepartments());

                return new ResponseEntity<>(repository.save(teacher), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
