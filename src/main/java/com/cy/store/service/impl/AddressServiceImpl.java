package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.IDistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private IDistrictService districtService;

    @Value("${user.address.max-count}")
    private int maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        // Call the countByUid (Integer uid) method of addressMapper according to the uid parameter to count the amount of delivery address data of the current user
        Integer count = addressMapper.countByUid(uid);

        // Determine whether the quantity reaches the upper limit
        if (count > maxCount) {
            //Yes: Throws An AddressCountLimitException
            throw new AddressCountLimitException("The number of shipping addresses has reached the upper limit (" + maxCount + ") ！");
        }

        // Complete data: The name of the province, city, and district
        String countryName = address.getCountryName();
        String cityOrCountyName = address.getCityOrCountyName();
        address.setCountryName(countryName);
        address.setCityOrCountyName(cityOrCountyName);

        // Complete the data: Encapsulate the parameter uid into the parameter address
        address.setUid(uid);

        // Complete the data: According to the above statistics, get the correct is Default value (whether it is default: 0-not default, 1-default), and encapsulate
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);

        // Complete data: 4 logs
        Date now = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        address.setModifiedUser(username);
        address.setModifiedTime(now);

        // Call addressMapper's insert (Address address) method to insert the shipping address data and get the number of affected rows returned
        Integer rows = addressMapper.insert(address);

        // Determine whether the number of affected rows is not 1
        if (rows != 1) {
            // Yes: Throws An InsertException
            throw new InsertException("An unknown error occurred while inserting the shipping address data, please contact your system " +
                    "administrator! ");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        /*
        for (Address address : list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }*/
        return list;
    }

    @Transactional
    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        // According to the aid parameter, call findByAid() in AddressMapper to query the shipping address data
        Address result = addressMapper.findByAid(aid);

        // Determines whether the query result is null
        if (result == null) {
            // Yes: Throws An AddressNotFoundException
            throw new AddressNotFoundException("The shipping address data that you are trying to access does not exist");
        }

        // Determine whether the uid in the query result is inconsistent with the parameter uid (use equals() to determine)
        if (!result.getUid().equals(uid)) {
            // Yes: Throws An AccessDeniedException
            throw new AccessDeniedException("Exceptions for illegal access");
        }

        // Call address Mapper's updateNonDefaultByUid() to set all shipping addresses for that user to non-default and get the number of rows affected
        Integer rows = addressMapper.updateNonDefaultByUid(uid);

        // Determine whether the number of affected rows is less than 1 (not greater than 0)
        if (rows < 1) {
            // Yes: Throws An UpdateException
            throw new UpdateException("An unknown error occurred while setting the default shipping address[1]");
        }

        // call addressMappeleupdateDefaultByAid() to set the shipping address of the specified aid as the default and get the number of affected
        // rows returned
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());

        // Determine whether the number of affected rows is not 1
        if (rows != 1) {
            // Yes: Throws An UpdateException
            throw new UpdateException("An unknown error occurred while setting the default shipping address [2]");
        }
    }

    @Transactional
    @Override
    public void delete(Integer aid, Integer uid, String username) {
        // According to the aid parameter, call findByAid() to query the shipping address data
        Address result = addressMapper.findByAid(aid);

        // Determines whether the query result is null
        if (result == null) {
            //  Yes: Throws An AddressNotFoundException
            throw new AddressNotFoundException("The shipping address data that you are trying to access does not exist");
        }

        // Determine whether the uid in the query result is inconsistent with the parameter uid (use equals() to determine)
        if (!result.getUid().equals(uid)) {
            //  Yes: Throws An AccessDeniedException: Illegal access
            throw new AccessDeniedException("Unauthorized access");
        }

        // According to the aid parameter, call deleteByAid() to perform the deletion
        Integer rows1 = addressMapper.deleteByAid(aid);
        if (rows1 != 1) {
            throw new DeleteException("An unknown error occurred while deleting shipping address data, please contact your system administrator");
        }

        // Determines whether the is Default in the query result is 0
        if (result.getIsDefault() == 0) {
            return;
        }

        // Call the countbyUid() of the persistence layer to count how many shipping addresses are left at present
        Integer count = addressMapper.countByUid(uid);

        // Determine whether the current number of delivery addresses is 0
        if (count == 0) {
            return;
        }

        //Call findLastModified() to find out the most recently modified shipping address data of the user
        Address lastModified = addressMapper.findLastModified(uid);

        // Find the aid attribute value from the above query results
        Integer lastModifiedAid = lastModified.getAid();

        // Call the updateDefaultByAid() method of the persistence layer to set the default shipping address and get the number of affected rows returned
        Integer rows2 = addressMapper.updateDefaultByAid(lastModifiedAid, username, new Date());

        // Determine whether the number of affected rows is not 1
        if (rows2 != 1) {
            //  Yes: Throws An UpdateException
            throw new UpdateException("An unknown error occurred while updating shipping address data, contact your system administrator");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // Query the details of the shipping address based on the shipping address data ID
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("The shipping address data that you are trying to access does not exist");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("Unauthorized access");
        }

        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }
}
