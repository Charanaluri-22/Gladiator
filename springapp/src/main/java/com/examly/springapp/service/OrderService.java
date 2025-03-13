package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Orders;

/**
 * Interface defining the contract for OrderService.
 */
public interface OrderService { 

    /**
     * Adds a new order to the system.
     *
     * @param newOrder the order to be added.
     * @return the added order.
     *  @author Shantanu
     */
    Orders addOrder(Orders newOrder);

    /**
     * Retrieves all orders from the system.
     *
     * @return a list of all orders.
     */
    List<Orders> getAllOrders();    

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order to be retrieved.
     * @return the order with the specified ID.
     */
    Orders getOrderById(long orderId);

    /**
     * Retrieves orders associated with a specific customer ID.
     *
     * @param customerId the ID of the customer whose orders are to be retrieved.
     * @return the orders associated with the specified customer ID.
     */
    List<Orders> getOrderByCustomerId(long customerId); 

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to be deleted.
     * @return true if the order was successfully deleted, false otherwise.
     */
    boolean deleteOrder(long orderId);      
    public Orders updateOrderStatus(long orderId, String status);  
}
