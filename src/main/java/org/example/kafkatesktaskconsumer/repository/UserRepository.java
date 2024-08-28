package org.example.kafkatesktaskconsumer.repository;


import java.util.Optional;

import java.util.UUID;
import org.example.kafkatesktaskconsumer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, UUID>,
        JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);
}
