package ar.com.ayi.ejercicio2final.service;

import java.util.List;

public interface ICrud<T> {

    T agregar(T t);
    T modificar(T t);
    T buscar(Integer id);
    void eliminar(Integer id);
    List<T> listar();
}
