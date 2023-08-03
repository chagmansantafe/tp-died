package TP;

import DB.ConexionDB;

public class GestorSucursales {

	public void altaSucursal(Integer id, String nombre, Boolean estado, String apertura, String cierre) {
 	
		
		TipoEstadoSucursal est;
		
		if(estado == true) {
			est = TipoEstadoSucursal.OPERATIVA;
		}
		else {
			est = TipoEstadoSucursal.NO_OPERATIVA;
		}
		
		Sucursal sucursal = new Sucursal(id,nombre,apertura,cierre,est);
		
		new ConexionDB().crearSucursal(sucursal);
		
		
	}
	
	
	
}
