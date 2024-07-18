package org.example.kafkatesktaskconsumer.user;


import java.util.Optional;

import org.example.kafkatesktaskconsumer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<User, String>,
        JpaSpecificationExecutor<User> {

    Optional<User> findByEmail(String email);
}
