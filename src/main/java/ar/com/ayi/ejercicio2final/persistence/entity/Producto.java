package ar.com.ayi.ejercicio2final.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
@Table(name = "PRODUCTOS")
public class Producto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    @Column(name = "codigo_ean")
    @NotBlank(message = "El código ean es obligatorio")
    private String codigoEan;

    @Column(name = "nombre_producto")
    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción del producto es obligatoria")
    @Column(name = "descripcion_producto")
    private String descripcion;

    @NotBlank(message = "El tipo de producto es obligatorio")
    private String tipo;

    @NotBlank(message = "El tipo de producto es obligatorio")
    private String marca;

    @NotNull(message = "El precio del producto es obligatorio")
    private Double precio;

    @NotNull(message = "El stock del producto es obligatorio")
    @PositiveOrZero(message = "El stock no puede ser negativo")
    private Integer stock;

}
