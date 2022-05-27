package com.in28minutes.rest.webservices.user;


import com.in28minutes.rest.webservices.posts.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "name should have at least 2 character.")
    private String name;

    private String birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

}
