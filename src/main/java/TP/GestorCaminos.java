package TP;

import DB.ConexionDB;

public class GestorCaminos {

public void altaCamino(Integer id, Integer origen, Integer destino, Integer capacidad ,Boolean estado, Integer tiempo) {
 	
		TipoEstadoSucursal est;
		
		if(estado == true) {
			est = TipoEstadoSucursal.OPERATIVA;
		}
		else {
			est = TipoEstadoSucursal.NO_OPERATIVA;
		}
		
		Camino camino = new Camino(id,origen,destino,capacidad,est,tiempo);
		
		new ConexionDB().crearCamino(camino);
		
		
	}
	
	
}
