package ar.edu.grupoesfera.cursospring.dao;

import ar.edu.grupoesfera.cursospring.modelo.CompraCola;

import java.util.List;

public interface CompraColaDao {
    List<CompraCola> obtenerPedidosEnCola();
    void agregarCompraCola(CompraCola compra);

    void eliminarCompraCola(CompraCola compra);
     CompraCola obtenerUltimaCompraCola();
}
