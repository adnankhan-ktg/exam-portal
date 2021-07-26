package com.portal.controllers.user.registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portal.models.user.User;
import com.portal.models.user.login.passwordforget.MailRequest;
import com.portal.models.user.registration.Request;
import com.portal.repository.user.UserRepository;
import com.portal.service.user.UserService;
import com.portal.services.MailService;
import com.portal.services.OtpService;

@RestController
@CrossOrigin
public class UserRegistrationController {
	
	private static final Logger log = LoggerFactory.getLogger(UserRepository.class);
	@Autowired
	private OtpService otpService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailService mailService;
	@Autowired
	private UserService userService;
//	@Autowired
//	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@PostMapping("/registration_otp")
	public ResponseEntity<?> getOtp(@RequestBody Request request)
	{
		log.info("Request came on the otp controller");
		log.info("Object send to repository layer for check->user exist or not");
		if(this.userRepository.findByUsername(request.getUsername()) != null)
		{
			log.debug("user already registerd");
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
		}
         log.info("user not exits");
		int GeneratedOtp = otpService.generateOTP(request.getUsername());
		String GeneratedOtpString = Integer.toString(GeneratedOtp);
		MailRequest mailRequest = new MailRequest();
		mailRequest.setMessage("Your Registration OTP ("+GeneratedOtpString+")");
		mailRequest.setReceiverAddress(request.getUsername());
		mailRequest.setSubject("OTP For Registration");
		log.info("send OTP on the user email");
		mailService.sendemail(mailRequest);
		return ResponseEntity.status(HttpStatus.OK).build();
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody User user)
	{
		log.info("check user exists or not");
		if(this.userRepository.findByUsername(user.getUsername()) != null)
		{
			log.debug("user already registerd");
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();
		}
		
		int serverOtp = otpService.getOtp(user.getUsername());
		int clientOtp = Integer.parseInt(user.getOtp());
		log.info("match the otp");
		if(serverOtp == clientOtp)
		{
			log.info("OtP is successfully matched");
			otpService.clearOTP(user.getUsername());
			
			 User tempUser = null;
//			 user.setPassword(this.bcryptPasswordEncoder.encode(user.getPassword()));
			 tempUser = this.userService.addUser(user);
			 if(tempUser != null)
			 {
				 log.debug("new User successfully registerd");
			return ResponseEntity.status(HttpStatus.OK).build();
			 }
			 else {
				 log.debug("new user not register");
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			 }
			
		}
		
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
	}
	

}
