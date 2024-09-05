package net.javaguides.springboot_restful_webservices_kennedy.repository;

import net.javaguides.springboot_restful_webservices_kennedy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRespository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
