package br.com.fiap.tastytap.insfraestructure.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<OrderEntity> findByNumber(Long number);

    @Query(value = """
        SELECT
            o.*
        FROM orders o
        WHERE o.status != 'FINISHED'
        ORDER BY
        CASE
            WHEN o.status = 'DONE' THEN 1
            WHEN o.status = 'PREPARING' THEN 2
            WHEN o.status = 'RECEIVED' THEN 3
            ELSE 4
        END,
        o.created_at ASC
    """, countQuery = "SELECT COUNT(*) FROM orders o WHERE o.status != 'FINISHED'",
        nativeQuery = true)
    List<OrderEntity> findAllOrdersOrderByStatus();

    Optional<OrderEntity> findByTransactionId(Long transactionId);
}
