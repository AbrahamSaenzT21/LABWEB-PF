package com.abraham.cart.dl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abraham.cart.domain.Customer;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class CustomerRepositoryH2 {
    
    private JdbcTemplate jdbcTemplate;

    public CustomerRepositoryH2(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Customer> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM CUSTOMER",
                (rs, rowNum) -> new Customer(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE"),
                        rs.getString("PASSWORD")
                )
        );
    }

    public Customer findByEmail(String email) {
        String sql = "SELECT * FROM CUSTOMER WHERE EMAIL = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email}, (rs, rowNum) ->
                new Customer(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("LASTNAME"),
                        rs.getString("EMAIL"),
                        rs.getString("PHONE"),
                        rs.getString("PASSWORD")
                )
        );
    }

    public void save(Customer customer) {
        String sql = "INSERT INTO CUSTOMER (id, name, lastname, email, phone, password) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, customer.getCustomerId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhone(), customer.getPassword());
    }
}
