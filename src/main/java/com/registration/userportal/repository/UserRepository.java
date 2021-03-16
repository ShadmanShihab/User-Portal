package com.registration.userportal.repository;

import com.registration.userportal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String name);

    Optional<UserDetails> findByUsername(String name);

    @Query(value = "select u from User u where u.username <> 'admin@localhost.local' ")
    List<User> findAllUserWithoutAdmin();

    @Query(value = "select u from User u where u.firstName like :name% or u.lastName LIKE :name% ")
    Collection<User> findAllUserWithName(@Param("name") String name);
}
