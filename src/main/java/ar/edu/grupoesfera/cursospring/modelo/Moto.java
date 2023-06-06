package ar.edu.grupoesfera.cursospring.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private int cantidadDisponible;

    public Moto() {
    }

    public Moto(int id, String marca, int cantidadDisponible) {
        this.id = id;
        this.marca = marca;
        this.cantidadDisponible = cantidadDisponible;
    }

    @Override
    public String toString() {
        return "Moto{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", cantidadDisponible=" + cantidadDisponible +
                '}';
    }

}