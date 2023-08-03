package com.cy.store.service.impl;

import com.cy.store.entity.Cart;
import com.cy.store.entity.Product;
import com.cy.store.mapper.CartMapper;
import com.cy.store.service.ICartService;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.DeleteException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * The business layer implementation class that handles shopping cart data
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private IProductService productService;


    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        // Query the data in the shopping cart based on the parameters PID and UID
        Cart result = cartMapper.findByUidAndPid(uid, pid);

        Date now = new Date();

        // Determines whether the query result is null
        if (result == null) {
            //Yes: Indicates that the user did not add the item to the cart

            //Create a Cart object
            Cart cart = new Cart();

            //Encapsulated data: UID, PID, AMOUNT
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setNum(amount);

            //Call productService.findById (pid) to query commodity data and get the price of goods
            Product product = productService.findById(pid);

            // Encapsulated data: price
            cart.setPrice(product.getPrice());

            //Encapsulated data: 4 logs
            cart.setCreatedUser(username);
            cart.setCreatedTime(now);
            cart.setModifiedUser(username);
            cart.setModifiedTime(now);

            //Call insert(cart) to execute the insertion of data into the data table/
            Integer rows = cartMapper.insert(cart);
            if (rows != 1) {
                throw new InsertException("An unknown error occurred while inserting product data, contact your system administrator");
            }


        } else {
            // No: Indicates that the item is already in the user's cart
            //Get the ID of the cart data from the query results
            Integer cid = result.getCid();

            //Take the original quantity from the query result and add it to the parameter amount to obtain the new quantity
            Integer num = result.getNum() + amount;

            //The number of updates performed
            Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
            if (rows != 1) {
                throw new InsertException("An unknown error occurred while modifying the number of items, please contact your system administrator");
            }


        }
    }

    @Override
    public void deleteFromCart(Integer uid, Integer pid) {
        // Query the data in the shopping cart based on the parameters PID and UID
        Cart result = cartMapper.findByUidAndPid(uid, pid);

        //Call delete to execute the insertion of data into the data table/
        Integer rows = cartMapper.delete(pid, uid);
        if (rows != 1) {
            throw new InsertException("An unknown error occurred while deleting product data, contact your system administrator");
        }

    }

    @Override
    public void deleteMultipleFromCart(Integer uid, List<Integer> pidList) {
        if (pidList == null || pidList.isEmpty()) {
            throw new IllegalArgumentException("The pid list cannot be null or empty.");
        }

        // Delete multiple cart items in one go
        Integer rowsAffected = cartMapper.deleteMultiple(uid, pidList);
        if (rowsAffected != pidList.size()) {
            throw new DeleteException("Error occurred while trying to delete multiple cart items.");
        }
    }


    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }


    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        //Call findByCid (cid) to query shopping cart data based on the cid parameter
        Cart result = cartMapper.findByCid(cid);

        //Determines whether the query result is null
        if (result == null) {
            //Yes: Throw a CartNotFoundException
            throw new CartNotFoundException("The cart data you are trying to access does not exist");
        }

        //Determine whether the uid in the query result is inconsistent with the uid parameter
        if (!result.getUid().equals(uid)) {
            //Yes: Throws an AccessDeniedException
            throw new AccessDeniedException("Unauthorized access");
        }

        //Check if the quantity of the item is greater than (for increasing quantity) or less than (for decreasing quantity)
        //According to the original quantity in the query result, increase by 1 to get the new quantity num
        Integer num = result.getNum() + 1;

        //Creates the current time object, as modifiedTime
        Date now = new Date();

        //Call updateNumByCid (cid, num, modifiedUser, modifiedTime) to perform the number of modifications
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("An unknown error occurred while modifying the number of items, please contact your system administrator");
        }

        //Returns the new quantity
        return num;
    }


    @Override
    public Integer minusNum(Integer cid, Integer uid, String username) {
        //Call findByCid (cid) to query shopping cart data based on the cid parameter
        Cart result = cartMapper.findByCid(cid);

        //Determines whether the query result is null
        if (result == null) {
            //Yes: Throw a CartNotFoundException
            throw new CartNotFoundException("The cart data you are trying to access does not exist");
        }

        //Determine whether the uid in the query result is inconsistent with the uid parameter
        if (!result.getUid().equals(uid)) {
            //Yes: Throws an AccessDeniedException
            throw new AccessDeniedException("Unauthorized access");
        }

        //Check if the quantity of the item is greater than (for increasing quantity) or less than (for decreasing quantity)
        //According to the original quantity in the query result, increase by 1 to get the new quantity num
        Integer num = result.getNum() - 1;

        //Creates the current time object, as modifiedTime
        Date now = new Date();

        //Call updateNumByCid (cid, num, modifiedUser, modifiedTime) to perform the number of modifications
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("An unknown error occurred while modifying the number of items, please contact your system administrator");
        }

        //Returns the new quantity
        return num;
    }


    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> list = cartMapper.findVOByCids(cids);
        /**
         for (CartVO cart : list) {
         if (!cart.getUid().equals(uid)) {
         list.remove(cart);
         }
         }
         */
        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;
    }
}
