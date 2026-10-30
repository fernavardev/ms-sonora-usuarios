
package com.example.mssonorausuarios.service;

import com.example.mssonorausuarios.model.Usuario;
import com.example.mssonorausuarios.repository.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Usuario> listarUsuarios() {
        log.info("Obteniendo todos los usuarios");
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        log.info("Buscando usuario con id {}", id);

        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            log.warn("No se encontro un usuario con id {}", id);
        }

        return usuario;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        log.info("Creando nuevo usuario");

        if (usuarioRepository.findByCorreo(usuario.getCorreo()).isPresent()) {
            log.warn("Ya existe un usuario registrado con el correo {}", usuario.getCorreo());
            throw new RuntimeException("El correo ya se encuentra registrado");
        }

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        log.info("Actualizando usuario con id {}", id);

        Usuario existente = usuarioRepository.findById(id).orElseThrow(() -> {
            log.warn("No se encontro un usuario para actualizar con id {}", id);
            return new RuntimeException("Usuario no encontrado");
        });

        // Verificar que el correo no pertenezca a otro usuario
        usuarioRepository.findByCorreo(usuario.getCorreo()).ifPresent(encontrado -> {
            if (!encontrado.getId().equals(id)) {
                log.warn("El correo {} ya pertenece a otro usuario", usuario.getCorreo());
                throw new RuntimeException("El correo ya se encuentra registrado");
            }
        });

        existente.setNombre(usuario.getNombre());
        existente.setNickname(usuario.getNickname());
        existente.setCorreo(usuario.getCorreo());
        existente.setRol(usuario.getRol());

        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            existente.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }

        return usuarioRepository.save(existente);
    }

    @Override
    public void eliminarUsuario(Long id) {
        log.info("Eliminando usuario con id {}", id);

        if (!usuarioRepository.existsById(id)) {
            log.warn("No se encontro un usuario para eliminar con id {}", id);
            throw new RuntimeException("Usuario no encontrado");
        }

        usuarioRepository.deleteById(id);
    }
}
