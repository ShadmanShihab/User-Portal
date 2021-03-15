package com.registration.userportal.service;

import com.registration.userportal.domain.ResetPasswordDto;
import com.registration.userportal.domain.UserDto;
import com.registration.userportal.model.Role;
import com.registration.userportal.model.User;
import com.registration.userportal.repository.RoleRepository;
import com.registration.userportal.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = (User) userRepository.findByUsername(email).orElseThrow();
        return user;
    }

    public String saveUser(UserDto userDto) {

        if(userRepository.existsByUsername(userDto.getUsername())){
            return "exist";
        }
        else{
            Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow();
            String pass = encoder.encode(userDto.getPassword());

            User user = new User();

            BeanUtils.copyProperties(userDto, user);

            LocalDate birthdate = LocalDate.parse(userDto.getBirthdate());

            user.setBirthDate(birthdate);
            user.setPassword(pass);
            user.setRole(role);

            userRepository.save(user);
            return "not-exist";
        }
    }

    public UserDto getUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        userDto.setRoleName(user.getRole().getAuthority());

        userDto.setBirthdate(user.getBithdate().toString());
        return userDto;
    }

    public String savePassword(ResetPasswordDto resetPasswordDto) {
        User user = userRepository.findById(resetPasswordDto.getUserId()).get();
        String currentPassword = resetPasswordDto.getCurrentPassword();
        String newPassword = resetPasswordDto.getNewPassword();
        String confirmPassword = resetPasswordDto.getConfirmPassword();

        if(encoder.matches(currentPassword, user.getPassword()) && newPassword.equals(confirmPassword)){
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return "redirect:/user/home";
        }
        else{
            return "reset-password";
        }
    }
}
