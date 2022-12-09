package com.tccparkingiot.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_parking_rental")
@Entity
public class ParkingRental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "plate_id", nullable = true)
    private Plate plate;

    @ManyToOne
    @JoinColumn(name = "parking_spot_id")
    @JsonIgnore
    private ParkingSpot parkingSpot;

    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime startDate;

    @Column(nullable = true, columnDefinition = "timestamp")
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Boolean isRegistered;

}
