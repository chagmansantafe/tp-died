package TP;
//importo el paquete de excepciones entero

//metodos para setear stock van en sucursal
//metodo check stock tbn en sucursal? (stock.length = productos.length)
public class Stock {

	private Integer idSucursal;
	private Integer idProducto;
	private Integer stock;
	
	public Stock() {};
	
	public Stock (Integer ids,Integer idp,Integer s) {
		this();
		this.idSucursal = ids;
		this.idProducto = idp;
		this.stock = s;
	};
	public Integer getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Integer idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
/*
	//para alta
	//ver que exception se lanza
	public Stock(Producto pr, Integer stock) {
		
		this.producto = pr;
		this.stockProducto = stock;
	}
	
	//getters y setters
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Integer getStockProducto() {
		return stockProducto;
	}
	public void setStockProducto(Integer stockProducto) {
		this.stockProducto = stockProducto;
	}
	
	//modificar
	public void modificarStock(Integer stock) {
		
		this.stockProducto = stock;
		
	}
	public void modificarProducto(Producto prod) {
		this.producto = prod;
	}

	//baja en sucursal?
	
	//busqueda en sucursal?
}
*/