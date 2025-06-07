package ar.com.ayi.ejercicio2final.persistence.repository;

import ar.com.ayi.ejercicio2final.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {
}
