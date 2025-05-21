package com.gestor_tiendas.gestor_tiendas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gestor_tiendas.gestor_tiendas.model.Region;
import com.gestor_tiendas.gestor_tiendas.model.Tienda;
import com.gestor_tiendas.gestor_tiendas.service.TiendaService;

@RestController
@RequestMapping("/api/v1/tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    // Tradicionalsssssssss
    @GetMapping
    public ResponseEntity<List<Tienda>> getAllStores() {
        List<Tienda> listaTiendas = this.tiendaService.findAll();
        if (listaTiendas.isEmpty()) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(listaTiendas);

    }

    @GetMapping("/{idTienda}")
    public ResponseEntity<Tienda> getStoreById(@PathVariable Integer idTienda) {
        try {
            Tienda tiendaGet = this.tiendaService.findById(idTienda);
                return ResponseEntity.ok(tiendaGet);

            } catch (Exception e) {
                return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Tienda> saveNewStore(@RequestBody Tienda tienda) {
        Tienda tiendaPost = this.tiendaService.saveTienda(tienda);
        return ResponseEntity.status(HttpStatus.CREATED).body(tiendaPost);

    }

    @PostMapping("/bulk")   // bulk post mapping, se refiere a cargar, guardar volumenes de datos
    public ResponseEntity<List<Tienda>> saveAllStores(@RequestBody List<Tienda> listaTiendas) {
        List<Tienda> tiendasGuardadas = this.tiendaService.saveAllTiendas(listaTiendas);
        return ResponseEntity.ok(tiendasGuardadas);

    }


    @PutMapping("/{idTienda}")
    public ResponseEntity<Tienda> putStore(@PathVariable Integer idTienda, @RequestBody Tienda tienda) {
        try {
            Tienda tiendaPut = this.tiendaService.findById(idTienda);
            tiendaPut.setIdTienda(idTienda);
            tiendaPut.setNombre(tienda.getNombre());
            tiendaPut.setCiudad(tienda.getCiudad());
            tiendaPut.setDireccion(tienda.getDireccion());
            tiendaPut.setTelefono(tienda.getTelefono());
            tiendaPut.setCorreo(tienda.getCorreo());
            tiendaPut.setHoraApertura(tienda.getHoraApertura());
            tiendaPut.setHoraCierre(tienda.getHoraCierre());

            this.tiendaService.saveTienda(tiendaPut);

            return ResponseEntity.ok(tienda);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{idTienda}")
    public ResponseEntity<?> deleteStore(@PathVariable Long idTienda) {
        try {
            this.tiendaService.deleteById(idTienda);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }


    // Adicionales

    @GetMapping("/count")
    public Long getStoreCount() {
        return this.tiendaService.countTiendas();
    }



    @GetMapping("/ciudad")
    public ResponseEntity<List<Tienda>> getStoresByCityIgnoringCase(@RequestParam(name = "ciudad") String ciudadBuscar) {
        // Ejemplo: http://localhost:8080/api/v1/tiendas/ciudad?ciudad=valparaiso
        List<Tienda> tiendasCiudad = this.tiendaService.findByCiudadIgnoringCase(ciudadBuscar);
        if (tiendasCiudad.isEmpty()) {
            // Si no encuentra tiendas para esa ciudad, la lista esta vacia, =>
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(tiendasCiudad);

    }

    @GetMapping("/region/{codigoRegion}")
    public ResponseEntity<List<Tienda>> getStoresByRegion(@PathVariable Integer codigoRegion) {
        Region region = new Region();
        region.setCodigoRegion(codigoRegion);
        List<Tienda> listaTiendasCiu = this.tiendaService.findByRegion(region);
        if (listaTiendasCiu.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaTiendasCiu);

    }



}
