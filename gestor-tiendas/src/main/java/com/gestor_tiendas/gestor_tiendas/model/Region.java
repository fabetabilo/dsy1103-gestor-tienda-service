package com.gestor_tiendas.gestor_tiendas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "region")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa regiones de Chile")
public class Region {

    /* Nota
     * columnDefinition: define la columna en la base de datos
     * MySQL: INT(5)
     * ORACLE: NUMBER(5)
     */
    @Id
    @Column(columnDefinition = "INT(3)")
    @Schema(description = "Codigo CUT (codigo unico territorial)", example = "13")
    private Integer codigoRegion;              // segun CODIGOS UNICOS TERRITORIALES, Chile, 3 digitos

    @Column(length = 70, nullable = false)
    @Schema(description = "Nombre de la region", example = "Region Metropolitana de Santiago")
    private String nombreRegion;

    
}
