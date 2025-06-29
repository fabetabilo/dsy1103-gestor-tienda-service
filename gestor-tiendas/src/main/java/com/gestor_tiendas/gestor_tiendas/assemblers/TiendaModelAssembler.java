package com.gestor_tiendas.gestor_tiendas.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.gestor_tiendas.gestor_tiendas.controller.TiendaControllerV2;
import com.gestor_tiendas.gestor_tiendas.model.Tienda;

@Component
public class TiendaModelAssembler implements RepresentationModelAssembler<Tienda, EntityModel<Tienda>> {

    @Override
    public EntityModel<Tienda> toModel(Tienda tienda) {
        return EntityModel.of(tienda,
            linkTo(methodOn(TiendaControllerV2.class).getTiendaById(tienda.getIdTienda())).withSelfRel(),
            linkTo(methodOn(TiendaControllerV2.class).getAllTiendas()).withRel("tiendas"));

    }

}
