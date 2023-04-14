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
                        rs.getString("PHONE")
                )
        );
    }
}
