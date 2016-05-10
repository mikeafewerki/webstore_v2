/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.webstore.service.impl;

import edu.mum.waa.webstore.domain.User;
import edu.mum.waa.webstore.repository.UserRepository;
import edu.mum.waa.webstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 984986
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;
    
    @Override
    public boolean authenticate(User user) {
        for(User us : userRepo.getAllUsers())
            if(us.equals(user))
                return true;
        return false;
    }
    
}
