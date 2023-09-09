package com.example.practice.service;

import com.example.practice.domain.Role;
import com.example.practice.domain.User;
import com.example.practice.repository.RoleRepository;
import com.example.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor //lombok create constructor (final field)
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public User addUser(String name, String email, String password){
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new RuntimeException("already exist");
        }
        Role role = roleRepository.findByName("ROLE_USER").get();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(Set.of(role));

        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public User getUser(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<String> getRoles(int userId) {
        Set<Role> roles = userRepository.findById(userId).orElseThrow().getRoles();
        List<String> list = new ArrayList<>();
        for (Role role : roles){
            list.add(role.getName());
        }
        return list;
    }
}
