package com.onehundredmillion.library.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginForm {
<<<<<<< HEAD
	@NotEmpty
	private String userId;
	@NotEmpty
	private String password;

	private String name;
=======
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
>>>>>>> 563f2705796f23ccf25b0bbcd00d241a9f34bd7f
}