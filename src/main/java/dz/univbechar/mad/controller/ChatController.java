package dz.univbechar.mad.controller;

import dz.univbechar.mad.entity.Chat;
import dz.univbechar.mad.entity.Message;
import dz.univbechar.mad.entity.User;
import dz.univbechar.mad.repository.ChatRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/Chat")
public class ChatController implements CrudController<Chat, Long>{

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageController messageController;

    @PostMapping("/{id}/Message")
    public ResponseEntity<Chat> crateMessage(@PathVariable("id") Long id, @RequestBody() Message body) {
        ResponseEntity<Message> response = messageController.create(body);

        if(response.getStatusCode() == HttpStatus.OK){
            var optional = chatRepository.findById(id);

            if(optional.isPresent()){
                var chat = optional.get();

                chat.getMessages().add(body);

                return new ResponseEntity<>(chatRepository.save(chat), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/User/{id}")
    @ApiResponse(responseCode = "200",description = "User Chat Found")
    @ApiResponse(responseCode = "500",description = "Server issues")
    public ResponseEntity<List<Chat>> getByUserID(@PathVariable UUID id) {
        try {
            List<Chat> chats = chatRepository.findByUserContaining(new User().setId(id));

            return new ResponseEntity<>(chats, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Chat> get(@PathVariable("id") Long id) {
        Optional<Chat> chat = chatRepository.findById(id);

        return chat.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    @GetMapping()
    public ResponseEntity<Iterable<Chat>> getAll() {
        Iterable<Chat> chats = chatRepository.findAll();

        if(chats.iterator().hasNext())
            return new ResponseEntity<>(chats, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PostMapping()
    public ResponseEntity<Chat> create(@RequestBody Chat body) {
        try {
            return new ResponseEntity<>(chatRepository.save(body), HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Chat> update(@PathVariable("id") Long id,@RequestBody Chat body) {
        try {
            Optional<Chat> optional = chatRepository.findById(id);

            if (optional.isPresent()) {
                Chat chat = optional.get();

                return new ResponseEntity<>(chatRepository.save(chat.update(body)), HttpStatus.OK);
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
            chatRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping()
    public ResponseEntity<HttpStatus> deleteAll() {
        try {
            chatRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
