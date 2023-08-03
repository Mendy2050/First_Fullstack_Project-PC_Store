package com.cy.store.controller;

import com.cy.store.service.ICartService;
import com.cy.store.util.JsonResult;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.image.ImageObserver.ERROR;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {


    @Autowired
    private ICartService cartService;



    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        System.out.println("pid=" + pid);
        System.out.println("amount=" + amount);

        // Get uid and username from Session
        Integer uid = getUidFromSession(session);

        String username = getUsernameFromSession(session);

        // Call the business object to add to the shopping cart
        cartService.addToCart(uid, pid, amount, username);

        // return success
        return new JsonResult<Void>(OK);
    }


    @RequestMapping("delete_from_cart")
    public JsonResult<Void> deleteFromCart(Integer pid,  HttpSession session) {
        System.out.println("pid=" + pid);

        // Get uid and username from Session
        Integer uid = getUidFromSession(session);


        // Call the business object to add to the shopping cart
        cartService.deleteFromCart(uid, pid);

        // return success
        return new JsonResult<Void>(OK);
    }


    @RequestMapping("delete_multiple_from_cart")
    public JsonResult<Void> deleteMultipleFromCart(String pidListStr, HttpSession session) {
        if (pidListStr == null || pidListStr.trim().isEmpty()) {
            return new JsonResult<Void>(ERROR, "No product ids provided for deletion");
        }

        List<Integer> pidList = Arrays.stream(pidListStr.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Integer uid = getUidFromSession(session);

        cartService.deleteMultipleFromCart(uid, pidList);

        return new JsonResult<Void>(OK);
    }


    @GetMapping({"", "/"})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session) {
        // Get uid from Session
        Integer uid = getUidFromSession(session);

        // Call the business object to execute query data
        List<CartVO> data = cartService.getVOByUid(uid);

        // return success and data
        return new JsonResult<List<CartVO>>(OK, data);
    }



    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // Get uid and username from Session
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // Call the business object to execute the increase number
        Integer data = cartService. addNum(cid, uid, username);

        // return success
        return new JsonResult<Integer>(OK, data);
    }


    @RequestMapping("{cid}/num/minus")
    public JsonResult<Integer> minusNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // Get uid and username from Session
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        // Call the business object to execute the increase number
        Integer data = cartService. minusNum(cid, uid, username);

        // return success
        return new JsonResult<Integer>(OK, data);
    }



    @GetMapping("list")
    public JsonResult<List<CartVO>> getVOByCids(Integer[] cids, HttpSession session) {
        // Get uid from Session
        Integer uid = getUidFromSession(session);

        // Call the business object to execute query data
        List<CartVO> data = cartService.getVOByCids(uid, cids);

        // return success and data
        return new JsonResult<>(OK, data);
    }


}