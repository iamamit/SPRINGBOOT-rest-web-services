package com.amit.rest.webservices.restfulwebservices.user;

import com.amit.rest.webservices.restfulwebservices.customexceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserDaoService service;

    //GET /users
    //Retrieve all users
    @GetMapping("/users/findall")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    //GET /users/{id}
    //Retrieve one user use user id
    @GetMapping("/users/findone/{id}")
    public User retrieveOneUser(@PathVariable int id) {

        User retrieveUser = service.findOne(id);
        if(null == retrieveUser) {
            throw new UserNotFoundException("id-"+id+": user not found");
        }

        // HATEOAS
        // get-all-user BASEURI + /findall

//        Resource<User> resource = new Resouce(retrieveUser); Resource is now Entity model

        return retrieveUser;
    }

    @DeleteMapping("/users/deleteone/{id}")
    public ResponseEntity deleteOneUser(@PathVariable int id) {

        User deletedUser = service.deleteOne(id);
        if(null == deletedUser) {
            throw new UserNotFoundException("id-"+id+": user not found");
        }

        //Deleted
        return ResponseEntity.noContent().build();
    }

    //POST /users/{id}
    //Create one user use user id
    @PostMapping("/users/createuser")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);
        //CREATED
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
