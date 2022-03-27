package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Chat;
import dz.univbechar.mad.entity.Message;
import dz.univbechar.mad.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Message")
public class MessageController implements CrudController<Message, Long>{

    @Autowired
    MessageRepository messageRepository;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Message> get(@PathVariable("id") Long id) {
        Optional<Message> message = messageRepository.findById(id);

        return message.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping()
    public ResponseEntity<Iterable<Message>> getAll() {
        Iterable<Message> messages = messageRepository.findAll();

        if(messages.iterator().hasNext())
            return new ResponseEntity<>(messages, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PostMapping()
    public ResponseEntity<Message> create(@RequestBody Message body) {
        try {
            return new ResponseEntity<>(messageRepository.save(body), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Message> update(@PathVariable("id") Long id, @RequestBody Message body) {
        try {
            Optional<Message> optional = messageRepository.findById(id);

            if (optional.isPresent()) {
                Message message = optional.get();

                return new ResponseEntity<>(messageRepository.save(message.update(body)), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            messageRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            messageRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
