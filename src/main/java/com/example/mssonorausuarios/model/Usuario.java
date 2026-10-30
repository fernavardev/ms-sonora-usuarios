package com.example.mssonorausuarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El nickname no puede estar vacio")
    @Size(min = 2, max = 100, message = "El nickname debe tener entre 2 y 100 caracteres")
    private String nickname;

    @NotBlank(message = "El correo no puede estar vacio")
    @Email(message = "El correo debe tener un formato valido")
    private String correo;

    @NotBlank(message = "La contraseña no puede estar vacia")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El rol no puede estar vacio")
    @Pattern(
        regexp = "^(USUARIO|ADMIN)$",
        message = "El rol solo puede ser USUARIO o ADMIN"
    )
    private String rol;

    public Usuario() {
    }

    public Usuario(String nombre, String nickname, String correo,
                   String password, String rol) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
