/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.webstore.service;

import edu.mum.waa.webstore.domain.User;

/**
 *
 * @author 984986
 */
public interface UserService {
    boolean authenticate (User user);
}
