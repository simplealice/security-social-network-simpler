package com.socialnet.security.auth;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String email;
  private String password;
  private String lastname;
  private String firstname;
  private LocalDate birthday;
  private String city;
  private String university;
}
