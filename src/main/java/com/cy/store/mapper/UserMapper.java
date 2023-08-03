package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;


/** Persistence layer interface that handles user data operations */

public interface UserMapper {
    /**
     * Insert user data
     * @param user user data
     * @return the number of rows affected
     */
    Integer insert(User user);

    /**
     * Query user data by username
     * @param username username
     * @return the matching user data, or null if there is no matching data
     */
    User findByUsername(String username);

    /**
     * Update user's password according to uid
     * @param uid user id
     * @param password new password
     * @param modifiedUser Last modified executor
     * @param modifiedTime last modification time
     * @return the number of rows affected
     */
    Integer updatePasswordByUid(
            @Param("uid") Integer uid,
            @Param("password") String password,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
    /*
    Integer updatePasswordByUid(
            Integer uid,
            String password,
            String modifiedUser,
            Date modifiedTime); */

    /**
     * Query user data according to user id
     * @param uid user id
     * @return the matching user data, or null if there is no matching user data
     */
    User findByUid(Integer uid);



    /**
     * Update user profile according to uid
     * @param user encapsulates the object of user id and new profile
     * @return the number of rows affected
     */
    Integer updateInfoByUid(User user);





    /**
     * Update the user's avatar according to uid
     * @param uid user id
     * @param avatar the path of the new avatar
     * @param modifiedUser modification executor
     * @param modifiedTime modification time
     * @return the number of rows affected
     */
    Integer updateAvatarByUid(
            @Param("uid") Integer uid,
            @Param("avatar") String avatar,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);
}
