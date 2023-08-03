package com.cy.store.service;

import com.cy.store.entity.Order;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.ex.ServiceException;
import com.cy.store.vo.orderVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class OrderServiceTests {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private IOrderService orderService;

    @Test
    public void create() {
        try {
            Integer aid = 19;
            Integer[] cids = {10};
            Integer uid = 19;
            String username = "asdf";
            Order order = orderService.create(aid, cids, uid, username);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

   @Test
    public void testFindBytwo(){
       Integer uid = 19;
       List<Integer> orders = orderMapper.findOrdersByUid(uid);

       List<List<orderVO>> allOrdersItems = new ArrayList<>();

       for (Integer oid : orders) {
           // Get all the items of that order based on each OID and UID
           List<orderVO> orderItems = orderMapper.findOrderItemsByOidAndUid(oid, uid);

           if (orderItems != null && !orderItems.isEmpty()) {
               allOrdersItems.add(orderItems);
           }
       }

       // Print the result
       for (List<orderVO> details : allOrdersItems) {
           for (orderVO item : details) {
               System.out.println(item); // Assuming orderVO has a meaningful toString() method
           }
           System.out.println(); // Print an empty line to separate order items from different orders
       }
   }
}
