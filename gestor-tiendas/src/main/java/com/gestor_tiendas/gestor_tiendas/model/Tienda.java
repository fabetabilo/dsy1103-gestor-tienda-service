package com.gestor_tiendas.gestor_tiendas.model;

import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tienda")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa una tienda")
public class Tienda {

    @Id //jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Id de tienda", example = "6")
    private Integer idTienda;

    @Column(length = 100, nullable = false)
    @Schema(description = "Nombre de la tienda", example = "EcoMarket Casa Matriz")
    private String nombre;

    @Column(length = 50, nullable = false)
    @Schema(description = "Ciudad en que se ubica la tienda", example = "Santiago")
    private String ciudad;

    @Column(length = 200, nullable = false)
    @Schema(description = "Direccion con numero en que se ubica la tienda", example = "Avenida Vicencio, 4542")
    private String direccion;

    @Column(nullable = false, unique = true)    // restriccion: UK (debe ser unico), cada tienda tendra su numero contacto
    @Schema(description = "Telefono de contacto de tienda", example = "12234543")
    private int telefono;

    @Column(length = 50, nullable = false, unique = true)         // restriccion: UK (debe ser unico), cada tienda tendra su correo
    @Schema(description = "Correo electronico de contacto de tienda", example = "ecomarketlastarria@contacto.cl")
    private String correo;
    
    @Column(nullable = false)
    @Schema(description = "Hora de apertura de tienda", example = "8:00:0000")
    private LocalTime horaApertura;

    @Column(nullable = false)
    @Schema(description = "Hora de cierre de tienda", example = "19:00:0000")
    private LocalTime horaCierre;
    
    @ManyToOne
    @JoinColumn(name = "codigo_region", referencedColumnName = "codigoRegion", nullable = false)
    private Region region;          // region a la que pertenece la tienda


}
