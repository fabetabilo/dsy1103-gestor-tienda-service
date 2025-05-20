package com.gestor_tiendas.gestor_tiendas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestor_tiendas.gestor_tiendas.model.Tienda;
import com.gestor_tiendas.gestor_tiendas.repository.TiendaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TiendaService {

    @Autowired
    private TiendaRepository tiendaRepository;

    public Tienda findById(long idTiendaBuscar) {
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

    public void deleteById(Long idTiendaBorrar) {
        this.tiendaRepository.deleteById(idTiendaBorrar);
        
    }


    // Metodos utiles(?)

    public boolean existsById(Long idTiendaValidar) {
        // existsById (metodo boolean) Retorna true si existe
        return this.tiendaRepository.existsById(idTiendaValidar);

    }

    public Long countTiendas() {
        return this.tiendaRepository.count();

    }

    public List<Tienda> findByCiudad(String ciudad) {
        return this.tiendaRepository.findByCiudad(ciudad);
    }




}
