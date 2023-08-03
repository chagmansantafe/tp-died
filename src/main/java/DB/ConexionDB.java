package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import TP.Camino;
import TP.OrdenDeProvision;
import TP.Producto;
import TP.Sucursal;
import TP.TipoEstadoSucursal;
import TP.Stock;
import UI.AltaConfirmada;
import UI.ModificacionConfirmada;
import UI.BorradoConfirmado;
import UI.VentanasError;

public class ConexionDB {
	// DATOS DE LA BASE DE DATOS A LA QUE NOS CONECTAMOS
	private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USR = "postgres";
	private static final String PAS = "test123";
	private static final String DRIVER = "org.postgresql.Driver";

	// SENTENCIAS EN POSGRESQL
	// SUCURSAL
	private static final String insert_sucursal = "INSERT INTO sucursal (ID, nombre, estado, apertura, cierre) VALUES (?,?,?,?,?)";
	private static final String update_sucursal = "UPDATE public.sucursal ("
			+ "id,nombre,hapertura,hcierre,estado,direccion)"
			+ "SET nombre=?,hapertura=?,hcierre=?,estado=?,direccion=?" + "WHERE id=?";
	private static final String delete_sucursal = "DELETE FROM public.sucursal ("
			+ "id,nombre,hapertura,hcierre,estado,direccion)" + "WHERE id=?";
	private static final String search_sucursal = "SELECT * FROM public.sucursal ("
			+ "id,nombre,hapertura,hcierre,estado,direccion)" + "WHERE id= ?";
	private static final String ver_sucursales = "SELECT * FROM sucursal";

	// PRODUCTO
	private static final String insert_producto = "INSERT INTO producto (nombre, descripcion, precio, peso) VALUES (?,?,?,?)";
	private static final String update_producto = "UPDATE public.producto (" + "id,nombre,descripcion,precio,peso)"
			+ "SET nombre=?,descripcion=?,precio=?,peso=?" + "WHERE id=?";
	private static final String delete_producto = "DELETE FROM public.producto (" + "id,nombre,descripcion,precio,peso)"
			+ "WHERE id=?";
	private static final String search_producto_id = "SELECT * FROM public.producto ("
			+ "id,nombre,descripcion,precio,peso)" + "WHERE id= ?";
	private static final String ver_productos = "SELECT * FROM producto";


	// CAMINO
	private static final String ver_caminos = "SELECT * FROM camino";
	private static final String insert_camino = "INSERT INTO camino (id, origen, destino, capacidad, estado, tiempotransito) VALUES (?,?,?,?,?,?)";
	private static final String update_camino = "UPDATE public.camino (" + "id,origen,destino,capMaxima,estado,tiempo)"
			+ "SET origen=?,destino=?,capMaxima=?,estado=?,tiempo=?" + "WHERE id=?";
	private static final String delete_camino = "DELETE FROM public.camino ("
			+ "id,origen,destino,capMaxima,estado,tiempo)" + "WHERE id=?";

	// ORDEN DE PROVISION
	private static final String insert_ordenProvision = "INSERT INTO public.camino ("
			+ "idOrden,fechaOrden,idSucursalDestino," + "horasMaximo,id_lista,estado)" + "VALUES (null,?,?,?,?,?)";

	// STOCK
	private static final String insert_stock = "INSERT INTO public.stock (" + "id_sucursal,id_producto,stock)"
			+ "VALUES (?,?,?)";
	private static final String update_stock = "UPDATE public.stock (" + "id_sucursal,id_producto,stock)"
			+ "SET  id_producto =?, stock =?" + "WHERE id_sucursal=?";
	private static final String delete_stock = "DELETE FROM public.stock (" + "id_sucursal,id_producto,stock)"
			+ "WHERE id_sucursal=? AND id_producto=?";

	// LISTA DE PRODUCTOS
	private static final String insert_lista = "INSERT INTO public.lista (" + "id_orden,id_producto,cantidad)"
			+ "VALUES (?,?,?)";

	public void crearSucursal(Sucursal s) {

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USR, PAS);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pstm;

		try {
			pstm = conn.prepareStatement(insert_sucursal);
			pstm.setInt(1, s.id());
			pstm.setString(2, s.getNombre());

			Boolean est;
			if (s.estadoToString() == "Operativa") {
				est = true;
			} else {
				est = false;
			}
			pstm.setBoolean(3, est);
			pstm.setString(4, s.getHorarioApertura());
			pstm.setString(5, s.getHorarioCierre());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
				new AltaConfirmada();
			}

			if (pstm != null) {
				pstm.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			new VentanasError(e.getMessage());
			try {
				conn.close();
			} catch (SQLException e1) {
				new VentanasError(e.getMessage());
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void modificarSucursal(Sucursal s) {
		Connection conx = null;
		PreparedStatement pstm = null;
		try {
			// cargar el driver
			Class.forName(DRIVER);
			// obtenemos una coneccion
			conx = DriverManager.getConnection(URL, USR, PAS);
			// preparamos el comando sql
			pstm = conx.prepareStatement(update_sucursal);
			// nombre=?,hapertura=?,hcierre=?,estado=?,direccion=?" + "WHERE id=?
			pstm.setString(1, s.getNombre());
			pstm.setString(2, s.getHorarioApertura());
			pstm.setString(3, s.getHorarioCierre());
			if (s.estadoToString() == "Operativa") {
				pstm.setBoolean(4, true);
			} else {
				pstm.setBoolean(4, false);
			}
			pstm.setInt(5, s.id());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
				new ModificacionConfirmada();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void borrarSucursal(Sucursal s) {
		Connection conx = null;
		PreparedStatement pstm = null;
		try {
			// cargar el driver
			Class.forName(DRIVER);
			// obtenemos una coneccion
			conx = DriverManager.getConnection(URL, USR, PAS);
			// preparamos el comando sql
			pstm = conx.prepareStatement(delete_sucursal);
			// id=?
			pstm.setInt(1, s.id());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
				new BorradoConfirmado();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public Sucursal buscarSucursalporId(Integer id) {
		Connection conx = null;
		PreparedStatement pstm = null;
		Sucursal suc = new Sucursal();
		try {
			// cargar el driver
			Class.forName(DRIVER);
			// obtenemos una coneccion
			conx = DriverManager.getConnection(URL, USR, PAS);
			// preparamos el comando sql
			pstm = conx.prepareStatement(search_sucursal);
			// nombre=?,hapertura=?,hcierre=?,estado=?,direccion=?" + "WHERE id=?
			pstm.setInt(1, id);
			ResultSet rs = pstm.executeQuery();
			while (rs.next()) {
				
				suc.setId(rs.getInt(1));
				suc.setNombre(rs.getString(2));
				suc.setHorarioapertura(rs.getString(3));
				suc.setHorariocierre(rs.getString(4));
				suc.setEstado(rs.getInt(5));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 

			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			return suc;
		}


	public ArrayList<Sucursal> crearListadeSucursales() {
		
		ArrayList<Sucursal> listaSuc = new ArrayList<>();

	
			try {
				
				Class.forName(DRIVER);
				
			} catch (ClassNotFoundException e) {
				new VentanasError(e.getMessage());
				e.printStackTrace();
			}

			try (Connection conn = DriverManager.getConnection(URL, USR, PAS);
					Statement statement = conn.createStatement()) {

				String selectQuery = ver_sucursales;
				boolean hasResultSet = statement.execute(selectQuery);

				if (hasResultSet) {
					ResultSet rs = statement.getResultSet();
					while (rs.next()) {

						Integer id = rs.getInt(1);
						String nombre = rs.getString(2);

						TipoEstadoSucursal estado;
						if (rs.getBoolean(3) == true) {
							estado = TipoEstadoSucursal.OPERATIVA;
						} else {
							estado = TipoEstadoSucursal.NO_OPERATIVA;
						}

						String apertura = rs.getString(4);
						String cierre = rs.getString(5);
						Sucursal suc = new Sucursal(id, nombre, apertura, cierre, estado);
						listaSuc.add(suc);
					}
				}
				
				try {
					conn.close();
				} catch (SQLException e) {
					new VentanasError(e.getMessage());
					e.printStackTrace();
				}
				
			} catch (SQLException e) {
				new VentanasError(e.getMessage());
				e.printStackTrace();
			}


		return listaSuc;
		
		
		
	}

	public void crearProducto(Producto p) {
		
		/*
		Connection conx = null;
		PreparedStatement pstm = null;
		try {
			// cargar el driver
			Class.forName(DRIVER);
			// obtenemos una coneccion
			conx = DriverManager.getConnection(URL, USR, PAS);
			// preparamos el comando sql
			pstm = conx.prepareStatement(insert_producto);
			// id,nombre,descripcion,precio,peso
			pstm.setInt(1, p.getId());
			pstm.setString(2, p.getNombre());
			pstm.setString(3, p.getDescripcion());
			pstm.setFloat(4, p.getPrecio());
			pstm.setFloat(5, p.getPeso());

			int cantidad = pstm.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		*/
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USR, PAS);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PreparedStatement pstm;

		try {
			pstm = conn.prepareStatement(insert_producto);
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getDescripcion());
			pstm.setDouble(3, p.getPrecio());
			pstm.setDouble(4, p.getPeso());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
				new AltaConfirmada();
			}

			if (pstm != null) {
				pstm.close();
			}

			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			new VentanasError(e.getMessage());
			try {
				conn.close();
			} catch (SQLException e1) {
				new VentanasError(e.getMessage());
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public ArrayList<Producto> crearListadeProductos() {
		
		ArrayList<Producto> listaProd = new ArrayList<>();

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			new VentanasError(e.getMessage());
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(URL, USR, PAS);
				Statement statement = conn.createStatement()) {

			String selectQuery = ver_productos;
			boolean hasResultSet = statement.execute(selectQuery);

			if (hasResultSet) {
				ResultSet rs = statement.getResultSet();
				while (rs.next()) {
					Producto prod = new Producto();
					prod.setNombre(rs.getString(1));
					prod.setDescripcion(rs.getString(2));
					prod.setPrecioUnitario(rs.getDouble(3));
					prod.setPeso(rs.getDouble(4));
					listaProd.add(prod);
				}
			}

			try {
				conn.close();
			} catch (SQLException e) {
				new VentanasError(e.getMessage());
				e.printStackTrace();
			}

		} catch (SQLException e) {
			new VentanasError(e.getMessage());

			e.printStackTrace();
		}

		return listaProd;
		
	}
	
	public void modificarProducto(Producto p) {
		Connection conx = null;
		PreparedStatement pstm = null;
		try {
			// cargar el driver
			Class.forName(DRIVER);
			// obtenemos una coneccion
			conx = DriverManager.getConnection(URL, USR, PAS);
			// preparamos el comando sql
			pstm = conx.prepareStatement(update_producto);
			// nombre=?,descripcion=?,precio=?,peso=?" + "WHERE id=
			pstm.setString(1, p.getNombre());
			pstm.setString(2, p.getDescripcion());
			pstm.setDouble(3, p.getPrecio());
			pstm.setDouble(4, p.getPeso());
			pstm.setInt(5, p.getId());
			int cantidad = pstm.executeUpdate();
			if (cantidad >0) {new ModificacionConfirmada();}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void borrarProducto(Producto p) {
		Connection conx = null;
		PreparedStatement pstm = null;
		try {
			// cargar el driver
			Class.forName(DRIVER);
			// obtenemos una coneccion
			conx = DriverManager.getConnection(URL, USR, PAS);
			// preparamos el comando sql
			pstm = conx.prepareStatement(delete_producto);
			// "DELETE FROM public.producto (" + "id,nombre,descripcion,precio,peso)"
			// "WHERE id=?";
			pstm.setInt(1, p.getId());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
				new BorradoConfirmado();}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void crearCamino(Camino c) {

		Connection conx = null;
		PreparedStatement pstm = null;
		try {
// cargar el driver 
			Class.forName(DRIVER);
// obtenemos una coneccion  
			conx = DriverManager.getConnection(URL, USR, PAS);
// preparamos el comando sql 
			pstm = conx.prepareStatement(insert_camino);
// id,nombre,descripcion,precio,peso
			pstm.setInt(1, c.getID());
			pstm.setInt(2, c.getOrigen());
			pstm.setInt(3, c.getDestino());
			pstm.setInt(4, c.getCapMaxima());
			if(c.estadoToString() == "Operativo") {
				pstm.setBoolean(5, true);
			}
			else {
				pstm.setBoolean(5, false);
			}
			pstm.setInt(6, c.getTiempoTransito());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
			 	   new AltaConfirmada();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			new VentanasError(e.getMessage());
		} catch (SQLException e) {
			new VentanasError(e.getMessage());
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					new VentanasError(e.getMessage());
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					new VentanasError(e.getMessage());
					e.printStackTrace();
				}
		}
	}

	public void modificarCamino(Camino c) {

		Connection conx = null;
		PreparedStatement pstm = null;
		try {
// cargar el driver 
			Class.forName(DRIVER);
// obtenemos una coneccion  
			conx = DriverManager.getConnection(URL, USR, PAS);
// preparamos el comando sql 
			pstm = conx.prepareStatement(update_camino);
// SET origen=?,destino=?,capMaxima=?,estado=?,tiempo=?" + "WHERE id=?
			pstm.setInt(1, c.getOrigen());
			pstm.setInt(2, c.getDestino());
			pstm.setInt(3, c.getCapMaxima());
			pstm.setInt(4, c.estadoToInteger());
			pstm.setInt(5, c.getTiempoTransito());
			pstm.setInt(6, c.getID());
// ejecutamos la sentencia 
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
			 	   new ModificacionConfirmada();
			}
			
		} // capturamos las ecepciones , si las hay
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void eliminarCamino(Camino c) {

		Connection conx = null;
		PreparedStatement pstm = null;
		try {
// cargar el driver 
			Class.forName(DRIVER);
// obtenemos una coneccion  
			conx = DriverManager.getConnection(URL, USR, PAS);
// preparamos el comando sql 
			pstm = conx.prepareStatement(delete_camino);
// id,origen,destino,capMaxima,estado,tiempo) where id=?

			pstm.setInt(1, c.getID());
// ejecutamos la sentencia 
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
			 	   new BorradoConfirmado();
			}
		} // capturamos las ecepciones , si las hay
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public ArrayList<Camino> crearListadeCaminos() {
		
		ArrayList<Camino> listaCam = new ArrayList<>();

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			new VentanasError(e.getMessage());
			e.printStackTrace();
		}

		try (Connection conn = DriverManager.getConnection(URL, USR, PAS);
				Statement statement = conn.createStatement()) {

			String selectQuery = ver_caminos;
			boolean hasResultSet = statement.execute(selectQuery);

			if (hasResultSet) {
				ResultSet rs = statement.getResultSet();
				while (rs.next()) {
					Camino cam = new Camino();
					cam.setId(rs.getInt(1));
					cam.setOrigen(rs.getInt(2));
					cam.setDestino(rs.getInt(3));
					cam.setCapMaxima(rs.getInt(4));

					if (rs.getBoolean(5) == true) {
						cam.setEstado(TipoEstadoSucursal.OPERATIVA);
					} else {
						cam.setEstado(TipoEstadoSucursal.NO_OPERATIVA);
					}
					cam.setTiempodeTransito(rs.getInt(6));
					listaCam.add(cam);
				}
			}

			try {
				conn.close();
			} catch (SQLException e) {
				new VentanasError(e.getMessage());
				e.printStackTrace();
			}

		} catch (SQLException e) {
			new VentanasError(e.getMessage());

			e.printStackTrace();
		}

		return listaCam;
		
	}

	public void crearStock(Stock s) {
		Connection conx = null;
		PreparedStatement pstm = null;
		try {
// cargar el driver 
			Class.forName(DRIVER);
// obtenemos una coneccion  
			conx = DriverManager.getConnection(URL, USR, PAS);
// preparamos el comando sql 
			pstm = conx.prepareStatement(insert_stock);
// id_sucursal,id_producto,stock
			pstm.setInt(1, s.getIdSucursal());
			pstm.setInt(2, s.getIdProducto());
			pstm.setInt(3, s.getStock());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
			 	   new AltaConfirmada();}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void modificarStock(Stock s) {
		Connection conx = null;
		PreparedStatement pstm = null;
		try {
// cargar el driver 
			Class.forName(DRIVER);
// obtenemos una coneccion  
			conx = DriverManager.getConnection(URL, USR, PAS);
// preparamos el comando sql 
			pstm = conx.prepareStatement(update_stock);
// SET  id_producto =?, stock =?" + "WHERE id=?
			pstm.setInt(1, s.getIdProducto());
			pstm.setInt(2, s.getStock());
			pstm.setInt(3, s.getIdSucursal());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
			 	   new ModificacionConfirmada();}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void eliminarStock(Stock s) {
		Connection conx = null;
		PreparedStatement pstm = null;
		try {
// cargar el driver 
			Class.forName(DRIVER);
// obtenemos una coneccion  
			conx = DriverManager.getConnection(URL, USR, PAS);
// preparamos el comando sql 
			pstm = conx.prepareStatement(delete_stock);
//  id_sucursal=? id_producto=?
			pstm.setInt(1, s.getIdSucursal());
			pstm.setInt(2, s.getIdProducto());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
			 	   new BorradoConfirmado();}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	public void crearOrdenProvision(OrdenDeProvision op) {
		Connection conx = null;
		PreparedStatement pstm = null;
		Integer idGenerado = null;
		try {
// cargar el driver 
			Class.forName(DRIVER);
// obtenemos una coneccion  
			conx = DriverManager.getConnection(URL, USR, PAS);
// preparamos el comando sql 
			pstm = conx.prepareStatement(insert_ordenProvision, Statement.RETURN_GENERATED_KEYS);
// fechaOrden,idSucursalDestino,horasMaximo,estado
			pstm.setString(1, op.getFechaOrden().toString());
			pstm.setInt(2, op.getSucursalDestino().id());
			pstm.setInt(3, op.getHorasMaximo());
			pstm.setString(4, op.getEstadoString());
			int cantidad = pstm.executeUpdate();
			if (cantidad > 0) {
			 	   new AltaConfirmada();}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			ResultSet generatedKeys = pstm.getGeneratedKeys();
			if (generatedKeys.next()) {
				
					idGenerado = generatedKeys.getInt(1);
				
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (pstm != null)
				try {
					pstm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (conx != null)
				try {
					conx.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			op.setId(idGenerado);
			crearLista(op);
		}

	}

	public void crearLista(OrdenDeProvision op) {
		// primero obtenemos la lista de productos 
		HashMap<Integer, Integer> lista = new HashMap<>();
		lista = op.getListaProductosID();
		// luego obtenemos el id de la orden de pedido
		Integer idorden = op.getId();	
		Connection conx = null;
		PreparedStatement pstm = null;	
		try {
			// cargar el driver 
			Class.forName(DRIVER);
			// obtenemos una coneccion  
			conx = DriverManager.getConnection(URL, USR, PAS);
			for (Integer idproducto : lista.keySet()) {
				// preparamos el comando sql 
				pstm = conx.prepareStatement(insert_lista);
				// id_orden,id_producto,cantidad		 
				int cantidad = lista.get(idproducto);
				pstm.setInt(1,idorden);
				pstm.setInt(2,idproducto);
				pstm.setInt(3,cantidad);	
				pstm.executeUpdate();
			}
		} catch (ClassNotFoundException e) { e.printStackTrace(); }
		  catch (SQLException e) { e.printStackTrace(); }
		finally {
					if(pstm!=null) try { pstm.close(); }
								   catch (SQLException e) {e.printStackTrace(); }
					if(conx!=null) try { conx.close(); }
								   catch (SQLException e) { e.printStackTrace(); }
				} }
	
}
