package ar.edu.grupoesfera.cursospring.modelo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import jakarta.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "Carritos")
public class Carrito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
	private List<Carrito_Pizza> carritoPizzas;

}
