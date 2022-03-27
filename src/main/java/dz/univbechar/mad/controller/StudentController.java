package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Student;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dz.univbechar.mad.repository.StudentRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Student")
public class StudentController implements CrudController<Student, UUID>{

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Student Found")
    @ApiResponse(responseCode = "404", description = "Student Not Found")
    public ResponseEntity<Student> get(@PathVariable(value = "id") UUID id) {
        Optional<Student> student = studentRepository.findById(id);

        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @GetMapping("")
    public ResponseEntity<Iterable<Student>> getAll() {
        Iterable<Student> students = studentRepository.findAll();

        if(students.iterator().hasNext())
            return new ResponseEntity<>(students, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping()
    public ResponseEntity<Student> create( @RequestBody() Student body)   {
        try {
            return new ResponseEntity<>(studentRepository.save(body), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") UUID id, @RequestBody() Student body) {
        try {
            Optional<Student> optional = studentRepository.findById(id);

            if (optional.isPresent()) {
                Student student = optional.get();

                if (body.getName() != null) student.setName(body.getName());
                if (body.getLname() != null) student.setLname(body.getLname());
                if (body.getParents() != null) student.setParents(body.getParents());

                return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {
        try {
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}  	

