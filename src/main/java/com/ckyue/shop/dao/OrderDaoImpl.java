package com.ckyue.shop.dao;

import com.ckyue.shop.entities.Address;
import com.ckyue.shop.entities.Book;
import com.ckyue.shop.entities.Order;
import com.ckyue.shop.entities.User;
import com.ckyue.shop.exceptions.ShopAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao{
    private static final String SQL_CREATE_ORDER = "INSERT INTO PO(ID, FIRST_NAME, LAST_NAME, STATUS, ADDRESS) VALUES(NEXTVAL('PO_SEQ'), ?, ?, ?, ?)";
    private static final String SQL_CREATE_ADDRESS = "INSERT INTO ADDRESS(id, street, province, country, zip, phone) VALUES(NEXTVAL('ADDRESS_SEQ'), ?, ?, ?, ?, ?)";
    private static final String SQL_ADD_ITEM_TO_ORDER = "INSERT INTO POITEM(ID, BID, PRICE) VALUES(?, ?, ?)";
    private static final String SQL_GET_ORDER_BY_ID = "SELECT * FROM PO FULL OUTER JOIN POITEM ON PO.ID = POITEM.ID FULL OUTER JOIN ADDRESS ON ADDRESS.ID = PO.ID WHERE POITEM.BID = ?";
    private static final String SQL_GET_ADDRESS_BY_ID = "SELECT * FROM ADDRESS WHERE ID = ?";
    private static final String SQL_GET_BOOK_BY_BID = "SELECT * FROM BOOK WHERE BID = ?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer createAddress(String street, String province, String country, String zip, String phone) throws ShopAuthException{
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_ADDRESS, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, street);
                ps.setString(2, province);
                ps.setString(3, country);
                ps.setString(4, zip);
                ps.setString(5, phone);
                System.out.println(ps);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID");
        }catch (Exception e) {
            throw new ShopAuthException(e.getMessage());
        }
    }

    @Override
    public Integer createOrder(String first_name, String last_name, String email, Integer address) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, first_name);
                ps.setString(2, last_name);
                ps.setString(3, email);
                ps.setInt(4, address);
                System.out.println(ps);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("ID");
        }catch (Exception e) {
            throw new ShopAuthException(e.getMessage());
        }
    }

    @Override
    public String AddItemToOrder(Integer id, String bid, Integer price) {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_ADD_ITEM_TO_ORDER, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, id);
                ps.setString(2, bid);
                ps.setInt(3, price);
                System.out.println(ps);
                return ps;
            }, keyHolder);
            return (String) keyHolder.getKeys().get("BID");
        }catch (Exception e) {
            throw new ShopAuthException(e.getMessage());
        }
    }

    @Override
    public List<Order> getOrdersByBid(String bid) {
        return jdbcTemplate.query(SQL_GET_ORDER_BY_ID, new Object[]{bid}, orderRowMapper);
    }

    @Override
    public Address getAddressById(Integer id) {
        return jdbcTemplate.queryForObject(SQL_GET_ADDRESS_BY_ID, new Object[]{id}, addressRowMapper);
    }

    @Override
    public Book getBookByBid(String bid) {
        return jdbcTemplate.queryForObject(SQL_GET_BOOK_BY_BID, new Object[]{bid}, bookRowMapper);
    }

    private RowMapper<Order> orderRowMapper = ((rs, rowNum) -> {
        return new Order(rs.getInt("id"),
                rs.getString("BID"),
                rs.getInt("PRICE"),
                rs.getString("FIRST_NAME"),
                rs.getString("LAST_NAME"),
                rs.getString("STATUS"),
                this.getAddressById(rs.getInt("ADDRESS")));
    });

    private RowMapper<Address> addressRowMapper = ((rs, rowNum) -> {
        return new Address(rs.getInt("id"),
                rs.getString("STREET"),
                rs.getString("PROVINCE"),
                rs.getString("COUNTRY"),
                rs.getString("ZIP"),
                rs.getString("PHONE"));
    });

    private RowMapper<Book> bookRowMapper = ((rs, rowNum) -> {
        return new Book(rs.getString("BID"),
                rs.getString("TITLE"),
                rs.getInt("PRICE"),
                rs.getString("CATEGORY"));
    });
}
