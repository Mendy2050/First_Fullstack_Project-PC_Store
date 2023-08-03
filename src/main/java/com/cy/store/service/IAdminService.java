package com.cy.store.service;

import com.cy.store.entity.User;


import java.util.List;

/**
 * @author Mendy
 * @create 2023-07-31 16:41
 */

public interface IAdminService {
    /**
     * delete
     * @param uid
     * @return
     */
    public boolean delete(Integer uid);

    /**
     * search by ID
     * @param uid
     * @return
     */
    public User getById(Integer uid);

    /**
     * search all
     * @return
     */
    public List<User> getAll();
}
