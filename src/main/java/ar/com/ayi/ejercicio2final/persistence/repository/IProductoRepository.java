package ar.com.ayi.ejercicio2final.persistence.repository;

import ar.com.ayi.ejercicio2final.persistence.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    boolean existsByCodigoEan(String codigoEan);
}
