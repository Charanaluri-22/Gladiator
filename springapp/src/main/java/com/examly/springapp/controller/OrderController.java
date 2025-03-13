package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.examly.springapp.model.Orders;
import com.examly.springapp.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * OrderController class to handle order-related endpoints.
 * Annotated with `@RestController` to indicate a RESTful controller.
 * Mapped to the `/api/order` base path using `@RequestMapping`.
 * 
 * @author Shantanu
 */

@RestController
@RequestMapping("/api/order")    
public class OrderController {

    @Autowired
    private OrderService service; 

    /**
     * Adds a new order.
     *
     * @param order the `Orders` entity provided in the request body.
     * @return a `ResponseEntity` containing the saved `Orders` entity or an error status.
     */
    @Operation(description="Posting newOrders")
    @ApiResponse(responseCode="201",description="Status code after posting new Order.")
    @PostMapping()
    public ResponseEntity<Orders> addOrder(@RequestBody Orders order){
        Orders newOrder = service.addOrder(order);
        return ResponseEntity.status(201).body(newOrder);           
    }

    /**
     * Retrieves all orders.
     *
     * @return a `ResponseEntity` containing a list of all `Orders` entities or an error status.
     */
    @Operation(description="Retrieving List of Orders")
    @ApiResponse(responseCode="200",description="Status code after retrieving all orders")
    @GetMapping()
    public ResponseEntity<List<Orders>> getAllOrders(){  
        List<Orders> newOrder = service.getAllOrders();
        return ResponseEntity.status(200).body(newOrder);    
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order.
     * @return a `ResponseEntity` containing the `Orders` entity or an error status.
     */
    @Operation(description="Retrieving order by orderId")
    @ApiResponse(responseCode="200",description="Status code after retrieving all orders by orderId")
    @GetMapping("/{orderId}") 
    public ResponseEntity<Orders> getOrderById(@PathVariable long orderId){
        Orders newOrder = service.getOrderById(orderId); 
        return ResponseEntity.status(200).body(newOrder);  
    }

    /**
     * Retrieves orders by customer ID.
     *
     * @param customerId the ID of the customer.
     * @return a `ResponseEntity` containing the `Orders` entity or an error status.
     */
    @Operation(description="Retrieving order by customerId")
    @ApiResponse(responseCode="200",description="Status code after retrieving order by customerId")
    @GetMapping("/customer/{customerId}") 
    public ResponseEntity<List<Orders>> getOrderByCustomerId(@PathVariable long customerId){  
        List<Orders> newOrders = service.getOrderByCustomerId(customerId);  
        return ResponseEntity.status(200).body(newOrders);  
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order.
     * @return a `ResponseEntity` indicating whether the deletion was successful or not.
     */
    @Operation(description="Retrieving order by orderId")
    @ApiResponse(responseCode="200",description="Status code after retrieving order by orderId")
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable long orderId){ 
        boolean newOrder = service.deleteOrder(orderId);    
        return ResponseEntity.status(200).body(newOrder);   
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<Orders> updateOrderStatus(@PathVariable long orderId, @RequestBody String status) {
        Orders updatedOrder = service.updateOrderStatus(orderId, status);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        }
        return ResponseEntity.notFound().build();
    }
}
