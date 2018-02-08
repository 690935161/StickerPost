package org.hfly.springbootdemo.service;

import org.hfly.springbootdemo.entity.User;
import org.hfly.springbootdemo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public User checkUserPassword(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public boolean registerUser(String username, String password, String nickname, String description) {
        try {
            userMapper.addUser(username, password, nickname, description);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkUsernameExist(String username) {
        User user = getUserByUsername(username);
        if (user == null || user.getUsername().equals(username)) {
            return false;
        }
        return true;
    }


}
