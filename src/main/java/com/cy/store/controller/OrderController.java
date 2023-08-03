package com.cy.store.controller;

import com.cy.store.entity.Order;
import com.cy.store.service.IOrderService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.orderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(Integer aid, Integer[] cids, HttpSession session) {
        // Get uid and username from Session
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // Call the business object to execute the business
        Order data = orderService. create(aid, cids, uid, username);

        // return success and data
        return new JsonResult<Order>(OK, data);
    }





    @GetMapping("details")
    public JsonResult<List<List<orderVO>>> getUserAllOrders(HttpSession session) {
        Integer uid = getUidFromSession(session);

        List<List<orderVO>> orderItemsByUidAndOid = orderService.findOrderItemsByUidAndOid(uid);
        System.out.println(orderItemsByUidAndOid);

        return new JsonResult<List<List<orderVO>>>(OK, orderItemsByUidAndOid);
    }





}
