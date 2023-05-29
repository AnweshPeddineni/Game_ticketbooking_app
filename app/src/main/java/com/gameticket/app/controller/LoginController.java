package com.gameticket.app.controller;

import com.gameticket.app.dao.UserDAO;
import com.gameticket.app.pojo.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@Autowired
	private UserDAO userDao;
	

    @GetMapping("/login")
    public ModelAndView showLoginForm(Model model, HttpServletRequest request) {
        model.addAttribute("user", new User());
        return new ModelAndView("login");
    }

    @PostMapping("/login")
    public ModelAndView processLogin(User user, HttpSession session, HttpServletRequest request) {
    	if(user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin")) {
    		session = request.getSession();
    		session.setAttribute("email", "admin@gmail.com");
    		session.setAttribute("token", "games-app-token");
    		return new ModelAndView("redirect:/admin");
    	}
    	
        User currentUser = userDao.getUserByEmail(user.getEmail());
        if (currentUser != null) {
            if(currentUser.getPassword().equalsIgnoreCase(user.getPassword())) {
            	session = request.getSession();
                session.setAttribute("user", currentUser);
                session.setAttribute("token", "games-app-token");
                System.out.println("Valid credentials");
            } else {
                System.out.println("Check the password, it's incorrect");
            }
        } else {
            System.out.println("Given user emailid does not exist");
        }
        return new ModelAndView("redirect:/user");
    }
    
    @GetMapping("/logout")
    public ModelAndView showLogOut(Model model, HttpServletRequest request) {
    	request.getSession().invalidate();
        
        return new ModelAndView("login");
    }
}
