package com.utcn.scdproiect.courier;
import com.utcn.scdproiect.pkg.PackageStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Courier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "courier_id", nullable = true)
    private Courier courier;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdOn;
    private String deliveryAddress;
    boolean payOnDelivery;

    private PackageStatus status;

}
