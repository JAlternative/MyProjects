package main.controller;

import lombok.extern.java.Log;
import main.dto.api.request.LoginRequest;
import main.dto.api.request.RegisterRequest;
import main.dto.api.response.LoginResponse;
import main.dto.api.response.RegisterResponse;
import main.repository.UserRepository;
import main.service.CheckService;
import main.service.RegisterService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class ApiAuthController {
  private final CheckService checkService;
  private final AuthenticationManager authenticationManager;
  private final RegisterService registerService;
  private final UserRepository userRepository;

  public ApiAuthController(
      CheckService checkService,
      AuthenticationManager authenticationManager,
      RegisterService registerService,
      UserRepository userRepository) {
    this.checkService = checkService;
    this.authenticationManager = authenticationManager;
    this.registerService = registerService;
    this.userRepository = userRepository;
  }

  @PostMapping("/login")
  public LoginResponse login(@RequestBody LoginRequest loginRequest) {
    LoginResponse loginResponse = checkService.setCountAuth(loginRequest.getEmail());
    if (!loginResponse.getResult()) {
      return loginResponse;
    }
    Authentication auth =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(auth);
    User user = (User) auth.getPrincipal();
    return checkService.getLoginResponse(user.getUsername());
  }

  @PostMapping("/register")
  public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {
    return registerService.registration(registerRequest);
  }

  @GetMapping("/checkName/{email}")
  public LoginResponse checkUserName(@PathVariable("email") String email) {
    return checkService.checkUserName(email);
  }
}
