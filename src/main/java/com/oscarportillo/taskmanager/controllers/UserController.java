package com.oscarportillo.taskmanager.controllers;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.oscarportillo.taskmanager.models.User;
import com.oscarportillo.taskmanager.services.UserService;
import com.oscarportillo.taskmanager.validator.UserValidator;

@Controller
public class UserController {
	private final UserService userService;
	 
	private final UserValidator userValidator;
    
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
      
	 @GetMapping("")
	 public String logNreg(@ModelAttribute("user") User user) {
		 return "index.jsp";
	 }
	 
	 @RequestMapping(value="/registration", method=RequestMethod.POST)
	 public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
	     // if result has errors, return the registration page (don't worry about validations just now)
	     // else, save the user in the database, save the user id in session, and redirect them to the /home route
		 userValidator.validate(user, result);
		 if(result.hasErrors()) {
	    	 return "index.jsp";
	     } else {
	    	 System.out.println("no errors, proceed to saving to db");
		     User u = userService.registerUser(user);
		     System.out.println("sent to service");
		     session.setAttribute("userId", u.getId());
		     System.out.print("this should be the id-----> ");
		     System.out.println(u.getId());
		     return "redirect:/tasks";
	     }
	 }
		 @RequestMapping(value="/login", method=RequestMethod.POST)
		 public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		     // if the user is authenticated, save their user id in session
		     // else, add error messages and return the login page
			 boolean isAuthenticated = userService.authenticateUser(email, password);
			 if(isAuthenticated) {
				 User u = userService.findByEmail(email);
				 session.setAttribute("userId", u.getId());
				 return "redirect:/tasks";
			 } else {
				 model.addAttribute("error", "Invalid Credentiasl. Please try again.");
				 return "index.jsp";
			 }
		 }
		 @RequestMapping("/logout")
		 public String logout(HttpSession session) {
		     // invalidate session
		     // redirect to login page
			 session.invalidate();
			 return "redirect:/";
		 }
}
