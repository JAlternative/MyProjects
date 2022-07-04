package main.dto.api.response;

import lombok.Data;
import main.dto.ErrorDto;

@Data
public class RegisterResponse {

	private Boolean result;
	private ErrorDto errorDTO;
}