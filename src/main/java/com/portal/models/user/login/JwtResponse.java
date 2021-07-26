package com.portal.models.user.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
	private String token;
	private String activeFirstName;
	private String  activeLastName;
	private String phone;
	private boolean status; 
	private String role;

}
