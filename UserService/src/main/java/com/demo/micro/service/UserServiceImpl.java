package com.demo.micro.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.micro.entities.User;
import com.demo.micro.exceptions.ResourceNotFoundException;
import com.demo.micro.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User saveUser(User user) {
		 // Check if the email already exists in the database
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("User with the provided email already exists.");
        }
		String randomUserId = UUID.randomUUID().toString();
		user.setUserID(randomUserId);
		
		
		return userRepository.save(user);
	}
	
	@Override
	public boolean isUserExists(String email) {
	    return userRepository.findByEmail(email) != null;
	}

	@Override
	public User loginUser(String name, String password) {
	    User user = userRepository.findByEmail(name);
	    if (user != null) {
	        if (user.getPassword() != null && user.getPassword().equals(password)) {
	            return user;
	        } else {
	            return null;
	        }
	    } else {
	        return null;
	    }
	}
	
	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
    public User getUser(String userId) {
	    return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !! : "+userId));
    }

    @Override
    public User updateUserDetails(User userDetails) {
        Optional<User> optionalUser = userRepository.findById(userDetails.getUserId());
        if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        user.setEmail(userDetails.getEmail());
	        user.setName(userDetails.getName());
            // Update other fields as needed
            return userRepository.save(user);
        } 
        else {
        // Handle case when user is not found
        return null;
    }
}

	@Override
	public void forgotPassword(String email) {
		// TODO Auto-generated method stub
		
	}

	
}






