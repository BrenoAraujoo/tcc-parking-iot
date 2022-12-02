package com.tccparkingiot.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Table(name = "tb_parking_rental")
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

//    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "timestamp")
    private LocalDateTime startDate;

//    @UpdateTimestamp
    @Column(nullable = true, columnDefinition = "timestamp")
    @Setter(AccessLevel.NONE)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Boolean isRegistered;
    @Transient
    private Integer hour;

    @Transient
    private Double value = 10.00;

}
