package com.tccparkingiot.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;

@Table(name = "tb_plate")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NonNull
    @Size(min = 7, max=7, message = "O número de caracateres da placa deve ser 7")
    private String plateNumber;

    @OneToOne(mappedBy = "plate")
    @JsonIgnore
    private User user;
}
