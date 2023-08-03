package TP;

import java.time.LocalDate;
import java.util.ArrayList;

public class ListaOrdenes {

	ArrayList<OrdenDeProvision> lista = new ArrayList<OrdenDeProvision>();
	
	public ListaOrdenes(Sucursal sucursal){
		
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
		
		
		LocalDate fecha = LocalDate.now();
		Sucursal destino = sucursal;
		Integer horas = 2;
		OrdenDeProvision orden = new OrdenDeProvision( fecha,  destino,  horas);
		orden.agregarProducto(producto1,20);
		
		lista.add(orden);
		
	}
	
	public ArrayList<OrdenDeProvision> getLista() {
		return lista;
	}
	
}
