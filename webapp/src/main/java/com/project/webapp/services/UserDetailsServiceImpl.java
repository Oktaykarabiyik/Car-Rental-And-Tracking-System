package com.project.webapp.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.webapp.entities.User;
import com.project.webapp.repos.UserRepository;
import com.project.webapp.security.JwtUserDetails;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

private UserRepository userRepository;
	
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }//construction injection
    
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//bu metod userdetails onjesi dönüyor
		User user = userRepository.findByUserName(username);//repo bu username i döndürsün
		return JwtUserDetails.create(user);//create metodu user ı alıp bu user a user details objesi dönüyor
	}
	
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user); 
	}
}
