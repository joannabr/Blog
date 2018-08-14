package pl.joannabrania.blog.demo.models.forms;

import lombok.Data;

@Data
public class RegisterForm {
    private String username;
    private String email;
    private String password;
    private int age;
}
