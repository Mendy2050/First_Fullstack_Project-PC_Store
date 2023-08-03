package com.cy.store.service.impl;

import java.util.UUID;
import com.cy.store.entity.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.IUserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

/** Business layer implementation class for processing user data */

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public void reg(User user) {
        // Obtain the registered user name according to the parameter user object
        String username = user.getUsername();

        // Call the User findByUsername(String username) method of the persistence layer to query user data according to the username
        User result = userMapper.findByUsername(username);

        // Determine whether the query result is not null
        if (result != null) {
            // Yes: Indicates that the username is already taken, and a UsernameDuplicateException is thrown
            throw new UsernameDuplicateException("The username [" + username + "] you are trying to register is already taken");
        }

       // create current time object
        Date now = new Date();

        // Complete data: encrypted password
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = getMd5Password(user. getPassword(), salt);
        user.setPassword(md5Password);

        // Complete data: salt value
        user.setSalt(salt);

        // Complete data: isDelete(0)
        user.setIsDelete(0);

        // Complete data: 4 log attributes
        user.setCreatedUser(username);
        user.setCreatedTime(now);
        user.setModifiedUser(username);
        user.setModifiedTime(now);


        // Indicates that the username is not occupied, and registration is allowed
        // Call the Integer insert(User user) method of the persistence layer, perform registration and get the return value (the number of rows affected)
        Integer rows = userMapper.insert(user);

        // Determine whether the number of affected rows is not 1
        if (rows != 1) {
            // Yes: if there is some kind of error when inserting data, InsertException will be thrown
            throw new InsertException("An unknown error occurred while adding user data, please contact the system administrator");
        }
    }





   /**
     * Perform password encryption
     * @param password original password
     * @param salt salt value
     * @return encrypted ciphertext
     */
    private String getMd5Password(String password, String salt) {
        /*
         * Encryption rules:
         * 1. Regardless of the strength of the original password
         * 2. Use UUID as the salt value, splicing on the left and right sides of the original password
         * 3. Cyclic encryption 3 times
         */
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }





    @Override
    public User login(String username, String password) {
        // Call the findByUsername() method of userMapper to query user data according to the parameter username
        User result = userMapper.findByUsername(username);

        // Check if the query result is null
        if (result == null) {
            // Yes: Throw UserNotFoundException exception
            throw new UserNotFoundException("User data does not exist error");
        }

        // Determine whether isDelete in the query result is 1
        if ( result.getIsDelete() == 1) {

            // Yes: Throw UserNotFoundException exception
            throw new UserNotFoundException("User data does not exist error");
        }

        String oldPassword = result.getPassword();

        // Get the salt value from the query result
        String salt = result. getSalt();

        // Call the getMd5Password() method to combine the parameters password and salt for encryption
        String newMd5Password = getMd5Password(password, salt);

        // Determine whether the password in the query result is inconsistent with the password obtained by the above encryption
        if (!newMd5Password. equals(oldPassword)) {
            // Yes: Throw PasswordNotMatchException exception
            throw new PasswordNotMatchException("Password verification failed error");
        }

        // Create a new User object
        User user = new User();

        // Encapsulate uid, username, and avatar in the query result into a new user object
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        user.setIsAdmin(result.getIsAdmin());

        // return new user object
        return user;
    }




    @Override
    public void changePassword(Integer uid,
                               String username,
                               String oldPassword,
                               String newPassword) {

        // Call the findByUid() method of userMapper to query user data according to the parameter uid
        User result = userMapper.findByUid(uid);

        // Check if the query result is null
        if (result == null) {
            // Yes: Throw UserNotFoundException exception
            throw new UserNotFoundException("User data does not exist");
        }

        // Check if isDelete is 1 in the query result
        if (result. getIsDelete(). equals(1)) {
            // Yes: Throw UserNotFoundException exception
            throw new UserNotFoundException("User data does not exist");
        }

        // Get the salt value from the query result
        String salt = result. getSalt();

        // Encrypt the parameter oldPassword with the salt value to get oldMd5Password
        String oldMd5Password = getMd5Password(oldPassword, salt);

        // Determine whether the password in the query result is inconsistent with oldMd5Password
        if (!result. getPassword(). contentEquals(oldMd5Password)) {
            // Yes: Throw PasswordNotMatchException exception
            throw new PasswordNotMatchException("The original password is wrong");
        }

        // Encrypt the parameter newPassword with the salt value to get newMd5Password
        String newMd5Password = getMd5Password(newPassword, salt);

        // create current time object
        Date now = new Date();

        // Call updatePasswordByUid() of userMapper to update the password and get the return value
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, now);

        // Determine whether the number of affected rows returned above is not 1
        if (rows != 1) {
            // Yes: throw UpdateException exception
            throw new UpdateException("An unknown error occurred while updating user data, please contact the system administrator");
        }
    }




    @Override
    public User getByUid(Integer uid) {
        // Call the findByUid() method of userMapper to query user data according to the parameter uid
        User result = userMapper.findByUid(uid);

        // Check if the query result is null
        if (result == null) {
            // Yes: Throw UserNotFoundException exception
            throw new UserNotFoundException("User data does not exist");
        }

        // Determine whether isDelete in the query result is 1
        if (result. getIsDelete(). equals(1)) {
            // Yes: Throw UserNotFoundException exception
            throw new UserNotFoundException("User data does not exist");
        }

        // Create a new User object
        User user = new User();

        // Encapsulate username/phone/email/gender in the above query results into a new User object
        user.setUsername(result.getUsername());
        user.setPhone(result.getPhone());
        user.setEmail(result.getEmail());
        user.setGender(result.getGender());

        // return new User object
        return user;
    }




    @Override
    public void changeInfo(Integer uid, String username, User user) {
        // Call the findByUid() method of userMapper to query user data according to the parameter uid
        User result = userMapper.findByUid(uid);

        // Check if the query result is null
        if (result == null) {
            // Yes: Throw UserNotFoundException exception
            throw new UserNotFoundException("User data does not exist");
        }

        // Determine whether isDelete in the query result is 1
        if (result. getIsDelete(). equals(1)) {
            // Yes: Throw UserNotFoundException exception
            throw new UserNotFoundException("User data does not exist");
        }

        // Complete the data to the parameter user: uid
        user.setUid(uid);

        // Complete the data to the parameter user: modifiedUser(username)
        user.setModifiedUser(username);

        // Complete the data to the parameter user: modifiedTime(new Date())
        user.setModifiedTime(new Date());

        // Call the updateInfoByUid(User user) method of userMapper to modify and get the return value
        Integer rows = userMapper. updateInfoByUid(user);

        // Determine whether the number of affected rows returned above is not 1
        if (rows != 1) {
            // Yes: throw UpdateException exception
            throw new UpdateException("An unknown error occurred while updating user data, please contact the system administrator");
        }
    }




    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        // Call the findByUid() method of userMapper to query user data according to the parameter uid
        User result = userMapper.findByUid(uid);

        // Check if the query result is null
        if (result == null) {
            // Yes: Throw UserNotFoundException
            throw new UserNotFoundException("User data does not exist");
        }

        // Check if isDelete is 1 in the query result
        if (result. getIsDelete(). equals(1)) {
            // Yes: Throw UserNotFoundException
            throw new UserNotFoundException("User data does not exist");
        }

        // create current time object
        Date now = new Date();

        // Call the updateAvatarByUid() method of userMapper to update and get the return value
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, now);

        // Determine whether the number of affected rows returned above is not 1
        if (rows != 1) {
            // yes: throw UpdateException
            throw new UpdateException("An unknown error occurred while updating user data, please contact the system administrator");
        }
    }
}
