package com.examly.springapp.service.serviceimpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.NoContentException;
import com.examly.springapp.model.Orders;
import com.examly.springapp.repository.OrderRepo;
import com.examly.springapp.service.OrderService;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

/**
 * Service implementation for managing order-related operations.
 * 
 * Annotated with `@Service` to indicate it's a Spring service class.
 * Implements the `OrderService` interface to provide specific business logic.
 * 
 * @author Shantanu
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;
    private static final String  ORDER_NOT_FOUND = "ORDER NOT FOUND WITH ID: ";

    /**
     * Adds a new order to the system.
     * 
     * @param newOrder the new order entity to be added.
     * @return the saved order entity.
     * @throws EntityExistsException if an order with the same ID already exists.
     */
    @Override
    public Orders addOrder(Orders newOrder) {
        long orderId = newOrder.getOrderId();
        Optional<Orders> foundOrder = orderRepo.findById(orderId);
        if (!foundOrder.isPresent()) {
            return orderRepo.save(newOrder);
        }
        throw new EntityExistsException("Order with ID: " + orderId + " already present.");
    }

    /**
     * Retrieves a list of all orders.
     * 
     * @return a list of all order entities.
     */
    @Override
    public List<Orders> getAllOrders() {  
        List<Orders>  orders = orderRepo.findAll();
        if(orders.isEmpty()){
            throw new NoContentException("No Orders Found");
        }
        return orders;
    }

    /**
     * Retrieves an order by its ID.
     * 
     * @param orderId the ID of the order.
     * @return the found order entity.
     * @throws EntityNotFoundException if the order with the given ID is not found.
     */
    @Override
    public Orders getOrderById(long orderId) {
        Optional<Orders> foundOrder = orderRepo.findById(orderId); 
        if (foundOrder.isPresent()) { 
            return foundOrder.get();
        }
        throw new EntityNotFoundException(ORDER_NOT_FOUND + orderId);
    }

    /**
     * Retrieves an order by the customer ID.
     * 
     * @param customerId the ID of the customer.
     * @return the found order entity.
     * @throws EntityNotFoundException if the customer with the given ID is not found.
     */
    @Override
    public List<Orders> getOrderByCustomerId(long customerId) {
        List<Orders> foundOrders = orderRepo.findByCustomerId(customerId); 
        if (foundOrders.isEmpty()) { 
            throw new EntityNotFoundException("Customer with ID: " + customerId + " not found.");
        }
        return foundOrders;
    }

    /**
     * Deletes an order by its ID.
     * 
     * @param orderId the ID of the order to be deleted.
     * @return true if the order was successfully deleted.
     * @throws EntityNotFoundException if the order with the given ID is not found.
     */
    @Override
    public boolean deleteOrder(long orderId) {
        Optional<Orders> foundOrder = orderRepo.findById(orderId);
        if (foundOrder.isPresent()) {   
            orderRepo.deleteById(orderId);  
            return true;
        }
        throw new EntityNotFoundException(ORDER_NOT_FOUND + orderId  );       
    }       

    public Orders updateOrderStatus(long orderId, String status) {
        Optional<Orders> orderOptional = orderRepo.findById(orderId);
        if (orderOptional.isPresent()) {
            Orders order = orderOptional.get();
            order.setStatus(status);
            return orderRepo.save(order);
        }
        return null;
    }
}