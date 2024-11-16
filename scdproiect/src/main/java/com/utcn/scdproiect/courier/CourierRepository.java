package com.utcn.scdproiect.courier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourierRepository extends JpaRepository<Courier, Integer> {
    @Query("""
                SELECT c
                FROM Courier c
                WHERE c.id NOT IN (
                    SELECT p.courier.id
                    FROM Package p
                    WHERE p.status = com.utcn.scdproiect.pkg.PackageStatus.PENDING
                                                         )
            """)
    List<Courier> getAllCouriersWithoutPendingPackages();

}
