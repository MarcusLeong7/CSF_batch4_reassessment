package vttp2023.assessment.csf.orderbackend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vttp2023.assessment.csf.orderbackend.models.Order;
import vttp2023.assessment.csf.orderbackend.models.OrderSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static vttp2023.assessment.csf.orderbackend.repositories.Queries.*;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate template;

    // TODO Task 6 - you are free to add parameters and change the return type
    // DO NOT CHANGE THE METHOD NAME
    public void createOrder(Order order, Integer orderId, String pizzaId) {
        // Insert into SQL orders table
        System.out.println("Creating order " + orderId + " for pizza " + pizzaId);
        template.update(
                SQL_INSERT_ORDER,
                orderId,
                order.getName(),
                order.getEmail(),
                pizzaId
        );
    }

    // TODO Task 7
    public List<OrderSummary> getOrders (String email){
        SqlRowSet rs = template.queryForRowSet(SQL_GET_ORDERS, email);
        List<OrderSummary> orders = new ArrayList<>();

        while (rs.next()) {
            OrderSummary summary = new OrderSummary();
            summary.setOrderId(rs.getInt("order_id"));
            summary.setName(rs.getString("name"));
            summary.setEmail(rs.getString("email"));
            summary.setAmount(rs.getFloat("amount"));
            orders.add(summary);
        }

        return null;
    }

}
