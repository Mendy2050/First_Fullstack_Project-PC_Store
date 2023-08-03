package com.cy.store.service.impl;

import com.cy.store.controller.Code;
import com.cy.store.controller.ex.BusinessException;
import com.cy.store.entity.User;
import com.cy.store.mapper.AdminMapper;
import com.cy.store.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

/**
 * @author Mendy
 * @create 2023-07-31 16:41
 */

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;


    public boolean delete(Integer id) {
        return adminMapper.delete(id)>0;
    }

    public User getById(Integer uid)  {

        if(uid == 1){
            try {
                throw new Exception("Please don't use your technical skills to challenge my patience!!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return adminMapper.getById(uid);
    }

    public List<User> getAll() {
        return adminMapper.getAll();
    }

}
