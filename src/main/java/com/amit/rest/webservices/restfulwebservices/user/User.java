package com.amit.rest.webservices.restfulwebservices.user;

import com.amit.rest.webservices.restfulwebservices.post.Post;
import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "User Details")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2,message = "Name should have at least two characters")
    private String name;

    @Past(message = "Birth date must be past date from current date. You are not ELON MUSK")
    private Date birthdate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    protected User() {

    }

    public User(Integer id, String name, Date birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
