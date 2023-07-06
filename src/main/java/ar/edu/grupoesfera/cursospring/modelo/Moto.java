package ar.edu.grupoesfera.cursospring.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Identificador")
    private int id;
    @Column(name = "Marca")
    private String marca;
    private boolean disponible = true;
    @Column(name = "HorarioEntrega")
    private LocalTime horarioEntrega;
    @Column(name = "HorarioSalidaPlanificado")
    private LocalTime horarioSalidaPlanificado;
    @Column(name = "HorarioVueltaNegocio")
    private LocalTime horarioVueltaNegocio;
    public Moto() {
    }

    public Moto(int id, String marca, boolean disponible) {
        this.id = id;
        this.marca = marca;
        this.disponible= disponible;
    }

    public Moto(String marca,  boolean disponible) {
        this.marca = marca;
        this.disponible= disponible;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", disponible='" + disponible + '\'' +
                '}';
    }


}