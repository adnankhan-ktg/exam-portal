package com.portal.models.content;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Question {
	
	@Id
	private long quesid;
	private String content;
	private String image;
	private String explanation;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String answer;
	private long qid;
	
	

}
