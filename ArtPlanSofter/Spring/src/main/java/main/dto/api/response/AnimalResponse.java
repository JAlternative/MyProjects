package main.dto.api.response;

import lombok.Data;
import main.dto.AnimalDto;

@Data
public class AnimalResponse {

	private Boolean result;
	private AnimalDto animalResult;
}
