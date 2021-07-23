package com.upeu.app.catdoc.service;

import com.upeu.app.catdoc.entity.Docente;
import com.upeu.app.catdoc.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DocenteService {
    @Autowired
    DocenteRepository docenteRepository;

    public List<Docente> list(){
        return docenteRepository.findAll();
    }

    public Optional<Docente> getOne(int id){
        return docenteRepository.findById(id);
    }

    public  Optional<Docente> getByName(String name){
        return docenteRepository.findByName(name);
    }

    public void save(Docente docente){
        docenteRepository.save(docente);
    }

    public void delete(int id){
        docenteRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return docenteRepository.existsById(id);
    }

    public boolean existsByName(String name){
        return docenteRepository.existsByName(name);
    }
}
