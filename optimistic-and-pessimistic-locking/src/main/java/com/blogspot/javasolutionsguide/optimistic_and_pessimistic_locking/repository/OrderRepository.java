package com.blogspot.javasolutionsguide.optimistic_and_pessimistic_locking.repository;

import com.blogspot.javasolutionsguide.optimistic_and_pessimistic_locking.entity.Order;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT o FROM Order o WHERE o.id = :id")
    Order findByIdForUpdate(@Param("id") Long id);

    // Bulk update bypasses Hibernate's @Version check, simulating an unprotected read-modify-write.
    @Modifying
    @Query("UPDATE Order o SET o.total = :total WHERE o.id = :id")
    void updateTotalUnsafe(@Param("id") Long id, @Param("total") int total);
}
