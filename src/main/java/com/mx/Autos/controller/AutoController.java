package com.mx.Autos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Autos.dto.Respuesta;
import com.mx.Autos.entidad.Auto;
import com.mx.Autos.service.AutoService;

@RestController
@RequestMapping("autos")
@CrossOrigin
public class AutoController {
    @Autowired
    AutoService service;
    
    @GetMapping("listar")
    public ResponseEntity<List<Auto>> listar(){
        return service.mostrar();
    }
    
    @PostMapping("guardar")
    public Respuesta guardar(@RequestBody Auto auto) {
    	return service.guardar(auto);
    }
    
    @PostMapping("editar")
    public Respuesta editar(@RequestBody Auto auto) {
    	return service.editar(auto);
    }
    
    @PostMapping("eliminar")
    public Respuesta eliminar(@RequestBody Auto auto) {
    	return service.eliminar(auto);
    }
    
    @GetMapping("buscar/{matricula}")
    public ResponseEntity<Auto> buscar(@PathVariable("matricula")String matricula){
    	return service.buscar(matricula);
    }
    
    @GetMapping("buscarPorCurp/{curp}")
    public ResponseEntity<List<Auto>> buscarPorCurp(@PathVariable("curp")String curp){
    	return service.buscarPorCurp(curp);
    }
}
