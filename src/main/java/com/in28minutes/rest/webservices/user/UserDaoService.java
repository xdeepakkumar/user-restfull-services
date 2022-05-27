package com.in28minutes.rest.webservices.user;

import com.in28minutes.rest.webservices.posts.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int userCount = 4;

    static {

        List<Post> posts = new ArrayList<>();
        Post post = new Post();
        post.setDescription("Description");
        posts.add(post);

        users.add(new User(1, "Adam", new Date().toString(), posts));
        users.add(new User(2, "Eve", new Date().toString(), posts));
        users.add(new User(3, "Jack", new Date().toString(), posts));
        users.add(new User(4, "Tom", new Date().toString(), posts));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int id){
        for (User user: users){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }

    public User deleteById(Integer id){
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if(user.getId().equals(id)){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

}
