package com.cy.store.service;

import com.cy.store.entity.User;

/** Business layer interface for processing user data */
public interface IUserService {


    /**
     * User registration
     * @param user user data
     */
    void reg(User user);



    /**
     * User login
     * @param username username
     * @param password password
     * @return User data of successful login
     */
    User login(String username, String password);



   /**
     * change Password
     * @param uid currently logged in user id
     * @param username username
     * @param oldPassword original password
     * @param newPassword new password
     */
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword);




   /**
     * Get the information of the currently logged in user
     * @param uid the id of the currently logged in user
     * @return the information of the currently logged in user
     */
    User getByUid(Integer uid);




    /**
     * Modify user profile
     * @param uid the id of the currently logged in user
     * @param username current login username
     * @param user user's new data
     */
    void changeInfo(Integer uid, String username, User user);




   /**
     * Modify user avatar
     * @param uid the id of the currently logged in user
     * @param username current login username
     * @param avatar The path to the user's new avatar
     */
    void changeAvatar(Integer uid, String username, String avatar);
}
