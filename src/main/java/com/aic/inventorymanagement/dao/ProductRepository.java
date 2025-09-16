package com.aic.inventorymanagement.dao;

import com.aic.inventorymanagement.model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public int save(Product product) {
    // Get all existing IDs sorted
    String sqlIds = "SELECT id FROM products ORDER BY id ASC";
    List<Integer> ids = jdbcTemplate.queryForList(sqlIds, Integer.class);

    int nextId = 1; 

    // Find the smallest missing ID
    for (int id : ids) {
        if (id == nextId) {
            nextId++;
        } else if (id > nextId) {
            break; // found a gap
        }
    }

    String sql = "INSERT INTO products(id, name, category, price, quantity) VALUES(?, ?, ?, ?, ?)";
    return jdbcTemplate.update(sql, nextId, product.getName(), product.getCategory(),
            product.getPrice(), product.getQuantity());
   }

   
    public int update(Product product) {
        String sql = "UPDATE products SET name=?, category=?, price=?, quantity=? WHERE id=?";
        return jdbcTemplate.update(sql,
                product.getName(),
                product.getCategory(),
                product.getPrice(),
                product.getQuantity(),
                product.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM products WHERE id=?";
        return jdbcTemplate.update(sql, id);
    }

    public List<Product> findAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Product.class));
    }

    public Product findById(int id) {
        String sql = "SELECT * FROM products WHERE id=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Product.class), id);
    }
}
