package vttp2023.assessment.csf.orderbackend.controllers;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vttp2023.assessment.csf.orderbackend.models.Order;
import vttp2023.assessment.csf.orderbackend.models.OrderSummary;
import vttp2023.assessment.csf.orderbackend.services.OrderService;

import java.util.List;

@Controller
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderRestController {

    @Autowired
    private OrderService orderSvc;

    // TODO Task 6
    @PostMapping(path = "/order", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postOrder(@RequestBody Order order) {
        try {
            // Process the order by calling the service
            orderSvc.createOrder(order);
            System.out.println("Order created: " + order);
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
    @GetMapping(path = "/order/{email}/all",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getOrderAll(@PathVariable String email) {

        try {
            // Get orders for the email
            List<OrderSummary> orders = orderSvc.getOrdersByEmail(email);
            System.out.println("Retrieving Order Summaries:"+ orders);

            // Create a JSON array builder
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (OrderSummary order : orders) {
                arrBuilder.add(
                        Json.createObjectBuilder()
                                .add("orderId", order.getOrderId())
                                .add("name", order.getName())
                                .add("email", order.getEmail())
                                .add("amount", order.getAmount())
                                .build()
                );
            }
            // Build the final JSON array
            JsonArray resp = arrBuilder.build();
            return ResponseEntity.ok(resp.toString());

        } catch (Exception ex) {
            JsonObject errResp = Json.createObjectBuilder()
                    .add("error", ex.getMessage())
                    .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errResp.toString());
        }
    }

}
