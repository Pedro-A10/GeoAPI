package com.PedroA10.GeoAPI.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (username.equals("ADMIN")) {
      return User.withUsername("ADMIN")
        .password("$2a$10$R8o7LZtIu/jBzDywH1iVmeJhHaRzFvA1UhV4vXZ6JoWUPTtHuR8Ba")
        .roles("ADMIN")
        .build();
    }
    throw new UsernameNotFoundException("User not found: " + username);
  }
}
