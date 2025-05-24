package com.utcn.scdproiect.courier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Integer> {
    Courier findByemail(String email);
    Courier findByname(String username);
    @Query("""
                SELECT c
                FROM Courier c
                WHERE c.id NOT IN (
                    SELECT p.courier.id
                    FROM Package p
                    WHERE p.status = 1
                                                         )
            """)
    List<Courier> getAllCouriersWithoutPendingPackages();

    @Query("""
        SELECT c, COUNT(p.id)
        FROM Courier c
        LEFT JOIN Package p ON p.courier.id = c.id AND p.status = 2
        WHERE c.manager_id IS NULL
        GROUP BY c.id
       """)
    List<Object[]> getManagersAndDeliveredCount();


}
