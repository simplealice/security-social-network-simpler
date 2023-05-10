package com.socialnet.security.user;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserResponse {
  private Integer id;
  private String firstname;
  private String lastname;
  private String email;
  private String city;
  private String university;
  private LocalDate birthday;
  private String status;
  private String role;
}
