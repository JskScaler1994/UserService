package com.example.userservice.Repo;

import com.example.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/* Repositories should be interfaces. It's obvious if we think that in Repo we write only
*  Method definitions and not the logic */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String Email);
    User save(User user);
}