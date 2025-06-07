package ar.com.ayi.ejercicio2final.controller;

import ar.com.ayi.ejercicio2final.dto.ProductoDTO;
import ar.com.ayi.ejercicio2final.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> buscar(@PathVariable Integer id){

        ProductoDTO productoDTO = productoService.buscar(id);

        return ResponseEntity.ok().body(productoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar(){

        List<ProductoDTO> listaProductos = productoService.listar();
        return ResponseEntity.ok().body(listaProductos);
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> agregar(@RequestBody ProductoDTO productoDTO){
        ProductoDTO productoAgregado = productoService.agregar(productoDTO);

        return ResponseEntity.ok(productoAgregado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        productoService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ProductoDTO> modificar(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoModificado = productoService.modificar(productoDTO);
        return ResponseEntity.ok(productoModificado);
    }
}
