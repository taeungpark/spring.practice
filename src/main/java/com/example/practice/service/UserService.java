package com.example.practice.service;

import com.example.practice.dao.UserDao;
import com.example.practice.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor //lombok create constructor (final field)
public class UserService {
    private final UserDao userDao;

//    public UserService(UserDao userDao) {
//        this.userDao = userDao;
//    } @RequiredArgsConstructor

    @Transactional
    public User addUser(String name, String email, String password){
        User user1 = userDao.getUser(email);
        if(user1 != null){
            throw new RuntimeException("already exist");
        }
        User user = userDao.addUser(name, email, password);
//        userDao.getLastInsertId(); no need, when handle in addUser.
        userDao.mappingUserRole(user.getUserId()); // authorization
        return user;
    }

    @Transactional
    public User getUser(String email) {
        return userDao.getUser(email);
    }
}
