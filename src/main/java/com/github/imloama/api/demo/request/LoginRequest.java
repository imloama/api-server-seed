package com.github.imloama.api.demo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable{

	@NotNull
	private String username;
	@NotNull
	private String password;
}
