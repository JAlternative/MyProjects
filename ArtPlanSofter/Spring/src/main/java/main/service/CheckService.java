package main.service;

import main.dto.api.response.LoginResponse;
import main.model.User;
import main.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CheckService {
  private final UserRepository userRepository;

  public CheckService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public LoginResponse getLoginResponse(String email) {

    User user = userRepository.findByEmail(email);
    if (Objects.isNull(user)) {
      throw new UsernameNotFoundException("user" + email + " not found");
    }

    user.setAuthorizationAttempts(null);
    user.setAuthTime(null);
    userRepository.save(user);
    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setResult(true);
    return loginResponse;
  }

  public LoginResponse setCountAuth(String email) {
    LoginResponse loginResponse = new LoginResponse();
    Long oneHour = 3600000L;
    User user = userRepository.findByEmail(email);
    if (Objects.isNull(user)) {
      loginResponse.setResult(false);
      loginResponse.setMessage("Пользователь не существует");
      return loginResponse;
    }

    if (Objects.isNull(user.getAuthorizationAttempts())) {
      user.setAuthorizationAttempts(1);
      user.setAuthTime(System.currentTimeMillis());
    } else if ((System.currentTimeMillis() - user.getAuthTime()) > oneHour) {
      user.setAuthTime(System.currentTimeMillis());
      user.setAuthorizationAttempts(0);
    } else if (user.getAuthorizationAttempts() > 10) {
      loginResponse.setResult(false);
      loginResponse.setMessage("Количество попыток неудачной авторазиции за час больше 10");
      return loginResponse;
    } else {
      user.setAuthorizationAttempts(user.getAuthorizationAttempts() + 1);
    }

    userRepository.save(user);
    loginResponse.setResult(true);
    return loginResponse;
  }

  public LoginResponse checkUserName(String email) {
    LoginResponse loginResponse = new LoginResponse();
    User user = userRepository.findByEmail(email);
    if (Objects.isNull(user)) {
      loginResponse.setResult(true);
      loginResponse.setMessage("Имя доступно");
    } else {
      loginResponse.setResult(false);
      loginResponse.setMessage("Имя не доступно");
    }
    return loginResponse;
  }
}
