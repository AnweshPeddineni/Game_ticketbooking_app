package com.gameticket.app.dao;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;
import com.gameticket.app.pojo.User;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Component
public class UserDAO extends DAO{
	public void saveUser(User user) {
        try {
        begin(); //inherited from super class DAO
        getSession().persist(user);
        commit();
        } catch(Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteUser(User user) {
        begin();
        getSession().remove(user);
        commit();
    }
    
    public void deleteUserById(int userid) {
        begin();
        getSession().remove(getSession().get(User.class, userid));
        commit();
    }
    
    public User getUser(int userid) {
        User user = getSession().get(User.class, userid);
        return user;
    }
    
    public User getUserByEmail(String email) {
    	Query query = getSession().createQuery("select u FROM User u where u.email = :email", User.class);
        query.setParameter("email", email);
        Stream<User> user = query.getResultStream();
        return user.findFirst().get();
    }

}
