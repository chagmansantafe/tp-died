package TP;
import java.time.LocalDate;
import java.util.HashMap;

public class OrdenDeProvision {

	Integer id;
	LocalDate fechaOrden;
	Sucursal sucursalDestino;
	Integer horasMaximo;
	HashMap<Producto,Integer> listaProductos;
	EstadoOrdenDeProvision estado;

	public OrdenDeProvision(LocalDate fecha, Sucursal destino, Integer horas) {
		this.fechaOrden = fecha;
		this.sucursalDestino = destino;
		this.horasMaximo = horas;
		this.listaProductos = new HashMap<>();
		this.estado = EstadoOrdenDeProvision.PENDIENTE;
	}
	
	//agregar producto con cantidad
	public void agregarProducto(Producto producto, Integer cantidad) {
		
		listaProductos.put(producto,cantidad);
		
		return;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void establecerEnProceso() {
		this.estado = EstadoOrdenDeProvision.EN_PROCESO;
	}
	
	public LocalDate getFechaOrden() {
		return fechaOrden;
	}

	public void setFechaOrden(LocalDate fechaOrden) {
		this.fechaOrden = fechaOrden;
	}

	public Sucursal getSucursalDestino() {
		return sucursalDestino;
	}

	public void setSucursalDestino(Sucursal sucursalDestino) {
		this.sucursalDestino = sucursalDestino;
	}

	public Integer getHorasMaximo() {
		return horasMaximo;
	}

	public void setHorasMaximo(Integer horasMaximo) {
		this.horasMaximo = horasMaximo;
	}

	public HashMap<Producto, Integer> getListaProductos() {
		return listaProductos;
	}

	public HashMap<Integer, Integer> getListaProductosID() {
		
		HashMap <Integer,Integer> lista = new HashMap<>();
		HashMap<Producto, Integer> listaProductos = new HashMap<>();
		listaProductos = this.getListaProductos();		
		for (Producto p: listaProductos.keySet()) {
			lista.put(p.getId(),listaProductos.get(p));
		}
		return lista;
	}
	public void setListaProductos(HashMap<Producto, Integer> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public EstadoOrdenDeProvision getEstado() {
		return estado;
	}

	public String getEstadoString() {
		if(estado == EstadoOrdenDeProvision.PENDIENTE){
			return "pendiente";
		}
		else {
			return "en_proceso";
		}
	}
	
	public void setEstado(EstadoOrdenDeProvision estado) {
		this.estado = estado;
	}
	

}
