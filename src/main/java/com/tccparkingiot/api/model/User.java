package com.tccparkingiot.api.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tb_user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Email(message = "Email com formato inválido")
    private String email;
    @CPF(message = "CPF com formato inválido")
    private String cpf;

    @OneToOne(optional = true)
    private Plate plate;
}
