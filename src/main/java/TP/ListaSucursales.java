package TP;

import java.util.ArrayList;

import DB.ConexionDB;


public class ListaSucursales {

	ArrayList<Sucursal> lista = new ArrayList<Sucursal>();
	
	public ListaSucursales(){
	
	//TODO PIDO A LA BASE DE DATOS
	lista = new ConexionDB().crearListadeSucursales();

	}

	public ArrayList<Sucursal> getLista() {
		return lista;
	}

	public void setLista(ArrayList<Sucursal> lista) {
		this.lista = lista;
	}
	
}
