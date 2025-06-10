package com.gestor_tiendas.gestor_tiendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestor_tiendas.gestor_tiendas.model.Region;
import com.gestor_tiendas.gestor_tiendas.model.Tienda;
import com.gestor_tiendas.gestor_tiendas.repository.TiendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    public Tienda findById(Integer idTiendaBuscar) {
        return this.tiendaRepository.findById(idTiendaBuscar).get();

    }

    public List<Tienda> findAll() {
        return this.tiendaRepository.findAll();

    }

    public Tienda saveTienda(Tienda tienda) {   // Tambien para ACTUALIZAR, PUT
        return this.tiendaRepository.save(tienda);
    }

    public List<Tienda> saveAllTiendas(List<Tienda> listaTiendas) {
        return this.tiendaRepository.saveAll(listaTiendas);
        
    }

    public void deleteById(Integer idTiendaBorrar) {
        this.tiendaRepository.deleteById(idTiendaBorrar);
        
    }


    // Metodos utiles(?)

    public boolean existsById(Integer idTiendaValidar) {
        // existsById (metodo boolean) Retorna true si existe
        return this.tiendaRepository.existsById(idTiendaValidar);

    }

    public Long countTiendas() {
        return this.tiendaRepository.count();

    }


    public List<Tienda> findByCiudadIgnoringCase(String ciudad) {
        return this.tiendaRepository.findByCiudadContainingIgnoreCase(ciudad);
    }


    public List<Tienda> findByRegion(Region regionBuscar) {
        return this.tiendaRepository.findByRegion(regionBuscar);
        
    }

}
