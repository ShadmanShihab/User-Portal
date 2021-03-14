package com.registration.userportal.initial_data;

import com.registration.userportal.model.Role;
import com.registration.userportal.model.User;
import com.registration.userportal.repository.RoleRepository;
import com.registration.userportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class Initial_Data implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        Stream.of(role1, role2)
        .filter(s-> !roleRepository.existsByAuthority(s.getAuthority()))
                .map(roleRepository::save)
                .forEach(System.out::println);

        String password = passwordEncoder.encode(("admin"));
        User admin = new User("admin@localhost.local", password);

        if(!userRepository.existsByUsername(admin.getUsername())){
            admin.setRole(role1);
            userRepository.save(admin);
            role1.setEnabled(false);
            roleRepository.save(role1);
        }
    }
}
