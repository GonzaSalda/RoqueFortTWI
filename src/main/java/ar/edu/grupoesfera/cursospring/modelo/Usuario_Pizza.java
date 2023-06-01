package ar.edu.grupoesfera.cursospring.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@Entity
@Table(name="usuario_Pizza")
public class Usuario_Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;


	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name = "pizza_id")
	private Pizza pizza;
	@Column(name = "pizza_comprada")
	private Boolean isComprada;

	@Column(name = "fecha_compra")
	private LocalDate fecha_incio_compra;

	@Column(name = "hora_compra")
	private LocalTime hora;




	public Usuario_Pizza(Usuario usuario, Pizza pizza) {
		this.usuario = usuario;
		this.pizza = pizza;
		this.isComprada = false;
	}


	public Usuario_Pizza() {

	}
}
