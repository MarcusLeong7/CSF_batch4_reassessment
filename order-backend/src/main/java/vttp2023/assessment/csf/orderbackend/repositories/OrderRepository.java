package vttp2023.assessment.csf.orderbackend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import vttp2023.assessment.csf.orderbackend.models.Order;

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
        template.update(
                SQL_INSERT_ORDER,
                orderId,
                order.getName(),
                order.getEmail(),
                pizzaId
        );
    }

    // TODO Task 7

}
