package com.demo.micro.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.demo.micro.entities.User;
import com.demo.micro.service.UserService;


@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:4200")
public class UserController {
	
	@Autowired
	private UserService userService;
	//create
	
	@PostMapping("/signup")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		HashMap<String, Object> res = new HashMap();
		
		//if user is null
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        
        // Check if the email already exists in the database
        if (userService.isUserExists(user.getEmail())) {
        	res.put("error", "email already exists");
            // Return an appropriate response with an error message 
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(res);
        }
        
        // Validate email and password
        if (user.getEmail().equals("") || user.getPassword().equals("")) {
        	res.put("error", "email or password is missing");
            // Return an appropriate response with an error message 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
        
        //if password is smaller than 8
        if(user.getPassword().length() < 8) {
        	res.put("error", "password cannot be less than 8 characters");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
        
        //if user name is empty
        if(user.getName().equals("")) {
        	res.put("error", "name is empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(res);
        }
        
        // if user is successfully created  
        try {
        User createdUser = userService.saveUser(user);
        res.put("user", createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    } 
     // the case when user creation fails
        catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user.");
    }
}
	
	
	@PostMapping("/login")
	public ResponseEntity<User>loginUser(@RequestBody Map<String, String> user) {
		
		String email = user.get("email");
		String password = user.get("password");
		User userFromDb = userService.loginUser(email,password);
		
		 if (userFromDb != null) {   
		        Map<String, Object> res = new HashMap<>();
		        res.put("auth", true);
		        return ResponseEntity.ok(userFromDb);
		    }
		 else {
		        Map<String, Object> res = new HashMap<>();
		        res.put("auth", false);
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		    }
		}
	
	
	@GetMapping("/get")
	 public ResponseEntity<User> getSingleUser(@RequestParam("userId") String userId) {
       if (userId.contentEquals("") || userId.isEmpty()) {
           // Return bad request if the userId parameter is not provided
           return ResponseEntity.badRequest().build();
       }

       User user = userService.getUser(userId);
       if (user != null) {
           // Return the user with a OK response
           return ResponseEntity.ok(user);
       } else {
           // when user is not found
           return ResponseEntity.notFound().build();
       }
	}
	
	
	 @GetMapping
	    public ResponseEntity<List<User>> getAllUser(@RequestParam("admin") boolean isAdmin) {
	        if (isAdmin) {
	            List<User> allUser = userService.getAllUser();
	            return ResponseEntity.ok(allUser);
	        } else {
	            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	        }
	    }
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
		@PutMapping("/update")
		 public ResponseEntity<User> updateUser(@RequestBody User user) {
	        if (user == null) {
	            // Return 400 (Bad Request) if the user object is not provided
	            return ResponseEntity.badRequest().build();
	        }

	        User updatedUser = userService.updateUserDetails(user);
	        if (updatedUser != null) {
	            // Return the updated user with a 200 (OK) response
	            return ResponseEntity.ok(updatedUser);
	        } else {
	            // If user update fails
	            // Return an appropriate response with an error message and HTTP status code 500 (Internal Server Error)
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }
		
}