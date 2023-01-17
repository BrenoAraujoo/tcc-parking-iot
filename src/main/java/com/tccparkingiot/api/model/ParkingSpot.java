package com.tccparkingiot.api.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;

@Table(name = "tb_parking_spot")
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @Size(min = 2,max=2, message = "O número de caracteres deve ser 2")
    private String name;
    private Boolean available;
    @OneToOne(optional = true)
    private Plate plate;
}
