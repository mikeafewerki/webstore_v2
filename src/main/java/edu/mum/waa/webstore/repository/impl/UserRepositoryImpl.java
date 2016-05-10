/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.webstore.repository.impl;

import edu.mum.waa.webstore.domain.User;
import edu.mum.waa.webstore.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 984986
 */
@Repository
public class UserRepositoryImpl implements UserRepository{

    private List<User> users = new ArrayList<User>();
    
    public UserRepositoryImpl(){
        User user1 = new User();
        user1.setUserId("st01");
        user1.setPassword("user123");
        
        User user2 = new User();
        user2.setUserId("st02");
        user2.setPassword("user123");
        
        User user3 = new User();
        user3.setUserId("st03");
        user3.setPassword("user123");
        
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }
    
    @Override
    public User getUsertById(String userId) {
        for(User user : users)
            if(user.getUserId().equals(userId))
                return user;
        throw new IllegalArgumentException(
                String.format("User with id %s not found", userId));
    }

//    public List<User> getUsers() {
//        return users;
//    }

    @Override
    public List<User> getAllUsers() {
       return users;
    }
    
    
}
