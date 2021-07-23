package com.upeu.app.catdoc.controller;

import com.upeu.app.catdoc.dto.DocenteDto;
import com.upeu.app.catdoc.dto.Message;
import com.upeu.app.catdoc.entity.Docente;
import com.upeu.app.catdoc.service.DocenteService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docente")
@CrossOrigin(origins = "http://localhost:4200")
public class DocenteController {
    @Autowired
    DocenteService docenteService;

    @GetMapping("/list")
    public ResponseEntity<List<Docente>> list(){
        List<Docente> list = docenteService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Docente> getById(@PathVariable("id") int id){
        if(!docenteService.existsById(id))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        Docente docente = docenteService.getOne(id).get();
        return new ResponseEntity(docente, HttpStatus.OK);
    }

    @GetMapping("/detailname/{nombre}")
    public ResponseEntity<Docente> getByName(@PathVariable("id") String name){
        if(!docenteService.existsByName(name))
            return new ResponseEntity(new Message("no existe"), HttpStatus.NOT_FOUND);
        Docente docente = docenteService.getByName(name).get();
        return new ResponseEntity(docente, HttpStatus.OK);
    }

    @PostMapping("/create")
    public  ResponseEntity<?> create(@RequestBody DocenteDto docenteDto) {
        if(StringUtils.isBlank(docenteDto.getName()))
            return new ResponseEntity(new Message("El nombres es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(docenteDto.getSurname()))
            return new ResponseEntity(new Message("El Apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(docenteDto.getEmail()))
            return new ResponseEntity(new Message("El Email es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(docenteDto.getPassword()))
            return new ResponseEntity(new Message("La constraseña es obligatoria"), HttpStatus.BAD_REQUEST);
        if(docenteDto.getPassword().length() < 4 )
            return new ResponseEntity(new Message("La contraseña es muy corta"), HttpStatus.BAD_REQUEST);
        if(docenteService.existsByName(docenteDto.getName()))
            return new ResponseEntity(new Message("El usuario ya existe"), HttpStatus.BAD_REQUEST);
        Docente docente = new Docente(docenteDto.getName(), docenteDto.getSurname(), docenteDto.getEmail(), docenteDto.getPassword());
        docenteService.save(docente);
        return  new ResponseEntity(new Message("Docente registrado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DocenteDto docenteDto) {
        if(!docenteService.existsById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        if(docenteService.existsByName(docenteDto.getName()) && docenteService.getByName(docenteDto.getName()).get().getId() != id)
            return new ResponseEntity(new Message("Ese nombre ya existe"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(docenteDto.getName()))
            return new ResponseEntity(new Message("El nombres es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(docenteDto.getSurname()))
            return new ResponseEntity(new Message("El Apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(docenteDto.getEmail()))
            return new ResponseEntity(new Message("El Email es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(docenteDto.getPassword()))
            return new ResponseEntity(new Message("La constraseña es obligatoria"), HttpStatus.BAD_REQUEST);
        if(docenteDto.getPassword().length() < 4 )
            return new ResponseEntity(new Message("La contraseña es muy corta"), HttpStatus.BAD_REQUEST);
        if(docenteService.existsByName(docenteDto.getName()))
            return new ResponseEntity(new Message("El usuario ya existe"), HttpStatus.BAD_REQUEST);

        Docente docente = docenteService.getOne(id).get();
        docente.setName(docenteDto.getName());
        docente.setSurname(docenteDto.getSurname());
        docente.setEmail(docenteDto.getEmail());
        docente.setPassword(docenteDto.getPassword());
        docenteService.save(docente);
        return  new ResponseEntity(new Message("Docente actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!docenteService.existsById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        docenteService.delete(id);
        return new ResponseEntity(new Message("Docente Eliminado"), HttpStatus.OK);
    }
}
