package com.cy.store.service;

import com.cy.store.entity.User;
import com.cy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
public class UserServiceTests {
    @Autowired
    private IUserService iUserService;

    @Test
    public void reg() {
        try {
            User user = new User();
            user.setUsername("ceshi01");
            user.setPassword("123456");
            user.setGender("female");
            user.setPhone("17858802974");
            user.setEmail("lower@cy.cn");
            user.setAvatar("avatar.png");
            iUserService.reg(user);
            System.out.println("registration success! ");
        } catch (ServiceException e) {
            System.out.println("Registration failed! " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login() {
        try {
            String username = "asdf";
            String password = "123456";
            User user = iUserService.login(username, password);
            System.out.println("Successful login! " + user);
        } catch (ServiceException e) {
            System.out.println("Login failed! " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changePassword() {
        try {
            Integer uid = 11;
            String username = "lower";
            String oldPassword = "123456";
            String newPassword = "888888";
            iUserService.changePassword(uid, username, oldPassword, newPassword);
            System.out.println("Password changed successfully! ");
        } catch (ServiceException e) {
            System.out.println("Password modification failed! " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getByUid() {
        try {
            Integer uid = 19;
            User user = iUserService.getByUid(uid);
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo() {
        try {
            Integer uid = 19;
            String username = "Data Administrator";
            User user = new User();
            user.setPhone("15512328888");
            user.setEmail("admin03@cy.cn");
            user.setGender("male");
            iUserService.changeInfo(uid, username, user);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeAvatar() {
        try {
            Integer uid = 20;
            String username = "Avatar Manager";
            String avatar = "/upload/avatar.png";
            iUserService.changeAvatar(uid, username, avatar);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
