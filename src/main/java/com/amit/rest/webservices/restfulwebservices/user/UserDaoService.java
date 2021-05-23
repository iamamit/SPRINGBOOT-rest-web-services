package com.amit.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList();
    private int userCount = 3;
    static {
        users.add(new User(1,"Amit", new Date()));
        users.add(new User(2,"Uma", new Date()));
        users.add(new User(3,"Rajat", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        for (User user:users) {
            if(user.getId()==id) return user;
        }
        return null;
    }

    public User save(User user) {
        if(null == user.getId()) {
            user.setId(++this.userCount);
        }
        users.add(user);
        return user;
    }

    public User deleteOne(int id) {
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                return user;
            }

        }
        return null;
    }


}
