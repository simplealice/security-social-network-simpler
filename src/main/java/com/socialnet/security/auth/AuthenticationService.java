package com.socialnet.security.auth;

import com.socialnet.security.config.JwtService;
import com.socialnet.security.user.Role;
import com.socialnet.security.user.User;
import com.socialnet.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final PasswordEncoder passwordEncoder;

  public AuthenticationResponse register(RegisterRequest request, Role role) {
    var user = User.builder().
        email(request.getEmail()).
        password(passwordEncoder.encode(request.getPassword())).
        lastname(request.getLastname()).
        firstname(request.getFirstname()).
        birthday(request.getBirthday()).
        city(request.getCity()).
        university(request.getUniversity()).
        role(role).
        status("")
        .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    var user = repository.findByEmail(request.getEmail()).orElseThrow();
    var token = jwtService.generateToken(user);
    return AuthenticationResponse.builder().token(token).build();
  }
}
