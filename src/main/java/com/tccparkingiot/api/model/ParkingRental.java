package com.tccparkingiot.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
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
    @Column(nullable = false, columnDefinition = "datetime")
    private LocalDateTime startDate;

//    @UpdateTimestamp
    @Column(nullable = true, columnDefinition = "datetime")
    @Setter(AccessLevel.NONE)
    private LocalDateTime endDate;

    @Transient
    private Integer hour;

    @Transient
    private Double value = 10.00;

}
