package com.example.mssonorausuarios.dto;

import com.example.mssonorausuarios.model.Usuario;

public record UsuarioResponseDTO(
        Long id,
        String nombre,
        String nickname,
        String correo,
        String rol
) {
    public static UsuarioResponseDTO desde(Usuario usuario) {
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getNickname(),
                usuario.getCorreo(),
                usuario.getRol()
        );
    }
}
