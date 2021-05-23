package com.amit.rest.webservices.restfulwebservices.user;

import com.amit.rest.webservices.restfulwebservices.customexceptions.UserNotFoundException;
import com.amit.rest.webservices.restfulwebservices.post.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {
    @Autowired
    private UserDaoService service;

    @Autowired
    private UserRepository userRepository;


    /**GET /users
    Retrieve all users**/

    @GetMapping("/jpa/users/findall")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }


    /**GET /users/{id}
    Retrieve one user use user id */

    @GetMapping("/jpa/users/findone/{id}")
    public User retrieveOneUser(@PathVariable int id) {

        Optional<User> retrieveUser = userRepository.findById(id);
        if(!retrieveUser.isPresent()) {
            throw new UserNotFoundException("id-"+id+": user not found");
        }

        return retrieveUser.get();
    }

    @DeleteMapping("/jpa/users/deleteone/{id}")
    public ResponseEntity deleteOneUser(@PathVariable int id) {

        userRepository.deleteById(id);
        //Deleted
        return ResponseEntity.noContent().build();
    }

    //POST /users/{id}
    //Create one user use user id
    @PostMapping("/jpa/users/createuser")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        //CREATED
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * Posts operations
     *
     * @return*/
    @GetMapping("/jpa/users/findone/{id}/posts")
    public List<Post> retrieveOneUserPosts(@PathVariable int id) {

        Optional<User> retrieveUser = userRepository.findById(id);
        if(!retrieveUser.isPresent()) {
            throw new UserNotFoundException("id-"+id+": user not found");
        }
        return retrieveUser.get().getPosts();
    }


}
