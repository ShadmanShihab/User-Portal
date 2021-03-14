package com.registration.userportal.repository;

import com.registration.userportal.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    boolean existsByAuthority(String name);

    @Query(value = "select r from Role r where r.enabled = true")
    List<Role> findAllByEnabledRole();

}
