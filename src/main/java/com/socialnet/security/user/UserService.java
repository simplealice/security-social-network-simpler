package com.socialnet.security.user;

import java.time.LocalDate;
import java.util.List;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public UserResponse getUser(String email) {
    var userFromDB = userRepository.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException("User not found")
    );

    return new UserResponse(userFromDB.getId(), userFromDB.getFirstname(), userFromDB.getLastname(),
        userFromDB.getEmail(), userFromDB.getCity(), userFromDB.getUniversity(),
        userFromDB.getBirthday(), userFromDB.getStatus(),
        userFromDB.getRole().name());
  }

  public User editUser(Integer id, String newFirstname, String newLastname,
      String newCity, String newUniversity, LocalDate newBirthday,
      String newStatus) {
    User user = userRepository.getReferenceById(id);
    if (newFirstname != null) {
      user.setFirstname(newFirstname);
    }
    if (newLastname != null) {
      user.setLastname(newLastname);
    }
    if (newCity != null) {
      user.setCity(newCity);
    }
    if (newUniversity != null) {
      user.setUniversity(newUniversity);
    }
    if (newBirthday != null) {
      user.setBirthday(newBirthday);
    }
    if (newStatus != null) {
      user.setStatus(newStatus);
    }
    return userRepository.save(user);
  }
}
