package pl.joannabrania.blog.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.joannabrania.blog.demo.models.forms.CommentForm;
import pl.joannabrania.blog.demo.models.forms.PostForm;
import pl.joannabrania.blog.demo.models.services.CommentService;
import pl.joannabrania.blog.demo.models.services.PostService;
import pl.joannabrania.blog.demo.models.services.SessionService;


@Controller
public class PostController {

    final PostService postService;
    final SessionService sessionService;
    final CommentService commentService;


    @Autowired
    public PostController(PostService postService, SessionService sessionService, CommentService commentService) {
        this.postService = postService;
        this.sessionService = sessionService;
        this.commentService = commentService;
    }



    @GetMapping("/post")
    public String post(Model model) {
        if(!sessionService.isLogin()){
            return "redirect:/login";
        }
        model.addAttribute("postForm", new PostForm());
        return "addPost";
    }

    @PostMapping("/post")
    public String post(@ModelAttribute("postForm") PostForm postForm){
        postService.addPost(postForm);
        return "redirect:/";
    }


    @GetMapping("/post/{id}")
    public String post(@PathVariable("id") int id,
                       Model model){

        model.addAttribute("postData",  postService.getAllPostData(id));
        model.addAttribute("commentForm", new CommentForm());
        return "showPost";
    }

    @PostMapping("/comment/{id}")
    public String comment(@PathVariable("id") int postId,
                          @ModelAttribute("commentForm") CommentForm comment){
        if(!sessionService.isLogin()){
            return "redirect:/login";
        }
        commentService.addComment(comment, postId);
        return "redirect:/post/" + postId;
    }

    @GetMapping("/post/delete/{id}")
    public String delete(@PathVariable("id") int postId){
        if (sessionService.isLogin() && sessionService.getUserEntity().getPosts()
                .stream()
                .anyMatch(s -> s.getId() == postId)) {
            postService.deletePostById(postId);
            return "redirect:/";
        }
        return "redirect:/post/" + postId;
    }
}