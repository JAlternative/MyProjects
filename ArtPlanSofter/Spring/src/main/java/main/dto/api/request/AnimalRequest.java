package main.dto.api.request;

import lombok.Data;

@Data
public class AnimalRequest {

	private String kind;
	private String dateOfBirth;
	private String gender;
	private String name;
}
