package com.pheonix.digitalnotification.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class RegistrationEvent {

	@NonNull
	private String id;

	@NonNull
	private String email;

	@NonNull
	private String userId;


}
