package com.example.mssonorausuarios.config;

import com.example.mssonorausuarios.model.Usuario;
import com.example.mssonorausuarios.repository.UsuarioRepository;
import com.example.mssonorausuarios.service.UsuarioService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public UsuarioDataLoader(
            UsuarioRepository usuarioRepository,
            UsuarioService usuarioService) {

        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(String... args) {

        crearSiNoExiste("Fernando", "guitarrista_pro",
                "fernando@sonora.cl", "ClaveMusical#2026", "USUARIO");

        crearSiNoExiste("Camila", "baterista",
                "camila@sonora.cl", "Bateria#2026", "USUARIO");

        crearSiNoExiste("Administrador", "admin",
                "admin@sonora.cl", "Admin#2026", "ADMIN");
    }

    private void crearSiNoExiste(
            String nombre,
            String nickname,
            String correo,
            String password,
            String rol) {

        if (usuarioRepository.findByCorreo(correo).isEmpty()) {

            Usuario usuario = new Usuario(
                    nombre, nickname, correo, password, rol
            );

            usuarioService.crearUsuario(usuario);
        }
    }
}
