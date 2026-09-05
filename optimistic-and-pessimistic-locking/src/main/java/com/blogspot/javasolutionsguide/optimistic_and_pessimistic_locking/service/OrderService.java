package com.blogspot.javasolutionsguide.optimistic_and_pessimistic_locking.service;

import com.blogspot.javasolutionsguide.optimistic_and_pessimistic_locking.entity.Order;
import com.blogspot.javasolutionsguide.optimistic_and_pessimistic_locking.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // No locking: read-modify-write via a raw bulk update, which bypasses the @Version check.
    @Transactional
    public void incrementTotalNoLock(Long orderId, int amount) throws InterruptedException {
        Order o = orderRepository.findById(orderId).orElseThrow();
        int newTotal = o.getTotal() + amount;

        Thread.sleep(100); // widen the race window

        orderRepository.updateTotalUnsafe(orderId, newTotal);
        System.out.println(Thread.currentThread().getName() +
                " - total updated to " + newTotal + " (no lock)");
    }

    // Optimistic locking: relies on the entity's @Version field to reject stale writes.
    @Transactional
    public void incrementTotalOptimistic(Long orderId, int amount) throws InterruptedException {
        Order o = orderRepository.findById(orderId).orElseThrow();
        int newTotal = o.getTotal() + amount;

        Thread.sleep(100); // widen the race window

        o.setTotal(newTotal);
        orderRepository.saveAndFlush(o);
        System.out.println(Thread.currentThread().getName() +
                " - total updated to " + newTotal + " (optimistic)");
    }

    // Pessimistic locking: SELECT ... FOR UPDATE serializes concurrent access to the row.
    @Transactional
    public void incrementTotalPessimistic(Long orderId, int amount) throws InterruptedException {
        Order o = orderRepository.findByIdForUpdate(orderId);
        int newTotal = o.getTotal() + amount;

        Thread.sleep(100); // simulate processing while holding the lock

        o.setTotal(newTotal);
        System.out.println(Thread.currentThread().getName() +
                " - total updated to " + newTotal + " (pessimistic)");
    }
}
