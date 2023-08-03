package com.cy.store.mapper;

import com.cy.store.entity.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** A persistent layer interface that handles shipping address data */
public interface AddressMapper {
    /**
     * Insert shipping address data
     * @param address delivery address data
     * @return Number of rows affected
     */
    Integer insert(Address address);




    /**
     * Count the amount of delivery address data of a user
     * @param uid user uid
     * @return The amount of shipping address data for that user
     */
    Integer countByUid(Integer uid);






    /**
     * Query the shipping address list data of a user
     * @param uid The user ID to which the shipping address belongs
     * @return Shipping address list data for the user
     */
    List<Address> findByUid(Integer uid);



    /**
     * According to the delivery address AID value, query the details of the delivery address
     * @param aid shipping address ID
     * @return matching shipping address details, null if there is no matching data
     */
    Address findByAid(Integer aid);




    /**
     * Set all shipping addresses for a user to non-default addresses
     * @param uid The user ID to which the shipping address belongs
     * @return Number of rows affected
     */
    Integer updateNonDefaultByUid(Integer uid);




    /**
     * Sets the specified shipping address as the default address
     * @param aid shipping address ID
     * @param modifiedUser modifies the executor
     * @param modifiedTime modification time
     * @return Number of rows affected
     */
    Integer updateDefaultByAid(
            @Param("aid") Integer aid,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);






    /**
     * Data is deleted based on the shipping address ID
     * @param aid shipping address ID
     * @return Number of rows affected
     */
    Integer deleteByAid(Integer aid);




    /**
     * Query the last modified delivery address of a user
     * @param uid user ID
     * @return The last modified shipping address of the user, null if the user does not have delivery address data
     */
    Address findLastModified(Integer uid);

}
