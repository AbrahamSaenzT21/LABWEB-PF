package com.abraham.cart.dl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.abraham.cart.domain.Item;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ItemRepositoryH2 {
    
    private JdbcTemplate jdbcTemplate;

    public ItemRepositoryH2(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Item> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM MERCADO",
                (rs, rowNum) -> new Item(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("DESC"),
                        rs.getBigDecimal("PRICE"),
                        rs.getString("IMAGEFILENAME")
                )
        );
    }

    public Item findById(int id) {
        List<Item> items = jdbcTemplate.query(
                "SELECT * FROM MERCADO WHERE ID = ?",
                new Object[]{id},
                (rs, rowNum) -> new Item(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("DESC"),
                        rs.getBigDecimal("PRICE"),
                        rs.getString("IMAGEFILENAME")
                )
        );
        if (items.isEmpty()) {
            return null;
        } else {
            return items.get(0);
        }
    }


}
