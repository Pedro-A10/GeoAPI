package com.PedroA10.GeoAPI.service;

import com.PedroA10.GeoAPI.dto.userdto.UserRequestDTO;
import com.PedroA10.GeoAPI.dto.userdto.UserResponseDTO;
import com.PedroA10.GeoAPI.exception.EmailAlreadyExistsException;
import com.PedroA10.GeoAPI.exception.UserNotFoundException;
import com.PedroA10.GeoAPI.mapper.UserMapper;
import com.PedroA10.GeoAPI.model.User;
import com.PedroA10.GeoAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
    if (userRepository.existsByEmail(requestDTO.getEmail())) {
      throw new EmailAlreadyExistsException("Email already exists: " + requestDTO.getEmail());
    }

    User users = userMapper.toEntity(requestDTO);
    users.setPassword(passwordEncoder.encode(users.getPassword()));
    User saveUser = userRepository.save(users);
    return userMapper.toResponseDTO(saveUser);
  }

  public List<UserResponseDTO> findAll() {
    return userMapper.toResponseDTOList(userRepository.findAll());
  }

  public UserResponseDTO findById(String id) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    return userMapper.toResponseDTO(user);
  }

  public void deleteUser(String id) {
    if (!userRepository.existsById(id)) {
      throw new UserNotFoundException("User not found with id " + id);
    }
    userRepository.deleteById(id);
  }
}
