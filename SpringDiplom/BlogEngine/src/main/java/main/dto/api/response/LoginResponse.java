package main.dto.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import main.dto.UserLoginDto;

@Data
public class LoginResponse {

  private Boolean result;

  @JsonProperty("user")
  private UserLoginDto userLoginDTO;
}
