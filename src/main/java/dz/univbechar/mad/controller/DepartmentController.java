package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Department;
import dz.univbechar.mad.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Department")
public class DepartmentController implements CrudController<Department, Long>{

    @Autowired
    DepartmentRepository departmentRepository;


    @Override
    public ResponseEntity<Department> get(Long id) {

        Optional<Department> department = departmentRepository.findById(id);

        return department.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Iterable<Department>> getAll() {
        Iterable<Department> departments = departmentRepository.findAll();

        if(departments.iterator().hasNext())
            return new ResponseEntity<>(departments, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Department> create(Department body) {
        try {
            return new ResponseEntity<>(departmentRepository.save(body), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<Department> update(Long id, Department body) {
        try {
            Optional<Department> optional = departmentRepository.findById(id);

            if (optional.isPresent()) {
                Department department = optional.get();

                if (body.getName() != null) department.setName(body.getName());
                if (body.getSpecialities() != null) department.setSpecialities(body.getSpecialities());
                if (body.getTeachers() != null) department.setTeachers(body.getTeachers());

                return new ResponseEntity<>(departmentRepository.save(department), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> delete(Long id) {
        try {
            departmentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            departmentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
