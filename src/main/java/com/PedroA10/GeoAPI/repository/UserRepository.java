package com.PedroA10.GeoAPI.repository;

import com.PedroA10.GeoAPI.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

  List<User> findByUsername(String username);

  Optional<User> findUserById(String id);

  boolean existsByEmail(String email);
}
