package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import TP.Camino;
import TP.DarBaja;
import TP.EditarElemento;
import TP.Producto;
import TP.Stock;
import TP.Sucursal;

public class Editar extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Editar(Sucursal sucursal){
	
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Editar");
	    this.setSize(500, 500); // Set your preferred size
	    this.setLocationRelativeTo(null); // Center the frame on the screen
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    Background backgroundPanel = new Background("C:/Users/valec/OneDrive/Desktop/UI_resources/Background.jpg");
	    this.setContentPane(backgroundPanel);
		
	    JLabel aviso = new JLabel("ID: " + sucursal.id().toString());
	    aviso.setForeground(Color.WHITE);
	    aviso.setHorizontalAlignment(SwingConstants.CENTER);
	    aviso.setVerticalAlignment(SwingConstants.CENTER);
	    aviso.setFont(new Font("Antipasto", Font.PLAIN, 20)); 
	    
	    gbc.gridx = 0; 
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.anchor = GridBagConstraints.CENTER; 
	    gbc.insets = new Insets(10, 10, 10, 10); 
	    backgroundPanel.add(aviso,gbc);
	    
	    
	    JLabel nombreLabel = new JLabel("Nombre de la sucursal:");
	    nombreLabel.setForeground(Color.WHITE);
	    gbc.gridy = 1;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(nombreLabel,gbc);
	    
	    JTextField nombre = new JTextField(sucursal.getNombre());
	    Dimension preferredSize = new Dimension(nombre.getPreferredSize().width, 30); 
	    nombre.setPreferredSize(preferredSize);
	    nombre.setForeground(Color.WHITE);
	    nombre.setOpaque(false);
	    nombre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 2;
	    gbc.insets = new Insets(10, 10, 10, 10);
	    backgroundPanel.add(nombre,gbc);
	    
	    JCheckBox estado = new JCheckBox("Operativa");
	    if(sucursal.estadoToString() == "Operativa") {
	    	estado.setSelected(true); // Set the initial state (checked or unchecked)
		    estado.setEnabled(true); // Set if the checkbox is enabled or disabled (true = enabled, false = disabled)
	    }
	    else {
	    	estado.setSelected(false); // Set the initial state (checked or unchecked)
		    estado.setEnabled(true); // Set if the checkbox is enabled or disabled (true = enabled, false = disabled)
	    }
	    estado.setForeground(Color.WHITE);
	    estado.setOpaque(false);
	    gbc.gridy = 3;
	    backgroundPanel.add(estado,gbc);
	    
	    JLabel aperturaLabel = new JLabel("Horario de apertura:");
	    aperturaLabel.setForeground(Color.WHITE);
	    gbc.gridy = 4;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(aperturaLabel,gbc);
	    
	    JTextField apertura = new JTextField(sucursal.getHorarioApertura());
	    preferredSize = new Dimension(apertura.getPreferredSize().width, 30); 
	    apertura.setPreferredSize(preferredSize);
	    apertura.setForeground(Color.WHITE);
	    apertura.setOpaque(false);
	    apertura.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 5;
	    gbc.insets = new Insets(10, 10, 10, 10);
	    backgroundPanel.add(apertura,gbc);
	    
	    JLabel cierreLabel = new JLabel("Horario de ciere:");
	    cierreLabel.setForeground(Color.WHITE);
	    gbc.insets = new Insets(10, 10, 0, 10);
	    gbc.gridy = 6;
	    backgroundPanel.add(cierreLabel,gbc);
	    
	    JTextField cierre = new JTextField(sucursal.getHorarioCierre());
	    preferredSize = new Dimension(cierre.getPreferredSize().width, 30); 
	    cierre.setPreferredSize(preferredSize);
	    cierre.setForeground(Color.WHITE);
	    cierre.setOpaque(false);
	    cierre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 7;
	    gbc.insets = new Insets(10, 10, 10, 10);
	    backgroundPanel.add(cierre,gbc);
	
	    JButton volver = new JButton("Volver");
		buttonConfig(volver);
		gbc.gridy = 8;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 15, 15); 
		backgroundPanel.add(volver,gbc);
			
		
		JButton aceptar = new JButton("Aceptar");
		buttonConfig(aceptar);
		gbc.gridx = 1;
		backgroundPanel.add(aceptar,gbc);
		
	    
	    volver.addActionListener(e -> {
		       this.dispose();
		    });
	    
	    aceptar.addActionListener(e -> {
	    	   new EditarElemento(sucursal);
		       this.dispose();
		    });
	    
	    this.setVisible(true);

	}
    
	Editar(Camino camino){
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Editar");
	    this.setSize(500, 500); // Set your preferred size
	    this.setLocationRelativeTo(null); // Center the frame on the screen
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    Background backgroundPanel = new Background("C:/Users/valec/OneDrive/Desktop/UI_resources/Background.jpg");
	    this.setContentPane(backgroundPanel);
		
	    JLabel aviso = new JLabel("ID: " + camino.getID().toString());
	    aviso.setForeground(Color.WHITE);
	    aviso.setHorizontalAlignment(SwingConstants.CENTER);
	    aviso.setVerticalAlignment(SwingConstants.CENTER);
	    aviso.setFont(new Font("Antipasto", Font.PLAIN, 20)); 
	    
	    gbc.gridx = 0; 
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.anchor = GridBagConstraints.CENTER; 
	    gbc.insets = new Insets(10, 10, 10, 10); 
	    backgroundPanel.add(aviso,gbc);
	    
	    
	    JLabel origenLabel = new JLabel("ID de sucursal de origen:");
	    origenLabel.setForeground(Color.WHITE);
	    gbc.gridy = 1;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(origenLabel,gbc);
	    
	    JTextField idOrigen = new JTextField(camino.getOrigen().toString());
	    Dimension preferredSize = new Dimension(idOrigen.getPreferredSize().width, 30); 
	    idOrigen.setPreferredSize(preferredSize);
	    idOrigen.setForeground(Color.WHITE);
	    idOrigen.setOpaque(false);
	    idOrigen.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 2;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    backgroundPanel.add(idOrigen,gbc);
	    
	    JLabel destinoLabel = new JLabel("ID de sucursal de destino:");
	    destinoLabel.setForeground(Color.WHITE);
	    gbc.gridy = 3;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(destinoLabel,gbc);
	    
	    JTextField idDestino = new JTextField(camino.getDestino().toString());
	    preferredSize = new Dimension(idDestino.getPreferredSize().width, 30); 
	    idDestino.setPreferredSize(preferredSize);
	    idDestino.setForeground(Color.WHITE);
	    idDestino.setOpaque(false);
	    idDestino.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 4;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    backgroundPanel.add(idDestino,gbc);
	    
	    JLabel tiempoTransitoLabel = new JLabel("Tiempo de transito:");
	    tiempoTransitoLabel.setForeground(Color.WHITE);
	    gbc.gridy = 5;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(tiempoTransitoLabel,gbc);
	    
	    JTextField tiempoTransito = new JFormattedTextField(camino.getTiempoTransito());
	    preferredSize = new Dimension(tiempoTransito.getPreferredSize().width, 30); 
	    tiempoTransito.setPreferredSize(preferredSize);
	    tiempoTransito.setForeground(Color.WHITE);
	    tiempoTransito.setOpaque(false);
	    tiempoTransito.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 6;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    backgroundPanel.add(tiempoTransito,gbc);
	    
	    JLabel capMaximaLabel = new JLabel("Capacidad maxima de carga:");
	    capMaximaLabel.setForeground(Color.WHITE);
	    gbc.gridy = 7;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(capMaximaLabel,gbc);
	    
	    JTextField capMaxima = new JFormattedTextField(camino.getCapMaxima());
	    preferredSize = new Dimension(capMaxima.getPreferredSize().width, 30); 
	    capMaxima.setPreferredSize(preferredSize);
	    capMaxima.setForeground(Color.WHITE);
	    capMaxima.setOpaque(false);
	    capMaxima.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 8;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    backgroundPanel.add(capMaxima,gbc);
	    
	    JCheckBox estado = new JCheckBox("Operativa");
	    if(camino.estadoToString() == "Operativo") {
	    	estado.setSelected(true); // Set the initial state (checked or unchecked)
		    estado.setEnabled(true); // Set if the checkbox is enabled or disabled (true = enabled, false = disabled)
	    }
	    else {
	    	estado.setSelected(false); // Set the initial state (checked or unchecked)
		    estado.setEnabled(true); // Set if the checkbox is enabled or disabled (true = enabled, false = disabled)
	    }
	    estado.setForeground(Color.WHITE);
	    estado.setOpaque(false);
	    gbc.gridy = 9;
	    gbc.insets = new Insets(10, 10, 10, 10);
	    backgroundPanel.add(estado,gbc);
	
	    JButton volver = new JButton("Volver");
		buttonConfig(volver);
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 15, 15); 
		backgroundPanel.add(volver,gbc);
			
		
		JButton aceptar = new JButton("Aceptar");
		buttonConfig(aceptar);
		gbc.gridx = 1;
		backgroundPanel.add(aceptar,gbc);
		
	    
	    volver.addActionListener(e -> {
		       this.dispose();
		    });
	    
	    aceptar.addActionListener(e -> {
	    	   new EditarElemento(camino);
		       this.dispose();
		    });
	    
	    this.setVisible(true);

		
	}
	
	Editar(Producto producto){
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Editar");
	    this.setSize(500, 500); // Set your preferred size
	    this.setLocationRelativeTo(null); // Center the frame on the screen
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    Background backgroundPanel = new Background("C:/Users/valec/OneDrive/Desktop/UI_resources/Background.jpg");
	    this.setContentPane(backgroundPanel);
		
	    JLabel aviso = new JLabel("Nombre: " + producto.getNombre());
	    aviso.setForeground(Color.WHITE);
	    aviso.setHorizontalAlignment(SwingConstants.CENTER);
	    aviso.setVerticalAlignment(SwingConstants.CENTER);
	    aviso.setFont(new Font("Antipasto", Font.PLAIN, 20)); 
	    
	    gbc.gridx = 0; 
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.anchor = GridBagConstraints.CENTER; 
	    gbc.insets = new Insets(10, 10, 10, 10); 
	    backgroundPanel.add(aviso,gbc);
	    
	    
	    JLabel descripcionLabel = new JLabel("Descripcion del producto:");
	    descripcionLabel.setForeground(Color.WHITE);
	    gbc.gridy = 2;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(descripcionLabel,gbc);
	    
	    JTextField descripcion = new JTextField(producto.descripcion());
	    Dimension preferredSize = new Dimension(descripcion.getPreferredSize().width, 30); 
	    descripcion.setPreferredSize(preferredSize);
	    descripcion.setForeground(Color.WHITE);
	    descripcion.setOpaque(false);
	    descripcion.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 3;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    backgroundPanel.add(descripcion,gbc);
	    
	    JLabel precioLabel = new JLabel("Precio unitario del producto:");
	    precioLabel.setForeground(Color.WHITE);
	    gbc.gridy = 4;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(precioLabel,gbc);
	    
	    JTextField precio = new JTextField(producto.getPrecioUnitario().toString());
	    preferredSize = new Dimension(precio.getPreferredSize().width, 30); 
	    precio.setPreferredSize(preferredSize);
	    precio.setForeground(Color.WHITE);
	    precio.setOpaque(false);
	    precio.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 5;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    backgroundPanel.add(precio,gbc);
	    
	    JLabel pesoLabel = new JLabel("Peso del producto:");
	    pesoLabel.setForeground(Color.WHITE);
	    gbc.gridy = 6;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    backgroundPanel.add(pesoLabel,gbc);
	    
	    JTextField peso = new JFormattedTextField(producto.getPeso());
	    preferredSize = new Dimension(precio.getPreferredSize().width, 30); 
	    peso.setPreferredSize(preferredSize);
	    peso.setForeground(Color.WHITE);
	    peso.setOpaque(false);
	    peso.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 7;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    backgroundPanel.add(peso,gbc);
	
	    JButton volver = new JButton("Volver");
		buttonConfig(volver);
		gbc.gridy = 10;
		gbc.gridwidth = 1;
		gbc.insets = new Insets(15, 15, 15, 15); 
		backgroundPanel.add(volver,gbc);
			
		
		JButton aceptar = new JButton("Aceptar");
		buttonConfig(aceptar);
		gbc.gridx = 1;
		backgroundPanel.add(aceptar,gbc);
		
	    
	    volver.addActionListener(e -> {
		       this.dispose();
		    });
	    
	    aceptar.addActionListener(e -> {
	    	   new EditarElemento(producto);
		       this.dispose();
		    });
	    
	    this.setVisible(true);

		
	}
	
	Editar(Stock stock){
		
		/*
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Editar");
	    this.setSize(500, 500); // Set your preferred size
	    this.setLocationRelativeTo(null); // Center the frame on the screen
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    Background backgroundPanel = new Background("C:/Users/valec/OneDrive/Desktop/UI_resources/Background.jpg");
	    this.setContentPane(backgroundPanel);
		
	    JLabel aviso = new JLabel("Cantidad de stock:");
	    aviso.setForeground(Color.WHITE);
	    aviso.setHorizontalAlignment(SwingConstants.CENTER);
	    aviso.setVerticalAlignment(SwingConstants.CENTER);
	    aviso.setFont(new Font("Antipasto", Font.PLAIN, 20)); 
	    
	    gbc.gridx = 0; 
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.anchor = GridBagConstraints.CENTER; 
	    gbc.insets = new Insets(10, 10, 10, 10); 
	    backgroundPanel.add(aviso,gbc);
	    
	    JTextField descripcion = new JTextField(stock.getStockProducto());
	    Dimension preferredSize = new Dimension(descripcion.getPreferredSize().width, 30); 
	    descripcion.setPreferredSize(preferredSize);
	    descripcion.setForeground(Color.WHITE);
	    descripcion.setOpaque(false);
	    descripcion.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 1;
	    backgroundPanel.add(descripcion,gbc);
	
	    JButton volver = new JButton("Volver");
		buttonConfig(volver);
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbc.insets = new Insets(15, 15, 15, 15); 
		backgroundPanel.add(volver,gbc);
			
		
		JButton aceptar = new JButton("Aceptar");
		buttonConfig(aceptar);
		gbc.gridx = 1;
		backgroundPanel.add(aceptar,gbc);
		
	    
	    volver.addActionListener(e -> {
		       this.dispose();
		    });
	    
	    aceptar.addActionListener(e -> {
	    	   new EditarElemento(stock);
		       this.dispose();
		    });
	    
	    this.setVisible(true);
		*/
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Baja");
	    this.setSize(500, 500); // Set your preferred size
	    this.setLocationRelativeTo(null); // Center the frame on the screen
	    
	    Background backgroundPanel = new Background("C:/Users/valec/OneDrive/Desktop/UI_resources/Background.jpg");
	    this.setContentPane(backgroundPanel);
		
		GridBagConstraints gbc = new GridBagConstraints();
	    
	    JLabel aviso = new JLabel("Cantidad de stock:");
	    aviso.setForeground(Color.WHITE);
	    aviso.setHorizontalAlignment(SwingConstants.CENTER);
	    aviso.setVerticalAlignment(SwingConstants.CENTER);
	    aviso.setFont(new Font("Antipasto", Font.PLAIN, 20)); 
	
	    gbc.gridx = 0; 
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.anchor = GridBagConstraints.CENTER; 
	    gbc.insets = new Insets(10, 10, 10, 10); 
	    backgroundPanel.add(aviso,gbc);
	    
	    JTextField cantidad = new JTextField(stock.getStockProducto().toString());
	    Dimension preferredSize = new Dimension(cantidad.getPreferredSize().width, 30); 
	    cantidad.setPreferredSize(preferredSize);
	    cantidad.setForeground(Color.WHITE);
	    cantidad.setOpaque(false);
	    cantidad.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.insets = new Insets(10, 15, 10, 15); 
	    gbc.gridy = 1;
	    backgroundPanel.add(cantidad,gbc);
	    
	    JButton volver = new JButton("Volver");
	  		buttonConfig(volver);
	    
	    gbc.gridy = 2;
	    gbc.weightx = 1;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(15, 15, 15, 15); 
	    backgroundPanel.add(volver,gbc);
	
	    
	    volver.addActionListener(e -> {
		       this.dispose();
		    });
	    		
		JButton aceptar = new JButton("Aceptar");
		buttonConfig(aceptar);
		
		gbc.gridx = 1;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.anchor = GridBagConstraints.CENTER;  
	    this.getContentPane().add(aceptar,gbc);
	    
	    aceptar.addActionListener(e -> {
	    	   new DarBaja(stock);
		       this.dispose();
		    });
	    
	    this.setVisible(true);

		
	}
	
	private void buttonConfig(JButton button1) {
		 button1.setOpaque(false);
	 button1.setFont(new Font("Code Light", Font.PLAIN, 13));
	 button1.setForeground(Color.WHITE);
	 button1.setBackground(new Color(0,0,0,70));
	 button1.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	 button1.setPreferredSize(new Dimension(180, 50));
	 button1.setFocusable(false);
	
	 button1.addMouseListener(new MouseAdapter() {
	     @Override
	     public void mouseEntered(MouseEvent e) {
	     	button1.setOpaque(true);
	         button1.setBackground(new Color(60,80,91,255));
	         button1.setForeground(Color.WHITE);
	         button1.setBorder(BorderFactory.createLineBorder(Color.white, 2));
	         button1.setFont(new Font("Code Light", Font.PLAIN, 14));
	     	}
	
	     @Override
	     public void mouseExited(MouseEvent e) {
	     	button1.setOpaque(false);
	         button1.setForeground(Color.WHITE);
	         button1.setBorder(BorderFactory.createLineBorder(Color.white, 1));
	         button1.setFont(new Font("Code Light", Font.PLAIN, 13));
	     	}
	 	});
 
	}

}
