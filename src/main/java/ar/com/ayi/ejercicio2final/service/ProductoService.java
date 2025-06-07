package ar.com.ayi.ejercicio2final.service;

import ar.com.ayi.ejercicio2final.dto.ProductoDTO;
import ar.com.ayi.ejercicio2final.exceptions.BadRequestException;
import ar.com.ayi.ejercicio2final.exceptions.NotFoundElementException;
import ar.com.ayi.ejercicio2final.persistence.entity.Producto;
import ar.com.ayi.ejercicio2final.persistence.repository.IProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements ICrud<ProductoDTO>{
    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    ObjectMapper mapper;

    private static final Logger logger = LogManager.getLogger(ProductoService.class);

    @Transactional
    @Override
    public ProductoDTO agregar(ProductoDTO productoDTO) {


        if (productoDTO.getCodigoEan() == null || productoDTO.getNombre() == null) {

            throw new BadRequestException("001", "Faltan campos obligatorios en el RequestBody del producto o algún campo tiene valor null");

        }

        if(productoRepository.existsByCodigoEan(productoDTO.getCodigoEan())){
            throw new BadRequestException("001", "Código Ean ya utilizado en otro producto.");
        }

        Producto productoAAgregar = mapper.convertValue(productoDTO, Producto.class);
        Producto productoAgregado = productoRepository.save(productoAAgregar);
        logger.info("Producto agregado correctamente.");

        return mapper.convertValue(productoAgregado, ProductoDTO.class);
    }

    @Transactional
    @Override
    public ProductoDTO modificar(ProductoDTO productoDTO) {
        Integer idProducto = productoDTO.getId();
        if (idProducto == null || !productoRepository.existsById(productoDTO.getId())) {
            throw new NotFoundElementException("002", "Producto no encontrado con id: " + idProducto);
        }

        if(productoRepository.existsByCodigoEan(productoDTO.getCodigoEan())){
            throw new BadRequestException("001", "Código Ean ya utilizado en otro producto.");
        }

        Producto productoModificado =  mapper.convertValue(productoDTO, Producto.class);

        productoRepository.save(productoModificado);
        logger.info("Producto modificado correctamente");
        return productoDTO;
    }

    @Transactional(readOnly = true)
    @Override
    public ProductoDTO buscar(Integer id) {

        Optional<Producto> producto = productoRepository.findById(id);

        if(producto.isPresent()){
            return mapper.convertValue(producto.get(), ProductoDTO.class);
        }else{
            throw new NotFoundElementException("002", "El producto con id "+ id + " no existe");
        }
    }

    @Transactional
    @Override
    public void eliminar(Integer id) {

        Optional<Producto> productoAEliminar = productoRepository.findById(id);

        if(productoAEliminar.isPresent()){
            productoRepository.deleteById(id);
            logger.info("Producto con id "+ id +" eliminado correctamente");
        }else{
            throw new NotFoundElementException("002", "El producto que intentas eliminar no existe.");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProductoDTO> listar() {
        return productoRepository.findAll()
                .stream()
                .map(producto -> mapper.convertValue(producto,ProductoDTO.class))
                .toList();
    }
}
