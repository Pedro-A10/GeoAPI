package com.PedroA10.GeoAPI.mapper;

import com.PedroA10.GeoAPI.dto.userdto.UserRequestDTO;
import com.PedroA10.GeoAPI.dto.userdto.UserResponseDTO;
import com.PedroA10.GeoAPI.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserResponseDTO toResponseDTO(User user);

  User toEntity(UserRequestDTO dto);

  List<UserResponseDTO> toResponseDTOList(List<User> users);

  void updateEntityFromDTO(UserRequestDTO dto, @MappingTarget User entity);
}
