package com.pheonix.digitalnotification.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailInformation {
	
	@NotNull
	private String message;
	
	//@NotNull
	private String from;
	
	private String subject;
	
	//@NotNull
	private String to;

}
