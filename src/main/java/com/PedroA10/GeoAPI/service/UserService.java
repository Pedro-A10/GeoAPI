package com.PedroA10.GeoAPI.service;

import com.PedroA10.GeoAPI.dto.userdto.UserRequestDTO;
import com.PedroA10.GeoAPI.dto.userdto.UserResponseDTO;
import com.PedroA10.GeoAPI.mapper.UserMapper;
import com.PedroA10.GeoAPI.model.User;
import com.PedroA10.GeoAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  UserMapper userMapper;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserResponseDTO registerUser(UserRequestDTO requestDTO) {
    User users = userMapper.toEntity(requestDTO);
    users.setPassword(passwordEncoder.encode(users.getPassword()));
    User saveUser = userRepository.save(users);
    return userMapper.toResponseDTO(saveUser);
  }

  public List<UserResponseDTO> findAll() {
    List<User> usersList =  userRepository.findAll();
    return userMapper.toResponseDTOList(usersList);
  }

  public Optional<UserResponseDTO> findById(String id) {
    Optional<User> usersOPT = userRepository.findById(id);
    return usersOPT.map(userMapper::toResponseDTO);
  }

  public void deleteUser(String id) {
    userRepository.deleteById(id);
  }
}
