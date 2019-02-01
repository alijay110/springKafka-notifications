package com.pheonix.digitalnotification.model;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DigitalEmailNotification {

	@NotNull
	private EmailInformation email;
	

	@NotNull
	private EmailCustomerDetails emailCustomerDetails;
	
}
