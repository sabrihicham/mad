package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Parent;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dz.univbechar.mad.repository.ParentRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController  
@RequestMapping("/Parent")
public class ParentController implements CrudController<Parent, UUID>{

        @Autowired
        ParentRepository parentRepository;

        @GetMapping("/{id}")
        @ApiResponse(responseCode = "200", description = "Parent Found")
        @ApiResponse(responseCode = "404", description = "Parent Not Found")
        public ResponseEntity<Parent> get(@PathVariable(value = "id") UUID id) {
            
            Optional<Parent> parent = parentRepository.findById(id);

            return parent.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        }

        @GetMapping("")
        public ResponseEntity<Iterable<Parent>> getAll() {
            Iterable<Parent> parents = parentRepository.findAll();
            
            if(parents.iterator().hasNext())
            	return new ResponseEntity<>(parents, HttpStatus.OK);
            	
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        @GetMapping("/Student/{id}")
        public ResponseEntity<Iterable<Parent>> findParentsByStudent(@PathVariable UUID id) {

            List<Parent> parents = parentRepository.findAll();

            List<Parent> result = parents.stream().filter(parent -> parent.getStudents().stream().anyMatch(student -> student.getId() == id)).collect(Collectors.toList());

            if(result.isEmpty())
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        @PostMapping()
        public ResponseEntity<Parent> create( @RequestBody() Parent body)   {
            try {
                return new ResponseEntity<>(parentRepository.save(body), HttpStatus.CREATED);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

       @PutMapping("/{id}")
        public ResponseEntity<Parent> update(@PathVariable("id") UUID id, @RequestBody() Parent body) {
            try {
                Optional<Parent> optional = parentRepository.findById(id);

                if (optional.isPresent()) {
                    Parent parent = optional.get();

                    if (body.getName() != null) parent.setName(body.getName());
                    if (body.getLname() != null) parent.setLname(body.getLname());
                    if (body.getStudents() != null) parent.setStudents(body.getStudents());

                    return new ResponseEntity<>(parentRepository.save(parent), HttpStatus.OK);
                }

                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
       }
       
       @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> delete(@PathVariable("id") UUID id) {
            try {
                parentRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
       }

       @DeleteMapping()
       public ResponseEntity<HttpStatus> deleteAll() {
            try {
                parentRepository.deleteAll();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch(Exception e){
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
       }

}  	
