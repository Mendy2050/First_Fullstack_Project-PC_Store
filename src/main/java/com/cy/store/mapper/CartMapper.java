package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** Persistence layer interface for processing shopping cart data */
public interface CartMapper {


    /**
     * Insert shopping cart data
     * @param cart shopping cart data
     * @return the number of rows affected
     */
    Integer insert(Cart cart);




    /**
     * Delete shopping cart data
     * @param pid product id
     * @param uid user id
     * @returnthe number of rows affected
     */
    Integer delete(Integer pid, Integer uid);




    /**
     * Delete multiple cart items
     * @param uid user ID
     * @param pidList product ID list
     * @return The number of rows affected
     */
    int deleteMultiple(@Param("uid") Integer uid, @Param("pidList") List<Integer> pidList);



    /**
     * Modify the quantity of items in the shopping cart data
     * @param cid The id of the shopping cart data
     * @param num new number
     * @param modifiedUser modification executor
     * @param modifiedTime modification time
     * @return the number of rows affected
     */
    Integer updateNumByCid(
            @Param("cid") Integer cid,
            @Param("num") Integer num,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);




    /**
     * Query the data in the shopping cart according to the user id and product id
     * @param uid user id
     * @param pid product id
     * @return matching shopping cart data, if the user does not have the item in the shopping cart, return null
     */
    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);




    /**
     * Query a user's shopping cart data
     * @param uid user id
     * @return list of the user's shopping cart data
     */
    List<CartVO> findVOByUid(Integer uid);





    /**
     * Query the shopping cart data details according to the shopping cart data id
     * @param cid shopping cart data id
     * @return The details of the matching shopping cart data, or null if there is no matching data
     */
    Cart findByCid(Integer cid);





    /**
     * Query the list of details according to several shopping cart data ids
     * @param cids several shopping cart data ids
     * @return list of matching shopping cart data details
     */
    List<CartVO> findVOByCids(Integer[] cids);


}