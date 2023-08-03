package TP;

import java.util.ArrayList;

public class ListaStock {

	ArrayList<Stock> lista = new ArrayList<Stock>();

	
	public ListaStock(Sucursal sucursal) {
			
		//consultaSQL a BD
		
		//lista= return consulta

		String nombre;
		String descripcion;
		Double precioUnitario;
		Double peso;
		
		Producto producto1;
		
		nombre = "producto1";
		descripcion = "descripcion teeeeeeeeeeeeeeeeeeeeeeeeeeeest";
		precioUnitario = (double) 69000;
		peso = (double) 50;
		producto1 = new Producto(nombre, descripcion, precioUnitario, peso);
		
		
		Stock stock = new Stock(producto1,69);
		lista.add(stock);
		
	}
	
	public ArrayList<Stock> getLista() {
		return lista;
	}
	
}
