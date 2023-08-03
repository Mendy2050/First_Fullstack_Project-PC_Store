package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

/** Persistence layer interface for processing product data */
public interface ProductMapper {
    /**
     * Query the top four best-selling products
     * @return collection of the top four best sellers
     */
    List<Product> findHotList();


    /**
     * Query the top four new arrivals products
     * @return collection of the top four new arrivals
     */
    List<Product> findNewList();

    /**
     * Query product details based on product id
     * @param id product id
     * @return The matching product details, if there is no matching data, return null
     */
    Product findById(Integer id);
}