package ar.edu.grupoesfera.cursospring.modelo;

import java.util.LinkedList;
import java.util.List;



public class Extras {

	private static Extras instance = new Extras();
	private List<Ingrediente> ingredientes = new LinkedList<Ingrediente>();

	private Extras(){}
	
	public static Extras getInstance(){
		return instance;
	}
	
	
	public void vaciar() {
        ingredientes.clear();
    }

    public void agregarIngrediente(Ingrediente ingrediente) {
        Stock stock= Stock.getInstance();
        if(stock.obtenerStockDisponible(ingrediente)>0){
        	ingredientes.add(ingrediente);        	
        }
    }

	public List<Ingrediente> verIngredientes(){
		return ingredientes;
	}

    public Double total() {
        Double total= new Double(0);
        for (Ingrediente aux : ingredientes){
        	total=total + aux.getPrecio();
        }
        return total;
    }


    public void agregarIngrediente(String tomate) {
    }
}
