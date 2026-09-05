package com.blogspot.javasolutionsguide.optimistic_and_pessimistic_locking.service;

import com.blogspot.javasolutionsguide.optimistic_and_pessimistic_locking.entity.Order;
import com.blogspot.javasolutionsguide.optimistic_and_pessimistic_locking.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderConcurrencyTests {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @BeforeEach
    void setup() {
        orderRepository.deleteAll();
        Order order = new Order();
        order.setId(1L);
        order.setTotal(100);
        orderRepository.save(order);
    }

    @Test
    void testLostUpdateWithoutLocking() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            try { orderService.incrementTotalNoLock(1L, 10); } catch (InterruptedException e) {}
        });

        executor.submit(() -> {
            try {
                orderService.incrementTotalNoLock(1L, 20);
            } catch (InterruptedException e) {}
        });

        executor.shutdown();
        while (!executor.isTerminated()) Thread.sleep(50);

        int finalTotal = orderRepository.findById(1L).get().getTotal();
        System.out.println("Final total (without locking): " + finalTotal);

        // This is intentionally demonstrating the problem
        assertNotEquals(130, finalTotal, "Lost update occurred!");
    }

    @Test
    void testOptimisticLocking() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            try {
                orderService.incrementTotalOptimistic(1L, 10);
            } catch (Exception e) {
                if (e instanceof ObjectOptimisticLockingFailureException) {
                    System.out.println("Optimistic lock exception caught");
                } else {
                    e.printStackTrace();
                }
            }
        });

        executor.submit(() -> {
            try {
                orderService.incrementTotalOptimistic(1L, 20);
            } catch (Exception e) {
                if (e instanceof ObjectOptimisticLockingFailureException) {
                    System.out.println("Optimistic lock exception caught");
                } else {
                    e.printStackTrace();
                }
            }
        });

        executor.shutdown();
        while (!executor.isTerminated()) Thread.sleep(50);

        int finalTotal = orderRepository.findById(1L).get().getTotal();
        System.out.println("Final total (optimistic locking): " + finalTotal);
        assertTrue(finalTotal == 110 || finalTotal == 120, "Optimistic locking prevented lost update!");
    }

    @Test
    void testPessimisticLocking() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(() -> {
            try { orderService.incrementTotalPessimistic(1L, 10); } catch (InterruptedException e) { e.printStackTrace(); }
        });

        executor.submit(() -> {
            try { orderService.incrementTotalPessimistic(1L, 20); } catch (InterruptedException e) { e.printStackTrace(); }
        });

        executor.shutdown();
        while (!executor.isTerminated()) Thread.sleep(50);

        int finalTotal = orderRepository.findById(1L).get().getTotal();
        System.out.println("Final total (pessimistic locking): " + finalTotal);
        assertEquals(130, finalTotal, "Pessimistic locking prevents lost update!");
    }
}
