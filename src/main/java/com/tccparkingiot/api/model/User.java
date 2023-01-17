package com.tccparkingiot.api.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;


@Table(name = "tb_user")
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 20,
            message = "Nome deve conter no mínimo 1 e no máximo 20 caracteres")
    @NotBlank
    private String firstName;

    @Size(min = 1, max = 20,
            message = "Sobrenome deve conter no mínimo 1 e no máximo 20 caracteres")
    private String lastName;
    @Email(message = "Email com formato inválido")
    private String email;
    @CPF(message = "CPF com formato inválido")
    private String cpf;

    @OneToOne(optional = true)
    private Plate plate;
}
