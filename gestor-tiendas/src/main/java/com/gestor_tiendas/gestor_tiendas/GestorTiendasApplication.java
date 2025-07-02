package com.gestor_tiendas.gestor_tiendas;

//import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//
@SpringBootApplication
public class GestorTiendasApplication {

	public static void main(String[] args) {
		// carga variables de entorno ANTES que Spring Boot inicie, para que esten disponibles para Spring Boot
		//Dotenv dotenv = Dotenv.load();

		SpringApplication.run(GestorTiendasApplication.class, args);
	}

}
