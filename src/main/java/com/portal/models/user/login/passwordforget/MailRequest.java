package com.portal.models.user.login.passwordforget;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailRequest {
	private String receiverAddress;
	private String message;
	private String subject;

}
