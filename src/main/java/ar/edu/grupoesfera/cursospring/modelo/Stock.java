package ar.edu.grupoesfera.cursospring.modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Stock {
	
	private static Stock instance = new Stock();
	private Map<Ingrediente, Integer> stock = new HashMap<Ingrediente, Integer>();
	
	private Stock(){
	}

	public static Stock getInstance(){
		return instance;
	}
	

	public Set<Ingrediente> listarIngredientesDisponibles(){
		return stock.keySet();
	}
	

	public Map<Ingrediente, Integer> obtenerStock(){
		return stock;
	}

	public Boolean agregarIngrediente(Ingrediente ingrediente){
		if(this.stock.containsKey(ingrediente)){
			return false;
		}
		this.stock.put(ingrediente, 0);
		return true;
	}

	public Boolean agregarStock(Ingrediente ingrediente, Integer cantidad){
		if(!this.stock.containsKey(ingrediente)){
			return false;
		}
		Integer nuevaCantidad = this.stock.get(ingrediente) + cantidad;
		this.stock.put(ingrediente, nuevaCantidad);
		return true;
	}

	public Integer obtenerStockDisponible(Ingrediente ingrediente){
		if(!this.stock.containsKey(ingrediente)){
			return null;
		}
		return this.stock.get(ingrediente);
	}



	public Boolean eliminarIngrediente(Ingrediente ingrediente){
		if(!this.stock.containsKey(ingrediente)){
			return false;
		}
		this.stock.remove(ingrediente);
		return true;
	}
}
