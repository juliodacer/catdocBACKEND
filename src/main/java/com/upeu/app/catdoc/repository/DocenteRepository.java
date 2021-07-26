package com.upeu.app.catdoc.repository;

import com.upeu.app.catdoc.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Integer> {
    Optional<Docente> findByName(String name);
    boolean existsByName(String name);
    <Optional>Docente findByEmailAndPassword(String email, String password);
}
