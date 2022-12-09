package com.tccparkingiot.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.Size;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "tb_plate")
@Entity
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
