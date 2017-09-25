package io.metaphor.tosapi.user;

import io.metaphor.tosapi.error.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
//@Secured("ROLE_ADMIN")
public class UserApiController {
    private UserRepository userRepository;

    @Autowired
    public UserApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        HttpStatus status = HttpStatus.OK;
        if (!userRepository.exists(user.getEmail())) {
            status = HttpStatus.CREATED;
        }
        User saved = userRepository.save(user);
        return new ResponseEntity(saved, status);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUserName(@PathVariable String email, @RequestBody User user) throws EntityNotFoundException {
        User loaded =  userRepository.findByEmail(email);
        if(loaded==null)
            throw new EntityNotFoundException("can not find user by email");
        loaded.setUsername(user.getUsername());
        User saved = userRepository.save(loaded);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable String email) throws EntityNotFoundException {
        userRepository.delete(email);
        return new ResponseEntity(HttpStatus.OK);
    }
}
