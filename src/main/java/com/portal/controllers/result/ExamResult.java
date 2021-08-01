package com.portal.controllers.result;


import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.portal.models.user.UserDashBoard;
import com.portal.models.user.login.passwordforget.MailRequest;
import com.portal.service.user.UserService;
import com.portal.services.MailService;

@RestController
@CrossOrigin
public class ExamResult {
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExamResult.class);
	@Autowired
	private MailService mailService;
	@Autowired
	private UserService userService;
   
	   
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
	   
	   @PostMapping("/add_dashboard")
	   public ResponseEntity<?> saveDashboard(@RequestBody UserDashBoard userDashBoard)
	   {
		   
		   log.info("The Request came on the Dashboard controller");
		   UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
	                .getPrincipal();
       	String username = userDetails.getUsername();
       	userDashBoard.setUsername_ref(username);
		   
		   UserDashBoard userDashBoard2 = this.userService.saveDashboard(userDashBoard);
		   if(userDashBoard2 != null)
		   {
			   return ResponseEntity.status(HttpStatus.OK).body(userDashBoard2);
		   }
		   else {
			   return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		   }
	
	   }
	   
	   @GetMapping("/get_all_dashboard_data")
	   public ResponseEntity<?> getAllDashboard()
	   {
		   List<UserDashBoard> list = null;
		   list = this.userService.getDashBoards();
		   if(list == null)
		   {
			   return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		   }
		   else {
			   return ResponseEntity.status(HttpStatus.OK).body(list);
		   }
	   }
}
