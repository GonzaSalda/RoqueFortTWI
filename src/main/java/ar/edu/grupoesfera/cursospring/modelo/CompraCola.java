package ar.edu.grupoesfera.cursospring.modelo;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class CompraCola {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Pizza pizza;

    @Column
    private String direccion;

    @Column
    private boolean delivery;

    @Column
    private long tiempoViajeMinutos;
    public CompraCola() {
    }
    public CompraCola(Usuario usuario, Pizza pizza, String direccion, boolean delivery, long tiempoViajeMinutos) {
        this.usuario = usuario;
        this.pizza = pizza;
        this.direccion = direccion;
        this.delivery = delivery;
        this.tiempoViajeMinutos = tiempoViajeMinutos;
    }


    public CompraCola(Usuario usuario, List<Pizza> pizzas, String direccion, boolean delivery, long tiempoViajeMinutos) {
    }
}
