package com.socialnet.security.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping("/getAll")
  public List<User> getAllUsers() {
    return userService.getUsers();
  }

  @PostMapping(value = "/get/{email}")
  public UserResponse getUser(@PathVariable("email") String email) {
    return userService.getUser(email);
  }

  @PutMapping("edit/{id}")
  public ResponseEntity<User> editUser(@PathVariable Integer id,
      @RequestBody EditUserRequest editUserRequest) {
    User editedUser = userService.editUser(id,
        editUserRequest.getNewFirstname(),
        editUserRequest.getNewLastname(),
        editUserRequest.getNewCity(),
        editUserRequest.getNewUniversity(),
        editUserRequest.getNewBirthday(),
        editUserRequest.getNewStatus());
    return new ResponseEntity<>(editedUser, HttpStatus.OK);
  }
}
