package ar.com.ayi.ejercicio2final.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    private Integer id;
    private String nombre;
    private String apellido;
    private String fechaIngreso;
    private String domicilio;
    private String telefono;
}
