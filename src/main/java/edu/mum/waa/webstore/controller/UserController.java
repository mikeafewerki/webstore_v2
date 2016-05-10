/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.webstore.controller;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import edu.mum.waa.webstore.domain.User;
import edu.mum.waa.webstore.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author 984986
 */
@Controller
//@SessionAttributes("user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String getLoginForm(){
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authenticate(User user, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model){
        if(userService.authenticate(user)){
            request.getSession().setAttribute("user", user);
            model.addAttribute("user",user);
            redirectAttributes.addFlashAttribute("mike", "Welcome to the one and only amazing webstore");
            return "redirect:/welcome";
        }
        else{
            redirectAttributes.addFlashAttribute("error", "UserId and / or password invalid!");
            //throw new IllegalArgumentException("UserId and / or password invalid!");
            return "redirect:/login";
        }
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model model){
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
