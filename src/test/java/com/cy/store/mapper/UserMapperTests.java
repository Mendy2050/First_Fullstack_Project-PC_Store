package com.cy.store.mapper;

import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootTest
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("user08");
        user.setPassword("123456");
        Integer rows = userMapper.insert(user);
        System.out.println("rows=" + rows);
    }



    @Test
    public void findByUsername() {
        String username = "lower";
        User result = userMapper.findByUsername(username);
        System.out.println(result);
    }

    @Test
    public void updatePasswordByUid() {
        Integer uid = 17;
        String password = "654321";
        String modifiedUser = "General Administrator";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updatePasswordByUid(uid, password, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByUid() {
        Integer uid = 11;
        User result = userMapper.findByUid(uid);
        System.out.println(result);
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(19);
        user.setPhone("17858802222");
        user.setEmail("admin@cy.com");
        user.setGender("female");
        user.setModifiedUser("System Administrator");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void updateAvatarByUid() {
        Integer uid = 19;
        String avatar = "/upload/avatar.png";
        String modifiedUser = "Super Administrator";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updateAvatarByUid(uid,
                                                    avatar,
                                                    modifiedUser,
                                                    modifiedTime);
        System.err.println("rows=" + rows);
    }
}

