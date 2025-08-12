package com.mx.Autos.service;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mx.Autos.dao.AutoDao;
import com.mx.Autos.dto.Respuesta;
import com.mx.Autos.entidad.Auto;

@Service
public class AutoService {

    AutoDao dao;
    
    public AutoService(AutoDao autoDao) {
        dao = autoDao;
    }
    
    public ResponseEntity<List<Auto>> mostrar(){
        if(dao.findAll().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(dao.findAll());
    }
    
    public Respuesta guardar(Auto auto) {
        Respuesta rs = new Respuesta();
        if(dao.existsById(auto.getMatricula())) {
            rs.setMensaje("El auto no se agrego por que ya existe en la base de datos");
            rs.setSuccess(false);
            rs.setObj(auto.getMatricula());
            return rs;
        }
        dao.save(auto);
        rs.setMensaje("El auto ha sido agregado a la base de datos");
        rs.setSuccess(true);
        rs.setObj(auto);
        return rs;
    }
    
    public Respuesta editar(Auto auto) {
        Respuesta rs = new Respuesta();
        if(dao.existsById(auto.getMatricula())) {
            rs.setMensaje("El auto ha sido editado");
            dao.save(auto);
            rs.setSuccess(true);
            rs.setObj(auto);
            return rs;
        }
        rs.setMensaje("El auto no se ha editado");
        rs.setSuccess(false);
        rs.setObj(auto.getMatricula());
        return rs;
    }
    
    public Respuesta eliminar(Auto auto) {
        Respuesta rs = new Respuesta();
        auto = dao.findById(auto.getMatricula()).orElse(null);
        if(auto == null) {
            rs.setMensaje("El auto que tratas de eliminar no existe");
            rs.setSuccess(false);
            rs.setObj(null);
            return rs;
        }
        rs.setObj(new Auto(auto.getMatricula(),
        		           auto.getModelo(),
        		           auto.getMarca(),
        		           auto.getAnio(),
        		           auto.getKilometraje(),
        		           auto.getPrecio(),
        		           auto.getCilindros(),
        		           auto.getCurpPersona()));
        dao.delete(auto);
        rs.setMensaje("El auto ha sido eliminado");
        rs.setSuccess(true);
        return rs;
    }
    
    public ResponseEntity<Auto> buscar(String matricula){
    	Auto auto = dao.findById(matricula).orElse(null);
    	if(auto == null) {
    		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    	}
    	return ResponseEntity.status(HttpStatus.OK).body(auto);
    }
    
    public ResponseEntity<List<Auto>> buscarPorCurp(String curp){
        List<Auto> autos = dao.findByCurpPersona(curp);
        if(autos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(autos);
    }
    
}
