package com.portal.controllers.result;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.portal.models.user.login.passwordforget.MailRequest;
import com.portal.services.MailService;

@RestController
@CrossOrigin
public class ExamResult {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExamResult.class);
	@Autowired
	private MailService mailService;
   
	   
	   @PostMapping("/send_result")
	   public ResponseEntity<?> sendResult(@RequestParam("file1") MultipartFile file)
	   {
		   log.info("The Request came on the Exam Result controller");
		   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	                .getPrincipal();
        	String username = userDetails.getUsername();
        	System.out.print(username);
        	MailRequest mailRequest = new MailRequest();
        	mailRequest.setReceiverAddress(username);
        	mailRequest.setSubject("For Result");
        	mailRequest.setMessage("Your Exam Result");
        	
        	mailService.attech(mailRequest, file);
        	
        	return ResponseEntity.status(HttpStatus.OK).build();
	   }
}