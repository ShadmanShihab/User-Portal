package com.registration.userportal.service;

import com.registration.userportal.domain.UserDto;
import com.registration.userportal.model.User;
import com.registration.userportal.repository.RoleRepository;
import com.registration.userportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = (User) userRepository.findByEmail(email).orElseThrow();
        return user;
    }

    public String saveUser(UserDto userDto) {

        if(userRepository.existsByEmail(userDto.getEmail())){
            return "exist";
        }
    }
}
