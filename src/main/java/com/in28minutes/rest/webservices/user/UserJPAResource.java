package com.in28minutes.rest.webservices.user;

import com.in28minutes.rest.webservices.posts.Post;
import com.in28minutes.rest.webservices.posts.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAResource {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("id - "+id);

        EntityModel<User> model = EntityModel.of(user.get());
        WebMvcLinkBuilder linkBuilder = linkTo(methodOn(this.getClass()).retrieveAllUser());
        model.add(linkBuilder.withRel("all-users"));
        return model;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUserById(@PathVariable Integer id) throws UserNotFoundException {
        userRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable Integer id){
        Optional<User> user = userRepository.findById(id);
        if(!user.isPresent())
            throw new UserNotFoundException("User not found");
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Object> createPosts(@PathVariable Integer id, @RequestBody Post post){

        Optional<User> userOptional = userRepository.findById(id);

        if(!userOptional.isPresent())
            throw new UserNotFoundException("User not found");

        User user = userOptional.get();
        post.setUser(user);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

}
