package com.gestor_tiendas.gestor_tiendas;

import net.datafaker.Faker;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.gestor_tiendas.gestor_tiendas.model.Region;
import com.gestor_tiendas.gestor_tiendas.model.Tienda;
import com.gestor_tiendas.gestor_tiendas.repository.TiendaRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    // Instanciar repositorios
    @Autowired
    public TiendaRepository tiendaRepository;

    @Override
    public void run(String... args) throws Exception {

        Faker faker = new Faker();

        // Generar tiendas
        for (int i = 0; i < 10; i++) {
            
            Tienda tienda = new Tienda();

            tienda.setNombre("EcoMarket " + faker.company().name());
            tienda.setCiudad(faker.address().city());
            tienda.setDireccion(faker.address().streetAddress());
            tienda.setTelefono(faker.number().numberBetween(10000000, 99999999));
            tienda.setCorreo(faker.internet().emailAddress());
            
            tienda.setHoraApertura(LocalTime.of(faker.number().numberBetween(8, 12), 0)); // Abren desde 8 a 12 hrs en punto (0)
            tienda.setHoraCierre(LocalTime.of(faker.number().numberBetween(15, 21), 0)); // cierran desde 15 a 21 hrs en punto (0)
            
            int codRegion = faker.number().numberBetween(1, 16);    // GENERA codigo aleatorio para la region
            Region region = new Region();       // Crea una nueva instancia/objeto de Region, ya que no tengo repositorio, servicio, de Region
            region.setCodigoRegion(codRegion);  // Define esa region con uno de los codigos aleatorios

            tienda.setRegion(region);   // tukiiiiiii

            tiendaRepository.save(tienda);

        }



    }

}
