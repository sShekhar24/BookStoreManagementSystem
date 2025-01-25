package com.shaan.library.manager.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
	
	private long userId;
	private String firstName;
	private String lastName;
	private String email;
	
}
