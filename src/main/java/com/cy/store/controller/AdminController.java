package com.cy.store.controller;

import com.cy.store.entity.User;
import com.cy.store.service.IAdminService;
import com.cy.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Mendy
 * @create 2023-06-29 14:04
 */

@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;


    @DeleteMapping("/{uid}")
    public JsonResult delete(@PathVariable Integer uid) {
        boolean flag = adminService.delete(uid);
//        System.out.println(flag);
        int codeState=0;
        if(flag=true){
            codeState=20021;
        }else{
            codeState=20020;
        }
        return new JsonResult(codeState, flag);
    }

    @GetMapping("/{uid}")
    public JsonResult getById(@PathVariable Integer uid) {

//        int i =1/0;

        User userById = adminService.getById(uid);
        Integer code = userById != null ? Code.GET_OK : Code.GET_ERR;
        String msg = userById != null ? "" : "Data retrieval failed, please try again";
        return new JsonResult(code,userById);
    }

    @GetMapping
    public JsonResult getAll() {
        List<User> userList = adminService.getAll();
        Integer code = userList != null ? Code.GET_OK : Code.GET_ERR;
        String msg = userList != null ? "" : "Data retrieval failed, please try again";
        return new JsonResult(code,userList);
    }


}
