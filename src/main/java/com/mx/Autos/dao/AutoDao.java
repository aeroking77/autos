package com.mx.Autos.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.Autos.entidad.Auto;
import java.util.List;


public interface AutoDao extends JpaRepository<Auto, String> {
    List<Auto> findByCurpPersona(String curpPersona);
}
