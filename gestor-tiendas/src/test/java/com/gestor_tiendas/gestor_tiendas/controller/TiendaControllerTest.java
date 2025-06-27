package com.gestor_tiendas.gestor_tiendas.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestor_tiendas.gestor_tiendas.model.Region;
import com.gestor_tiendas.gestor_tiendas.model.Tienda;
import com.gestor_tiendas.gestor_tiendas.service.TiendaService;

@WebMvcTest(TiendaController.class)
public class TiendaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TiendaService tiendaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Tienda tienda;

    @BeforeEach
    void setUp() {

        Region reg = new Region();
        reg.setCodigoRegion(14);
        reg.setNombreRegion("Region de los Rios");

        tienda = new Tienda();

        tienda.setIdTienda(1);
        tienda.setNombre("EcoMarket Valdivia");
        tienda.setCiudad("Valdivia");
        tienda.setDireccion("Avenida Armada 876");
        tienda.setTelefono(78563396);
        tienda.setCorreo("ecomarketvaldiv@contacto.cl");
        tienda.setHoraApertura(LocalTime.of(8, 0));
        tienda.setHoraCierre(LocalTime.of(19, 30));
        tienda.setRegion(reg);

    }

    @Test
    public void testGetAllStores() throws Exception {
        // metodo "Encontrar todas las tiendas"
        when(tiendaService.findAll()).thenReturn(List.of(tienda));

        mockMvc.perform(get("/api/v1/tienda"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].idTienda").value(1))
        .andExpect(jsonPath("$[0].nombre").value("EcoMarket Valdivia"))
        .andExpect(jsonPath("$[0].ciudad").value("Valdivia"))
        .andExpect(jsonPath("$[0].direccion").value("Avenida Armada 876"))
        .andExpect(jsonPath("$[0].telefono").value(78563396))
        .andExpect(jsonPath("$[0].correo").value("ecomarketvaldiv@contacto.cl"))
        .andExpect(jsonPath("$[0].horaApertura").value("08:00:00"))
        .andExpect(jsonPath("$[0].horaCierre").value("19:30:00"))
        .andExpect(jsonPath("$[0].region.codigoRegion").value(14))
        .andExpect(jsonPath("$[0].region.nombreRegion").value("Region de los Rios"));

    }

    @Test
    public void testGetStoreById() throws Exception {

        when(tiendaService.findById(1)).thenReturn(tienda);

        mockMvc.perform(get("/api/v1/tienda/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.idTienda").value(1))
            .andExpect(jsonPath("$.nombre").value("EcoMarket Valdivia"))
            .andExpect(jsonPath("$.ciudad").value("Valdivia"))
            .andExpect(jsonPath("$.direccion").value("Avenida Armada 876"))
            .andExpect(jsonPath("$.telefono").value(78563396))
            .andExpect(jsonPath("$.correo").value("ecomarketvaldiv@contacto.cl"))
            .andExpect(jsonPath("$.region.codigoRegion").value(14))
            .andExpect(jsonPath("$.region.nombreRegion").value("Region de los Rios"));

    }
    
    @Test
    public void testCreateTienda() throws Exception {

        when(tiendaService.saveTienda(any(Tienda.class))).thenReturn(tienda);

        mockMvc.perform(post("/api/v1/tienda").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(tienda)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.idTienda").value(1))
            .andExpect(jsonPath("$.nombre").value("EcoMarket Valdivia"))
            .andExpect(jsonPath("$.ciudad").value("Valdivia"))
            .andExpect(jsonPath("$.direccion").value("Avenida Armada 876"))
            .andExpect(jsonPath("$.telefono").value(78563396))
            .andExpect(jsonPath("$.correo").value("ecomarketvaldiv@contacto.cl"))
            .andExpect(jsonPath("$.horaApertura").value("08:00:00"))
            .andExpect(jsonPath("$.horaCierre").value("19:30:00"))
            .andExpect(jsonPath("$.region.codigoRegion").value(14))
            .andExpect(jsonPath("$.region.nombreRegion").value("Region de los Rios"));
    }

    @Test
    public void testPutTienda() throws Exception {
        // Se asegura de la simulacion de la actualizacion en el service, devolviendo una tienda con los datos actualizados
        when(this.tiendaService.existsById(1)).thenReturn(true);
        when(this.tiendaService.findById(1)).thenReturn(tienda);

        when(this.tiendaService.saveTienda(any(Tienda.class))).thenReturn(tienda);

        this.mockMvc.perform(put("/api/v1/tienda/1").contentType(MediaType.APPLICATION_JSON).content(this.objectMapper.writeValueAsString(tienda)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.idTienda").value(1))
        .andExpect(jsonPath("$.nombre").value("EcoMarket Valdivia"))
        .andExpect(jsonPath("$.ciudad").value("Valdivia"))
        .andExpect(jsonPath("$.direccion").value("Avenida Armada 876"))
        .andExpect(jsonPath("$.telefono").value(78563396))
        .andExpect(jsonPath("$.correo").value("ecomarketvaldiv@contacto.cl"))
        .andExpect(jsonPath("$.horaApertura").value("08:00:00"))
        .andExpect(jsonPath("$.horaCierre").value("19:30:00"))
        .andExpect(jsonPath("$.region.codigoRegion").value(14))
        .andExpect(jsonPath("$.region.nombreRegion").value("Region de los Rios"));

    }

    @Test
    public void testDeleteTienda() throws Exception {

        doNothing().when(this.tiendaService).deleteById(1);

        this.mockMvc.perform(delete("/api/v1/tienda/1"))
            .andExpect(status().isNoContent());
        verify(this.tiendaService, times(1)).deleteById(1);

    }

}
