package com.ust.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

	
	private Long id;
	private String name;
	private String genre;
	private int year;
	
	
	
	
}
