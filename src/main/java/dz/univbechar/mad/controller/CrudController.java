package dz.univbechar.mad.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

interface CrudController<T,ID> {

    @GetMapping("/{id}")
    ResponseEntity<T> get(@PathVariable("id") ID id);

    @GetMapping()
    ResponseEntity<Iterable<T>> getAll();

    @PostMapping()
    ResponseEntity<T> create( @RequestBody() T body);

    @PutMapping("/{id}")
    ResponseEntity<T> update(@PathVariable("id") ID id, @RequestBody() T body);

    @DeleteMapping("/{id}")
    ResponseEntity<HttpStatus> delete(@PathVariable("id") ID id);

    @DeleteMapping()
    ResponseEntity<HttpStatus> deleteAll();

}
