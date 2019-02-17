package com.oscarportillo.taskmanager.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.oscarportillo.taskmanager.models.User;
import com.oscarportillo.taskmanager.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // find all users
    public List<User> findAllUsers() {
    	return userRepository.findAll();
    }
    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }

    // need to updated the user info when we edit a task
    // public void updateUser(User user) {
    // 	userRepo.save(user);
    // }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

     //   checks if there is an email that already exists in the DB. not sure if you implemented this in your controller page. but this is how you'd do it.
    //  public boolean duplicateUser(String email) {
    //     User user = userRepo.findByEmail(email);
    //     if(user == null) {
    //         return false;
    //     }
    //     else {
    //     	return true;
    //     }
    // }
}