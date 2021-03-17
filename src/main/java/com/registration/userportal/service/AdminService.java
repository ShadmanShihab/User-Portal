package com.registration.userportal.service;

import com.registration.userportal.domain.UserDto;
import com.registration.userportal.model.User;
import com.registration.userportal.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUserDtos(){
        List<User> users = userRepository.findAllUserWithoutAdmin();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User u: users) {
            UserDto userDtoTemp = new UserDto();
            BeanUtils.copyProperties(u, userDtoTemp);
            LocalDate current = LocalDate.now();
            LocalDate birthDate = u.getBithdate();
            int age = Period.between(birthDate, current).getYears();
            userDtoTemp.setAge(age);
            userDtoList.add(userDtoTemp);
        }
        return userDtoList;
    }


    public List<UserDto> getUserByName(String name) {

        List<User> users = (List<User>) userRepository.findAllUserWithName(name);
        List<UserDto> userDtoList = new ArrayList<>();

        for(int i=0; i<users.size(); i++){
            UserDto userDto = new UserDto();
            User u = users.get(i);

            BeanUtils.copyProperties(u, userDto);
            LocalDate current = LocalDate.now();
            LocalDate birthDate = u.getBirthDate();

            int age = Period.between(birthDate, current).getYears();
            userDto.setAge(age);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }
}
