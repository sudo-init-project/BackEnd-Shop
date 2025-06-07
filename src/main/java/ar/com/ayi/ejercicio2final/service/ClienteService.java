package ar.com.ayi.ejercicio2final.service;

import ar.com.ayi.ejercicio2final.dto.ClienteDTO;
import ar.com.ayi.ejercicio2final.exceptions.BadRequestException;
import ar.com.ayi.ejercicio2final.exceptions.NotFoundElementException;
import ar.com.ayi.ejercicio2final.persistence.entity.Cliente;
import ar.com.ayi.ejercicio2final.persistence.repository.IClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ICrud<ClienteDTO>{

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger logger = LogManager.getLogger(ClienteService.class);

    @Transactional
    @Override
    public ClienteDTO agregar(ClienteDTO clienteDTO) {


        if (clienteDTO.getApellido() == null || clienteDTO.getNombre() == null) {

            throw new BadRequestException("001", "Faltan campos obligatorios en el RequestBody del cliente o algún campo tiene valor null");

        }

        Cliente clienteAAgregar = mapper.convertValue(clienteDTO, Cliente.class);
        Cliente clienteAgregado = clienteRepository.save(clienteAAgregar);
        logger.info("Cliente agregado correctamente.");

        return mapper.convertValue(clienteAgregado, ClienteDTO.class);
    }

    @Transactional
    @Override
    public ClienteDTO modificar(ClienteDTO clienteDTO) {
        Integer idCliente = clienteDTO.getId();
        if (idCliente == null || !clienteRepository.existsById(clienteDTO.getId())) {
            throw new NotFoundElementException("002", "Cliente no encontrado con id: " + idCliente);
        }

        Cliente clienteModificado =  mapper.convertValue(clienteDTO, Cliente.class);

        clienteRepository.save(clienteModificado);
        logger.info("Cliente modificado correctamente");
        return clienteDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public ClienteDTO buscar(Integer id) {

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isPresent()){
            return mapper.convertValue(cliente.get(), ClienteDTO.class);
        }else{
            throw new NotFoundElementException("002", "El cliente con id "+ id + " no existe");
        }
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {

        Optional<Cliente> clienteAEliminar = clienteRepository.findById(id);

        if(clienteAEliminar.isPresent()){
            clienteRepository.deleteById(id);
            logger.info("Cliente con id "+ id +" eliminado correctamente");
        }else{
            throw new NotFoundElementException("002", "El cliente que intentas eliminar no existe.");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClienteDTO> listar() {
        return clienteRepository.findAll()
                .stream()
                .map(cliente -> mapper.convertValue(cliente,ClienteDTO.class))
                .toList();
    }
}
