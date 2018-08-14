package pl.joannabrania.blog.demo.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.joannabrania.blog.demo.models.PostEntity;
import pl.joannabrania.blog.demo.models.forms.PostForm;
import pl.joannabrania.blog.demo.models.repositories.PostRepository;

import java.util.Optional;


@Service
public class PostService {

    final SessionService sessionService;
    final PostRepository postRepository;

    @Autowired
    public PostService(SessionService sessionService, PostRepository postRepository) {
        this.sessionService = sessionService;
        this.postRepository = postRepository;
    }

    public void addPost(PostForm postForm){
        PostEntity postEntity = createEntityFromForm(postForm);
        postRepository.save(postEntity);
    }

    private PostEntity createEntityFromForm(PostForm postForm) {
        PostEntity postEntity = new PostEntity();
        postEntity.setContext(postForm.getContext());
        postEntity.setTitle(postForm.getTitle());
        postEntity.setUser(sessionService.getUserEntity());
        return postEntity;
    }

    public Iterable<PostEntity> getAllPosts(){
        return postRepository. findAllByOrderByIdDesc();
    }

    public PostEntity getAllPostData(int id) {
        return postRepository.findById(id).get();
    }


    public Optional<PostEntity> getPost(int id) {
        return postRepository.findById(id);
    }

    public void deletePostById(int id){
        postRepository.deleteById(id);
    }

    public void savePost(PostEntity postEntity){
        postRepository.save(postEntity);
    }

}
