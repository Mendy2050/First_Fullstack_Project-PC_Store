package com.cy.store.service.impl;

import com.cy.store.entity.Address;
import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.IAddressService;
import com.cy.store.service.ICartService;
import com.cy.store.service.IOrderService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.CartVO;
import com.cy.store.vo.orderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** Business layer implementation classes that handle orders and order data */
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ICartService cartService;

    @Transactional
    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        // Creates a current-time object
        Date now = new Date();

        // Query the data in the checked shopping cart list according to CIDS
        List<CartVO> carts = cartService.getVOByCids(uid, cids);

        // Calculate the total price of these items
        long totalPrice = 0;
        for (CartVO cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }

        // Create an order data object
        Order order = new Order();

        // Complete data: uid
        order.setUid(uid);

        // Query shipping address data
        Address address = addressService.getByAid(aid, uid);

        // Complete data: 6 items related to the delivery address
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvCountry(address.getCountryName());
        order.setRecvCityOrCounty(address.getCityOrCountyName());
        order.setRecvAddress(address.getAddress());

        // Complete data: totalPrice
        order.setTotalPrice(totalPrice);

        // Complete data: status
        order.setStatus(0);

        // Complete data: Time of order
        order.setOrderTime(now);

        // Complete data: log
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);

        // Insert order data
        Integer rows1 = orderMapper.insertOrder(order);
        if (rows1 != 1) {
            throw new InsertException("An unknown error occurred while inserting order data, please contact your system administrator");
        }

        // Traverse the carts to insert order item data in a loop
        for (CartVO cart : carts) {
            // Create order product data
            OrderItem item = new OrderItem();

            // Complete data: setOid(order.getOid())
            item.setOid(order.getOid());

            // Complete data: pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());

            // Complete data: 4 logs
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);

            // Insert order product data
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("An unknown error occurred while inserting order item data, contact your system administrator");
            }
        }

        // return
        return order;
    }



    @Transactional(readOnly = true)
    @Override
    public List<List<orderVO>> findOrderItemsByUidAndOid(Integer uid) {
        if (uid == null || uid <= 0) {
            throw new IllegalArgumentException("Invalid UID provided.");
        }

        //Obtain all the order IDs of the user through the uid
        List<Integer> orderIds = orderMapper.findOrdersByUid(uid);

        if (orderIds == null || orderIds.isEmpty()) {
            try {
                throw new Exception("No orders found for the given UID.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<List<orderVO>> allOrdersItems = new ArrayList<>();

        for (Integer oid : orderIds) {
            // Get all the items of that order based on each OID and UID
            List<orderVO> orderItems = orderMapper.findOrderItemsByOidAndUid(oid, uid);

            if (orderItems != null && !orderItems.isEmpty()) {
                allOrdersItems.add(orderItems);
            }
        }



        return allOrdersItems;
    }






}
