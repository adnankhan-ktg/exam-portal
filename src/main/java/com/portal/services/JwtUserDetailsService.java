package com.portal.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.portal.models.user.User;
import com.portal.repository.user.UserRepository;


import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
 @Autowired
 private UserRepository userRepostitory;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       
	
             User user = new User();
             
             user = this.userRepostitory.findByUsername(username);
             if(user == null)
             {
            	 throw new UsernameNotFoundException(username);
             }
             return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),new ArrayList<>());

		 
		 
		 
		 
		 
		 
	}

	}
