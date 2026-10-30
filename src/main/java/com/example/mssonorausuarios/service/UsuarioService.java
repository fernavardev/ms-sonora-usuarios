package com.example.mssonorausuarios.service;

import com.example.mssonorausuarios.model.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listarUsuarios();

    Optional<Usuario> obtenerUsuarioPorId(Long id);

    Usuario crearUsuario(Usuario usuario);

    Usuario actualizarUsuario(Long id, Usuario usuario);

    void eliminarUsuario(Long id);

}