package com.cy.store.service;

import com.cy.store.entity.Order;
import com.cy.store.vo.orderVO;

import java.util.List;

/** Business layer interface for processing orders and order data */
public interface IOrderService {
    /**
     * Create Order
     * @param aid The id of the delivery address
     * @param cids The id of the product data to be purchased in the shopping cart table
     * @param uid the id of the currently logged in user
     * @param username current login username
     * @return successfully created order data
     */
    Order create(Integer aid, Integer[] cids, Integer uid, String username);

    List<List<orderVO>> findOrderItemsByUidAndOid(Integer uid);
}
