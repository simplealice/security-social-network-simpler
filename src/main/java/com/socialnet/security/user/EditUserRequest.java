package com.socialnet.security.user;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditUserRequest {
  private String newFirstname;
  private String newLastname;
  private String newCity;
  private String newUniversity;
  private LocalDate newBirthday;
  private String newStatus;
}
