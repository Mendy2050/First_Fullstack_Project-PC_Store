package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.vo.orderVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class OrderMapperTests {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(31);
        order.setRecvName("小王");
        Integer rows = orderMapper.insertOrder(order);
        System.out.println("rows=" + rows);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(2);
        orderItem.setTitle("高档铅笔");
        Integer rows = orderMapper.insertOrderItem(orderItem);
        System.out.println("rows=" + rows);
    }


    @Test
    public void findByUid() {
        List<Integer> ordersByUid = orderMapper.findOrdersByUid(19);
        System.out.println(ordersByUid);
    }

    @Test
    public void findOrderItemsByOid(){
        Integer oid = 25;
        Integer uid = 19;
        List<orderVO> orderVOList = orderMapper.findOrderItemsByOidAndUid(oid,uid);
        System.out.println(orderVOList);

    }

    @Test
    public void find() {
        List<orderVO> vos = orderMapper.find(19);
        System.out.println(vos);
    }
}
