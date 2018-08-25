package pl.joannabrania.blog.demo.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.joannabrania.blog.demo.models.PostEntity;
import pl.joannabrania.blog.demo.models.UserEntity;
import pl.joannabrania.blog.demo.models.services.PostService;

import java.util.Optional;

@RestController
@RequestMapping("rest")
public class PostRestController {

    final PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = "/post", produces = "application/json")
    public ResponseEntity allPost(){

        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping(value = "/post/{idPost}", produces = "application/json")
    public ResponseEntity onePost(@PathVariable("idPost") int idPost){

    return postService.getPost(idPost)
            .map(s -> ResponseEntity.ok(s))
            .orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(value = "/post", consumes = "application/json")
    public ResponseEntity savePost(@RequestBody PostEntity postEntity){
        postService.savePost(postEntity);
        return ResponseEntity.ok(postEntity);
    }

    @DeleteMapping(value = "/post/{idPost}", produces = "application/json")
    public ResponseEntity deletePost(@PathVariable("idPost") int idPost){
        postService.deletePostById(idPost);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/post", consumes = "application/json")
    public ResponseEntity updatePost(@RequestBody PostEntity postEntity){
        postService.savePost(postEntity);
        return ResponseEntity.ok(postEntity);
    }


}
