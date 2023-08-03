package com.cy.store.mapper;

import com.cy.store.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author Mendy
 * @create 2023-08-01 6:30
 */

@SpringBootTest
public class AdminMapperTests {
        @Autowired
        private AdminMapper adminMapper;



        @Test
        public void testDelete() {
            Integer uid = 12; // Consider using a test user ID
            int rows = adminMapper.delete(uid);
            System.out.println("Deleted rows=" + rows);
        }

        @Test
        public void testGetById() {
            Integer uid = 28; // Consider using a test user ID
            User user = adminMapper.getById(uid);
            System.out.println(user);
        }

        @Test
        public void testGetAll() {
            List<User> users = adminMapper.getAll();
            for (User user : users) {
                System.out.println(user);
            }
        }


}
