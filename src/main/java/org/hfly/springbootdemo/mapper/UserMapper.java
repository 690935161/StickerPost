package org.hfly.springbootdemo.mapper;

import org.apache.ibatis.annotations.Param;
import org.hfly.springbootdemo.entity.User;

import java.util.List;

public interface UserMapper {
    public void addUser(@Param("username") String username, @Param("password") String password, @Param("nickname") String nickname, @Param("description") String description);

    public User getUserByUsername(@Param("username") String username);

    public User getUserById(@Param("userId") int id);

    public List<User> getAllUser();

    public void updateUser(@Param("userID") int userID, @Param("nickname") String nickname, @Param("password") String password, @Param("description") String description);
}