package com.gestor_tiendas.gestor_tiendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestor_tiendas.gestor_tiendas.model.Region;
import com.gestor_tiendas.gestor_tiendas.model.Tienda;

@Repository
public interface TiendaRepository extends JpaRepository<Tienda, Long>{

    /*  CRUD (Create, Read, Update, Delete):
    o save(S entity): Guarda una entidad.
    o findById(ID id): Encuentra una entidad por su ID.
    o existsById(ID id): Verifica si una entidad con un ID dado existe.
    o findAll(): Encuentra todas las entidades.
    o findAllById(Iterable<ID> ids): Encuentra todas las entidades por sus IDs.
    o count(): Cuenta todas las entidades.
    o deleteById(ID id): Borra una entidad por su ID.
    o delete(S entity): Borra una entidad.
    o deleteAll(): Borra todas las entidades.
    Paginación y Ordenación:
    • findAll(Pageable pageable): Encuentra todas las entidades con paginación.
    • findAll(Sort sort): Encuentra todas las entidades con ordenación.
    */

    /*  Sobre Spring Data Jpa
     *  Gracias a las caracteristicas de Spring Data JPA, Containing y IgnoreCase generan una consulta SQL
     *  basada en el nombre del metodo. Usa descriptores de consulta, entonces Spring Data JPA, sabra como
     *  generar la consulta SQL.
     */

    /** 
     *  Metodo para buscar tiendas por ciudad.
     *  Este metodo ignora mayusculas y minusculas dentro del parametro, y
     *  realiza una busqueda de una cadena dentro de otra.
     * 
     * @param ciudad cadena String de Ciudad en la que buscar tiendas
     * 
     */
    List<Tienda> findByCiudadContainingIgnoreCase(String ciudad);

    /**
     * Metodo para buscar tiendas por region.
     * 
     * @param region region a buscar
     */
    List<Tienda> findByRegion(Region region);




}
