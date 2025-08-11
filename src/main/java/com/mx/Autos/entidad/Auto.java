package com.mx.Autos.entidad;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "AUTOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auto {
    private String matricula;  
    private String modelo;
    private String marca;
    private int anio;
    private long kilometraje;
    private double precio;
    private int cilindros;
    private String curpPersona;
}
