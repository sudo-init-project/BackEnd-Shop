package ar.com.ayi.ejercicio2final.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CLIENTES")
public class Cliente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido del cliente es obligatorio")
    private String apellido;

    @NotBlank(message = "La fecha de ingreso del cliente es obligatoria")
    @Column(name = "fecha_ingreso")
    private String fechaIngreso;

    @NotBlank(message = "El domicilio del cliente es obligatorio")
    private String domicilio;

    @NotBlank(message = "El telefono de producto es obligatorio")
    private String telefono;

}
