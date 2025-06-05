package com.gestor_tiendas.gestor_tiendas;

import net.datafaker.Faker;

import java.util.Random;

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
        for (int i = 0; i < 50; i++) {
            Tienda tienda = new Tienda();

            tienda.setNombre("EcoMarket" + faker.company().name());
            tienda.setCiudad(faker.address().city());
            tienda.setDireccion(faker.address().streetAddress());
            tienda.setTelefono(faker.number().numberBetween(10000000, 99999999));
            tienda.setCorreo(faker.internet().emailAddress());
            
            // horaApertura
            // horaCierre
            
            int codRegion = faker.number().numberBetween(1, 16);    // GENERA codigo aleatorio para la region
            Region region = new Region();       // Crea una nueva instancia/objeto de Region, ya que no tengo repositorio, servicio, de Region
            region.setCodigoRegion(codRegion);  // Define esa region con uno de los codigos aleatorios

            tienda.setRegion(region);   // tukiiiiiii

            tiendaRepository.save(tienda);


        }



    }

}
