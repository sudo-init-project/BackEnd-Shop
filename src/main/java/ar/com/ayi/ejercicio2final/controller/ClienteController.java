package ar.com.ayi.ejercicio2final.controller;

import ar.com.ayi.ejercicio2final.dto.ClienteDTO;
import ar.com.ayi.ejercicio2final.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Integer id){

        ClienteDTO clienteDTO = clienteService.buscar(id);

        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar(){

        List<ClienteDTO> listaClientes = clienteService.listar();
        return ResponseEntity.ok().body(listaClientes);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> agregar(@RequestBody ClienteDTO clienteDTO){
        ClienteDTO clienteAgregado = clienteService.agregar(clienteDTO);

        return ResponseEntity.ok(clienteAgregado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
        clienteService.eliminar(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<ClienteDTO> modificar(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteModificado = clienteService.modificar(clienteDTO);
        return ResponseEntity.ok(clienteModificado);
    }
}
