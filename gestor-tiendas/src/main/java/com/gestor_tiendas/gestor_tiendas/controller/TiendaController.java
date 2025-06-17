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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/v1/tienda")
@Tag(name = "Tiendas", description = "Operaciones con tiendas")
public class TiendaController {

    @Autowired
    private TiendaService tiendaService;

    /* ResponseEntity
     * Clase parte de libreria Spring Web, utilizada en personalizacion de respuestas
     * de clientes navegadores o aplicaciones que consumen servicios REST.
     * 
     * Esta clase incluye el cuerpo de una respuesta, el codigo de estado HTTP (200, 404, etc)
     * y encabezados HTTP (headers) que acompañan la respuesta
     */


    /** GET /api/v1/tienda
     * Metodo que retorna todas las tiendas existentes
     * 
     * @return ResponseEntity Lista sin contenido (HTTP 204 No Content), o ResponseEntity de Lista de tiendas existentes (HTTP 200 OK)
     */
    @GetMapping
    @Operation(summary = "Obtener todas las tiendas", description = "Obtiene lista con todas las tiendas existentes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tiendas existentes encontradas con exito",
            content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "204", description = "Tiendas no encontradas")
    }) 
    public ResponseEntity<List<Tienda>> getAllStores() {
        List<Tienda> listaTiendas = this.tiendaService.findAll();
        if (listaTiendas.isEmpty()) {
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(listaTiendas);

    }

    /** GET /api/v1/tienda/{idTienda}
     * Metodo que retorna una tienda por su id
     * 
     * @param idTienda el id de la tienda que se busca
     * @return ResponseEntity tienda buscada (HTTP 200 OK), o ResponseEntity la tienda no existe (HTTP 404 Not Found)
     */
    @GetMapping("/{idTienda}")
    @Operation(summary = "Obtener tienda por id", description = "Obtiene una tienda por codigo identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tienda encontrada con exito",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "404", description = "Tienda no encontrada")
    })
    public ResponseEntity<Tienda> getStoreById(@PathVariable Integer idTienda) {
        try {
            Tienda tiendaGet = this.tiendaService.findById(idTienda);
                return ResponseEntity.ok(tiendaGet);

            } catch (Exception e) {
                return ResponseEntity.notFound().build();
        }

    }

    /** POST /api/v1/tienda
     * Metodo que crea una nueva tienda
     * 
     * @RequestBody JSON cuerpo del objeto tienda, sin id
     * @param tienda tienda a crear
     * @return ResponseEntity tienda creada (HTTP 201 Created)
     */
    @PostMapping
    @Operation(summary = "Crear una nueva tienda", description = "Crea una nuevo recurso tienda")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tienda creada con exito",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "400", description = "No se pudo crear la tienda, los datos proporcionados son incorrectos")
    })
    public ResponseEntity<Tienda> saveNewStore(@RequestBody Tienda tienda) {
        try {
            Tienda tiendaPost = this.tiendaService.saveTienda(tienda);
            return ResponseEntity.status(HttpStatus.CREATED).body(tiendaPost);

        } catch (Exception e) {
            // Maneja una excepcion, en caso de que el cuerpo Body ingresado sea invalido
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }


    /** POST /api/v1/tienda/bulk
     * Metodo bulk que crea muchas tiendas a la vez
     * bulk postmapping se refiere a cargar, guardar volumenes de datos.
     * 
     * @RequestBody JSON cuerpo de tiendas a agregar
     * @param listaTiendas arreglo de tiendas a guardar
     * @return ResponseEntity tiendas creadas (HTTP 201 OK)
     */
    @PostMapping("/bulk")
    @Operation(summary = "Crear muchas tiendas", description = "Crea muchas tiendas a la vez")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Tiendas creadas con exito"),
        @ApiResponse(responseCode = "400", description = "No se pudo crear las tiendas, los datos proporcionados son incorrectos")
    })
    public ResponseEntity<List<Tienda>> saveAllStores(@RequestBody List<Tienda> listaTiendas) {
        try {
            List<Tienda> tiendasGuardadas = this.tiendaService.saveAllTiendas(listaTiendas);
            return ResponseEntity.status(HttpStatus.CREATED).body(tiendasGuardadas);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }
    }

    /** PUT /api/v1/tienda/{idTienda}
     * Metodo para modificar los detalles de una tienda
     * 
     * @param idTienda id de la tienda que se quiere modificar
     * @param tienda JSON cuerpo de los nuevos valores para la tienda. Objeto de clase Tienda.
     * @return ResponseEntity tienda modificada con exito (HTTP 200 OK), o ResponseEntity contenido no encontrado (HTTP 404 Not Found)
     */
    @PutMapping("/{idTienda}")
    @Operation(summary = "Actualizar datos de una tienda", description = "Actualiza una tienda existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tienda actualizada exitosamente",
            content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Tienda.class))),
        @ApiResponse(responseCode = "404", description = "Tienda no encontrada"),
        @ApiResponse(responseCode = "400", description = "No se puedo actualizar tienda, los datos proporcionados son erroneos")
    })
    public ResponseEntity<Tienda> putStore(@PathVariable Integer idTienda, @RequestBody Tienda tienda ) {
        try {
            // Verifica primero, que la tienda a actualizar exista
            if (this.tiendaService.existsById(idTienda)) {
                Tienda tiendaPut = this.tiendaService.findById(idTienda);
                tiendaPut.setIdTienda(idTienda);
                tiendaPut.setNombre(tienda.getNombre());
                tiendaPut.setCiudad(tienda.getCiudad());
                tiendaPut.setDireccion(tienda.getDireccion());
                tiendaPut.setTelefono(tienda.getTelefono());
                tiendaPut.setCorreo(tienda.getCorreo());
                tiendaPut.setHoraApertura(tienda.getHoraApertura());
                tiendaPut.setHoraCierre(tienda.getHoraCierre());
                // Se debe guardar esa tiendaPut ("tienda actualizada")
                this.tiendaService.saveTienda(tiendaPut);
                return ResponseEntity.ok(tienda);

            } else {
                // si no existe, da codigo 404 NOT FOUND
                return ResponseEntity.notFound().build();
            }

        } catch (Exception e) {
            // en caso que el cuerpo de la solicitud sea erroneo
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);

        }

    }

    /** DELETE /api/v1/tienda/{idTienda}
     * Metodo para borrar una tienda
     * 
     * @param idTienda id de la tienda a borrar
     * @return ResponseEntity sin contenido (HTTP 204 No Content) tienda borrada con exito, o ResponseEntity contenido no encontrado (HTTP 404 Not Found) tienda no existe
     */
    @DeleteMapping("/{idTienda}")
    @Operation(summary = "Eliminar una tienda", description = "Elimina una tienda por su codigo identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tienda eliminada con exito"),
        @ApiResponse(responseCode = "404", description = "Tienda no encontrada")
    })
    public ResponseEntity<?> deleteStore(@PathVariable Integer idTienda) {
        try {
            this.tiendaService.deleteById(idTienda);
            return ResponseEntity.noContent().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }


    // Adicionales

    /** GET /api/v1/tienda/cantidad
     * Metodo que cuenta el total de tiendas existentes
     * 
     * @return Long del numero de tiendas
     */
    @GetMapping("/cantidad")
    public Long getStoresCount() {
        return this.tiendaService.countTiendas();
    }


    /** GET /api/v1/tienda/ciudad
     * Metodo que busca tiendas por ciudad, ignorando mayusculas, minusculas y errores parciales de busqueda
     * (ej: http://busqueda/api/v1/tiendas/ciudad?ciudad=santiago)
     * 
     * @param ciudad = texto o texto parcial de ciudad a buscar (ej: Santiago, santiago, santi)
     * @return ResponseEntity Lista de tiendas existentes en ciudad (HTTP 200 OK), o ResponseEntity Lista sin contenido (HTTP 204 No Content)
     */
    @GetMapping("/ciudad")
    @Operation(summary = "Obtener tiendas por ciudad", description = "Obtiene lista de tiendas por texto completo o parcial de una ciudad")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tiendas encontradas con exito"),
        @ApiResponse(responseCode = "204", description = "Tiendas no encontradas, no existen tiendas para la ciudad buscada")
    })
    public ResponseEntity<List<Tienda>> getStoresByCity(@RequestParam(name = "ciudad") String ciudad) {
        List<Tienda> tiendasCiudad = this.tiendaService.findByCiudadIgnoringCase(ciudad);
        if (tiendasCiudad.isEmpty()) {
            // Si no encuentra tiendas para esa ciudad, la lista esta vacia, =>
            return ResponseEntity.noContent().build();

        }
        return ResponseEntity.ok(tiendasCiudad);

    }

    /** GET /api/v1/tienda/region/{codigoRegion}
     * Metodo que busca tiendas por region a la que pertenecen
     * 
     * @param codigoRegion codigo de la region a buscar (Codigos unicos territoriales, Chile)
     * @return ResponseEntity de Lista de tiendas que pertenecen a la region (HTTP 200 OK), o ResponseEntity sin contenido (HTTP 204 No Content)
     */
    @GetMapping("/region/{codigoRegion}")
    @Operation(summary = "Obtener tiendas por region", description = "Obtiene lista de tiendas de una region en especifico, por codigo de region")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tiendas encontradas con exito"),
        @ApiResponse(responseCode = "204", description = "Tiendas no encontradas, no existen tiendas para la region buscada")
    })
    public ResponseEntity<List<Tienda>> getStoresByRegion(@PathVariable Integer codigoRegion) {
        Region region = new Region();
        region.setCodigoRegion(codigoRegion);
        List<Tienda> listaTiendasCiu = this.tiendaService.findByRegion(region);
        if (listaTiendasCiu.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaTiendasCiu);

    }


    /** PUT /api/v1/tienda/{idTienda}/horario
     * Metodo para modificar exclusivamente el horario de apertura y cierre de una tienda
     * 
     * @param idTienda id de la tienda que se quiere modificar horas
     * @param tienda JSON cuerpo de las nuevas horas
     * @return ResponseEntity tienda con horarios actualizados (HTTP 200 OK), ResponseEntity tienda no encontrada (HTTP 404 Not Found), o ResponseEntity solicitud erronea (HTTP 400 Bad Request)
     */
    @PutMapping("/{idTienda}/horario")
    @Operation(summary = "Actualizar horario de apertura o cierre de tienda", description = "Modifica exclusivamente los horarios de una tienda existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horario de tienda actualizado con exito"),
        @ApiResponse(responseCode = "404", description = "Tienda no encontrada"),
        @ApiResponse(responseCode = "400", description = "No se pudo actualizar horario, los datos proporcionados son erroneos")
    })
    public ResponseEntity<Tienda> putStoreHorario(@PathVariable Integer idTienda, @RequestBody Tienda tienda) {
        try {
            if (this.tiendaService.existsById(idTienda)) {
                Tienda tiendaPut = this.tiendaService.findById(idTienda);
                tiendaPut.setHoraApertura(tienda.getHoraApertura());
                tiendaPut.setHoraCierre(tienda.getHoraCierre());

                this.tiendaService.saveTienda(tiendaPut);
                return ResponseEntity.ok(tiendaPut);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }



}
