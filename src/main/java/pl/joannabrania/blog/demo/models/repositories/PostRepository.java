package pl.joannabrania.blog.demo.models.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.joannabrania.blog.demo.models.PostEntity;


import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {
    List<PostEntity> findAllByOrderByIdDesc();
}
