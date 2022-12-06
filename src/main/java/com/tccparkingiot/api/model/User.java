package com.tccparkingiot.api.model;

import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;

////    @JsonIgnore
//    @OneToMany(mappedBy = "user")
//    private List<Vehicle> vehicles = new ArrayList<>();

    @OneToOne(optional = true)
    private Plate plate;
}
