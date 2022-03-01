package com.te.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
// @JsonIncludeProperties
public class AdminResponse {
	private boolean error;
	private String message;
	private String token;

}
