package com.gestor_tiendas.gestor_tiendas.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestor_tiendas.gestor_tiendas.assemblers.TiendaModelAssembler;
import com.gestor_tiendas.gestor_tiendas.model.Tienda;
import com.gestor_tiendas.gestor_tiendas.service.TiendaService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


//v2
@RestController
@RequestMapping("/api/v2/tienda")
public class TiendaControllerV2 {

    @Autowired
    private TiendaService tiendaService;

    @Autowired
    private TiendaModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Tienda>> getAllTiendas() {
        List<EntityModel<Tienda>> listaTiendas = this.tiendaService.findAll().stream()
            .map(assembler::toModel).collect(Collectors.toList());  // Aclarar que hace esta cosa
        // metodo que agrega un enlace a la representacion del recurso con la relacion "self", que es el enlace al propio recurso
        return CollectionModel.of(listaTiendas, linkTo(methodOn(TiendaControllerV2.class).getAllTiendas()).withSelfRel());
    }

    @GetMapping(value = "/{idTienda}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Tienda> getTiendaById(@PathVariable Integer idTienda) {
        Tienda tienda = this.tiendaService.findById(idTienda);
        return this.assembler.toModel(tienda);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Tienda>> saveTienda(@RequestBody Tienda tienda) {
        Tienda nuevaTienda = this.tiendaService.saveTienda(tienda);
        return ResponseEntity.created(linkTo(methodOn(TiendaControllerV2.class).getTiendaById(nuevaTienda.getIdTienda())).toUri())
                             .body(this.assembler.toModel(nuevaTienda));
    }

    @PutMapping(value = "/{idTienda}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Tienda>> putTienda(@PathVariable Integer idTienda, @RequestBody Tienda tienda) {
        tienda.setIdTienda(idTienda);
        Tienda putTienda = this.tiendaService.saveTienda(tienda);
        return ResponseEntity.ok(this.assembler.toModel(putTienda));
    }

    @DeleteMapping(value = "/{idTienda}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteTienda(@PathVariable Integer idTienda) {
        this.tiendaService.deleteById(idTienda);
        return ResponseEntity.noContent().build();

    }


}
