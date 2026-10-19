package co.edu.unilibre.gestion;

import java.util.List;

public interface GenericDAO <T>{
    Object crear(T t);
    int obtenerPorCedula(String cedula);
    void editar(T t);
    void borrar(Object id);
    List<T> consultarTodos();
}
