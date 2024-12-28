package com.example.userservice.Repo;

import com.example.userservice.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends JpaRepository<Token, Long> {
    Token save(Token token);
    Optional<Token> findByValue(String value);
}
