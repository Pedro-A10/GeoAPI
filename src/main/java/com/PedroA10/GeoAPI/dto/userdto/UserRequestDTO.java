package com.PedroA10.GeoAPI.dto.userdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRequestDTO {

  @NotEmpty
  @Size(min = 5, max = 20)
  private String username;

  @NotEmpty
  @Email
  private String email;

  @NotEmpty
  @Size(min = 8, max = 100)
  private String password;
}
