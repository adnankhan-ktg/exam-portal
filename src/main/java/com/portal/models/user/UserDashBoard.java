package com.portal.models.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class UserDashBoard {
	
	@Id
	private String id;
	private String username_ref;
	private String quizName;
	private String studentName;
	private String result;
	private String attemptedQuestion;
	private String correctAnswer;
	private String totalQuestion;
	private String timeStamp;

}
 