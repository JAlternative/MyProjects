package main.dto.api.response;

import lombok.Data;

@Data
public class LoginResponse {

	private Boolean result;
	private String message; //пользователь успешно авторизован
}
