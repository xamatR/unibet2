package com.labprogweb.unibet2.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labprogweb.unibet2.Model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByLogin(String login);
}