package vttp2023.assessment.csf.orderbackend.controllers;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import vttp2023.assessment.csf.orderbackend.models.Order;
import vttp2023.assessment.csf.orderbackend.services.OrderService;

@Controller
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {

    @Autowired
    private OrderService orderSvc;

    // TODO Task 6
    public ResponseEntity<String> postOrder(@RequestBody Order order) {
        try {
            // Process the order by calling the service
            orderSvc.createOrder(order);
            // Create success response with order ID
            JsonObject resp = Json.createObjectBuilder()
                    .add("orderId", order.getOrderId())
                    .build();
            // Return 200 status with the order ID
            return ResponseEntity.status(HttpStatus.OK)
                    .body(resp.toString());
        } catch (Exception ex) {
            // If there's an error, create error response
            JsonObject errResp = Json.createObjectBuilder()
                    .add("message", ex.getMessage())
                    .build();

            System.out.println(errResp.toString());

            // Return 400 status with the error message
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errResp.toString());
        }
    }


    // TODO Task 7
    public ResponseEntity<String> getOrderAll() {

        return null;
    }

}
