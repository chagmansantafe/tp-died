package TP;

import DB.ConexionDB;

public class GestorProductos {

	public void altaProducto(String nombre, String descripcion, Double precioUnitario, Double peso){

		Producto producto = new Producto(nombre,descripcion,precioUnitario,peso);
		
		new ConexionDB().crearProducto(producto);
		
		
	}
	
}
