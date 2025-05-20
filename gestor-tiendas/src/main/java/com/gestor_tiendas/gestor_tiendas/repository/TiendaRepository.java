package com.gestor_tiendas.gestor_tiendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

    /** Metodo para buscar por ciudad
     * 
     * @param city String: ciudad a buscar
     * @return List: con las tiendas de la ciudad
     */
    List<Tienda> findByCiudad(String ciudad);






}
