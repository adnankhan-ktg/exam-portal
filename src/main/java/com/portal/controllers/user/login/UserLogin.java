package com.portal.controllers.user.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portal.models.user.User;
import com.portal.models.user.login.JwtRequest;
import com.portal.models.user.login.JwtResponse;
import com.portal.repository.user.UserRepository;
import com.portal.security.config.JwtTokenUtil;
import com.portal.services.JwtUserDetailsService;

@RestController
@CrossOrigin
public class UserLogin {
	private static final Logger log = LoggerFactory.getLogger(UserLogin.class);
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest request)
	{
		User u = this.userRepository.findByUsername(request.getUsername());
//		if(this.userRepository.findByUsername(request.getUsername()) != null)
		if(u != null)
		{
//			System.out.println(passwordEncoder.matches(request.getPassword(), u.getPassword()));
//			User tempUser = this.userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
		
			if(passwordEncoder.matches(request.getPassword(), u.getPassword()))
			{
				final UserDetails userDetails  = this.jwtUserDetailsService.loadUserByUsername(request.getUsername());
				String token = this.jwtTokenUtil.generateToken(userDetails);
				return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token,u.getFirstName(),u.getLastName(),u.getPhone(),u.isStatus(),u.getRole()));
				
			}
			else {
				return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}

}
