package com.gj.test.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gj.test.user.model.User;
import com.gj.test.user.service.UserService;

@RestController 
@RequestMapping("/user") 
public class UserController {
	
	@Autowired
	private UserService userService;

	@RequestMapping("/index") 
    public String home() {  
        return "Hello World!";  
    }  
	
	@RequestMapping("/{id}") 
	public User get(@PathVariable("id")Long id) {  
        return userService.get(id);  
    }  
}
