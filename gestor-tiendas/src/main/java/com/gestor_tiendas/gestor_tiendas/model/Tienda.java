package com.gestor_tiendas.gestor_tiendas.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tienda")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tienda {

    @Id //jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTienda;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String ciudad;

    @Column(length = 200, nullable = false)
    private String direccion;

    @Column(nullable = false, unique = true)    // restriccion: UK (debe ser unico), cada tienda tendra su numero contacto
    private int telefono;

    @Column(length = 50, unique = true)         // restriccion: UK (debe ser unico), cada tienda tendra su correo
    private String correo;
    
    @Column(nullable = false)
    private LocalTime horaApertura;

    @Column(nullable = false)
    private LocalTime horaCierre;
    


}
