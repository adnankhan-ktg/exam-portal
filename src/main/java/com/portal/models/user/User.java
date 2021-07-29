package com.portal.models.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
@Component
public class User {
	@Id
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String profile;
	private String role = "NORMAL";
	private boolean status = true;
	private String otp;

}
