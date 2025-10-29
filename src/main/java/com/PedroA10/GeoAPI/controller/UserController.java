package com.PedroA10.GeoAPI.controller;

import com.PedroA10.GeoAPI.dto.userdto.UserRequestDTO;
import com.PedroA10.GeoAPI.dto.userdto.UserResponseDTO;
import com.PedroA10.GeoAPI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping
  public ResponseEntity<List<UserResponseDTO>> listUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponseDTO> listById(@PathVariable String id) {
    Optional<UserResponseDTO> point = userService.findById(id);
    return point.map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO users) {
    try {
      UserResponseDTO newUser = userService.registerUser(users);
      return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }catch (IllegalArgumentException e){
      return ResponseEntity.badRequest().build();
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable String id) {
    try {
      userService.deleteUser(id);
      return ResponseEntity.noContent().build();
    }catch (IllegalArgumentException e) {
      return ResponseEntity.notFound().build();
    }
  }
}
