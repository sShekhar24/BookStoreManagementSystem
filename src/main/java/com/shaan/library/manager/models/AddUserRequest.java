package com.shaan.library.manager.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
	
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z ]{2,20}$", message="First name should be 2-20 chars long")
	private String firstName;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z ]{2,20}$", message="Last name should be 2-20 chars long")
	private String lastName;
	@NotBlank
	@Pattern(regexp = "^[a-zA-Z0-9 ]{3,50}$", message="Username should be 3-50 chars long")
	private String username;
	@Email(message = "Not a valid email")
	private String email;
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Not a valid password")
	private String password;
}
