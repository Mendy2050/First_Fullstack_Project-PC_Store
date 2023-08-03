package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.apache.ibatis.annotations.*;

import java.awt.print.Book;
import java.util.List;

/**
 * @author Mendy
 * @create 2023-07-31 16:39
 */

@Mapper
public interface AdminMapper {

    int delete(Integer id);
    User getById(Integer id);
    List<User> getAll();
}
