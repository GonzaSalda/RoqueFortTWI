package ar.edu.grupoesfera.cursospring.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Identificador")
    private int id;
    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Email")
    private String email;
    @Column(name = "Password")
    private String password;
    @Column(name = "NumeroTarjeta")
    private Integer nroTarjeta;
    @Column(name = "Rol")
    private String rol;
    @Column(name = "Imagen")
    private String imagen;



}
