package com.cy.store.service;

import com.cy.store.entity.Address;

import java.util.List;

/** Business layer interface for processing delivery address data */
public interface IAddressService {


    /**
     * Create a new shipping address
     * @param uid the id of the currently login user
     * @param username current login username
     * @param address The delivery address data submitted by the user
     */
    void addNewAddress(Integer uid, String username, Address address);




    /**
     * Query a user's shipping address list data
     * @param uid The user id of the delivery address
     * @return the user's shipping address list data
     */
    List<Address> getByUid(Integer uid);




    /**
     Set default shipping address
     @param aid delivery address id
     @param uid belonged user id
     @param username The currently login username
     */
    void setDefault(Integer aid, Integer uid, String username);





    /**
     * Delete shipping address
     * @param aid delivery address id
     * @param uid user id
     * @param username current login username
     */
    void delete(Integer aid, Integer uid, String username);






    /**
    * According to the id of the delivery address data, query the details of the delivery address
     * @param aid delivery address id
     * @param uid user id
     * @return Details of the matching delivery address
     */
    Address getByAid(Integer aid, Integer uid);



}











