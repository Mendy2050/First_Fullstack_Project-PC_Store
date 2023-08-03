package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

/** Business-layer interfaces that process product data */
public interface ICartService {
    /**
     * Add items to cart
     * @param uid ID of the currently logged in user
     * @param pid of item
     * @param amount of increased quantity
     * @param username The user name that is currently logged in
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);




    /**
     * Delete items from cart
     * @param uid ID of the currently logged in user
     * @param pid of item
     */
    void deleteFromCart(Integer uid, Integer pid);


    void deleteMultipleFromCart(Integer uid, List<Integer> pidList);




    /**
     * Query a user's shopping cart data
     * @param uid user id
     * @return list of the user's shopping cart data
     */
    List<CartVO> getVOByUid(Integer uid);




    /**
     * Increment the quantity of an item in the shopping cart by 1
     * @param cid The id of the shopping cart quantity
     * @param uid the id of the currently logged in user
     * @param username current login username
     * @return the new amount after successful increment
     */
    Integer addNum(Integer cid, Integer uid, String username);


    /**
     * Decrement the quantity of an item in the shopping cart by 1
     * @param cid The id of the shopping cart quantity
     * @param uid the id of the currently logged in user
     * @param username current login username
     * @return the new amount after successful decrement
     */
    Integer minusNum(Integer cid, Integer uid, String username);




    /**
     * Query the list of details according to several shopping cart data ids
     * @param uid the id of the currently logged in user
     * @param cids several shopping cart data ids
     * @return list of matching shopping cart data details
     */
    List<CartVO> getVOByCids(Integer uid, Integer[] cids);



}
