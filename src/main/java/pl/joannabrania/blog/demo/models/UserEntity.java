package pl.joannabrania.blog.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    @JsonIgnore
    private String email;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private int age;

    @Column(name = "registered_date")
    private LocalDateTime registerDate;


    //Nie można usunać encji, ktore sa gdzies prztrzymywane (u nas w sesji)
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER /* cascade = CascadeType.ALL */, orphanRemoval = true)
    @JsonIgnore
    private List<PostEntity> posts;
}