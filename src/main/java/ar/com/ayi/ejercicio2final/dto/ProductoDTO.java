package ar.com.ayi.ejercicio2final.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {

    private Integer id;
    private String codigoEan;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String marca;
    private Double precio;
    private Integer stock;
}
