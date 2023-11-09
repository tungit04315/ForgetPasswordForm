package com.poly.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class Student {
	private String fullname;
	private String email;
	private double marks;
	private boolean gender;
	private String country;
}
