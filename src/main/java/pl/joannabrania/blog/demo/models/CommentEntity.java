package pl.joannabrania.blog.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String context;

    @JoinColumn(name = "user_id")
    @ManyToOne //wiele komentarzy do jednego usera = jeden komentarz ma jednego usera
    private UserEntity user;

    @JoinColumn(name = "post_id")
    @ManyToOne //wiele komentarzy ma jeden post = jeden komentarz ma jeden post
    @JsonIgnore
    private PostEntity post;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
