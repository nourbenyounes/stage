package Repositories;

import org.springframework.stereotype.Repository;

import Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findById(String username);
    List<User> findAll();
}
