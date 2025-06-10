package com.gestor_tiendas.gestor_tiendas;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.gestor_tiendas.gestor_tiendas.model.Tienda;
import com.gestor_tiendas.gestor_tiendas.repository.TiendaRepository;
import com.gestor_tiendas.gestor_tiendas.service.TiendaService;

@SpringBootTest
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
        // Long id = 23;
        Integer id = 4;
        Tienda tienda = new Tienda();
        // comportamiento del mock: cuando se use findById con "id", devuelve un opcional de tienda
        when(tiendaRepository.findById(id)).thenReturn(Optional.of(tienda));

        Tienda found = tiendaService.findById(id);

        assertNotNull(found);
        assertEquals(id, found.getIdTienda());

    }   

    @Test
    public void testSave() {

        Tienda tienda = new Tienda();
        when(tiendaRepository.save(tienda)).thenReturn(tienda);
        Tienda saved = tiendaService.saveTienda(tienda);

        assertNotNull(saved);
        assertEquals("EcoMarket Casa matriz", saved.getNombre());

    }

    @Test
    public void testDeleteById() {

        Integer id = 9;
        // mock: no hace nada al usar deleteById()
        doNothing().when(tiendaRepository).deleteById(id);
        tiendaService.deleteById(id);

        // Verifica que el metodo del repositorio se haya llamado UNA VEZ con el codigo 
        verify(tiendaRepository, times(1)).deleteById(id);

    }
}
