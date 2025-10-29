package com.PedroA10.GeoAPI.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

  @Id
  private String id;

  @NotEmpty
  @Size(min = 5, max = 20, message = "Username must be 5 to 20 characters long")
  private String username;

  @Email
  @NotEmpty
  private String email;

  @NotEmpty
  @Size(min = 8, max = 100, message = "Password must be 8 to more characters")
  private String password;
}
