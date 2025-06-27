package com.gestor_tiendas.gestor_tiendas.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.gestor_tiendas.gestor_tiendas.model.Region;
import com.gestor_tiendas.gestor_tiendas.model.Tienda;
import com.gestor_tiendas.gestor_tiendas.repository.TiendaRepository;
import com.gestor_tiendas.gestor_tiendas.service.TiendaService;

@SpringBootTest
@ActiveProfiles("test")
public class TiendaServiceTest {

 @Autowired
    private TiendaService tiendaService;

    @MockBean
    private TiendaRepository tiendaRepository;

    @Test
    public void testFindAll() {

        when(tiendaRepository.findAll()).thenReturn(List.of(new Tienda()));
        List<Tienda> listaTiendas = tiendaService.findAll();

        assertNotNull(listaTiendas);    // verifica que la lista devuelta no sea nula
        assertEquals(1, listaTiendas.size());   // que sea una sola
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Region region = new Region();
        Tienda tienda = new Tienda(id, "EcoMarket Casa Matriz", "Santiago", "Villavicencio 456", 234500250, "contactolastarria@ecomarket.cl", LocalTime.of(8, 0), LocalTime.of(20, 0), region);
        // comportamiento del mock: cuando se use findById con "id", devuelve un opcional de tienda
        when(tiendaRepository.findById(id)).thenReturn(Optional.of(tienda));
        Tienda found = tiendaService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getIdTienda());

    }   

    @Test
    public void testSave() {
        // necesita una region D:
        Region region = new Region();

        Tienda tienda = new Tienda(1, "EcoMarket Casa Matriz", "Santiago", "Villavicencio 456", 234500250, "contactolastarria@ecomarket.cl", LocalTime.of(8, 0), LocalTime.of(20, 0), region);
        when(tiendaRepository.save(tienda)).thenReturn(tienda);
        Tienda saved = tiendaService.saveTienda(tienda);

        assertNotNull(saved);
        assertEquals("EcoMarket Casa Matriz", saved.getNombre());

    }

    @Test
    public void testDeleteById() {
        Integer id = 1;
        // mock: no hace nada al usar deleteById()
        doNothing().when(tiendaRepository).deleteById(id);
        tiendaService.deleteById(id);
        // Verifica que el metodo del repositorio se haya llamado UNA VEZ con el codigo 
        verify(tiendaRepository, times(1)).deleteById(id);

    }

}
