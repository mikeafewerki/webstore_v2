/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.webstore.controller;

import edu.mum.waa.webstore.domain.User;
import edu.mum.waa.webstore.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String authenticate(User user, HttpServletRequest request, RedirectAttributes redirectAttributes, Model model, HttpServletResponse response){
        if(userService.authenticate(user)){
            
            if(user.isRemember()){
               Cookie c = new Cookie("userName",user.getUserId());
               Cookie c2 = new Cookie("checked","checked");
               response.addCookie(c);
               response.addCookie(c2);
               
            }else{
                Cookie c = new Cookie("userName",null);
                Cookie c2 = new Cookie("checked",null);
                c.setMaxAge(0);
                c2.setMaxAge(0);                
                response.addCookie(c);
                response.addCookie(c2);
                
            }
            
            request.getSession().setAttribute("user", user);
            model.addAttribute("user",user);
            redirectAttributes.addFlashAttribute("mike", "Welcome to the one and only amazing webstore" + request.getParameter("remember"));
            
            return "redirect:/welcome";
        }
        else{
            redirectAttributes.addFlashAttribute("error", "UserId and / or password invalid!");
            //throw new IllegalArgumentException("UserId and / or password invalid!");
            return "redirect:/login";
        }
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(User user, HttpServletRequest request, Model model){
        request.getSession().invalidate();
        
        return "redirect:/login";
    }
}
