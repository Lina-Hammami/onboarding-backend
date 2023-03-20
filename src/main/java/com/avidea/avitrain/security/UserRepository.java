package com.avidea.avitrain.security;

import com.avidea.avitrain.security.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, String> {

    AppUser findByEmail(String email);
}