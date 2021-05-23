package com.amit.rest.webservices.restfulwebservices.post;

import com.amit.rest.webservices.restfulwebservices.customexceptions.UserNotFoundException;
import com.amit.rest.webservices.restfulwebservices.user.User;
import com.amit.rest.webservices.restfulwebservices.user.UserRepository;
import com.amit.rest.webservices.restfulwebservices.user.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostJPAResource {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/jpa/posts/findall")
    public List<Post> retrieveAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Posts operations
     * Create post for any user
     *
     * @return*/
    @PostMapping("/jpa/posts/{id}/createone")
    public ResponseEntity createPostsForAUser(@PathVariable int id,@RequestBody Post post) {
         //Find user first
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent()) throw new UserNotFoundException("id-"+id+": user not found");

        // Set user for the post
        post.setUser(user.get());

        // CREATED
        Post createdPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/posts/deleteone/{id}")
    public ResponseEntity deleteOneUser(@PathVariable int id) {

        postRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
