package com.gestor_tiendas.gestor_tiendas.model;

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
public class Region {

    /* Nota
     * columnDefinition: define la columna en la base de datos
     * MySQL: INT(5)
     * ORACLE: NUMBER(5)
     */
    @Id
    @Column(columnDefinition = "INT(3)")
    private Integer codigoRegion;              // segun CODIGOS UNICOS TERRITORIALES, Chile, 3 digitos

    @Column(length = 70, nullable = false)
    private String nombreRegion;

    
}
