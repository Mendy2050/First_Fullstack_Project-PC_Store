package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;
import com.cy.store.vo.orderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/** Persistence layer interface for processing orders and order product data */
public interface OrderMapper {


    /**
     * Insert order data
     * @param order order data
     * @return the number of rows affected
     */
    Integer insertOrder(Order order);



    /**
     * Insert order item data
     * @param orderItem order item data
     * @return the number of rows affected
     */
    Integer insertOrderItem(OrderItem orderItem);



    // Join two tables based on oid and pid
    List<Integer> findOrdersByUid(@Param("uid") Integer uid);

    List<orderVO> find(@Param("uid") Integer uid);

    List<orderVO> findOrderItemsByOidAndUid(Integer oid, Integer uid);


    // Update status in store.t_order by oid
    Integer updateStatus(@Param("oid") Integer oid, @Param("status") Integer status);



}