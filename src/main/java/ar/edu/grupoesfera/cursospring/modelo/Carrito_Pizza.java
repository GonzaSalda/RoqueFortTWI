package ar.edu.grupoesfera.cursospring.modelo;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "carrito_pizza")
public class Carrito_Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "carrito_id")
	private Carrito carrito;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "pizza_id")
	private Pizza pizza;

	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
	@JoinColumn(name = "extra_id")
	private Extra extra;

	@Column(name = "cantidad")
    private Integer  cantidad;

	@Column(name = "cantidadExtra", nullable = true)
    private Integer  cantidadExtra;


	public Carrito_Pizza() {
	}

}
