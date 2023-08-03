package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AltaStock extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AltaStock() {
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setTitle("Alta Producto");
	    this.setSize(500, 500); // Set your preferred size
	    this.setLocationRelativeTo(null); // Center the frame on the screen
		
		
	    //background panel (extiende JPanel)
	    Background backgroundPanel = new Background("C:/Users/valec/OneDrive/Desktop/UI_resources/Background.jpg");
	    this.setContentPane(backgroundPanel);
	    
	    
	    JPanel panel = new JPanel(new GridBagLayout());
	    panel.setOpaque(false);
	    
	    GridBagConstraints gbc = new GridBagConstraints();
	    
	    JLabel nombreLabel = new JLabel("Nombre del producto:");
	    nombreLabel.setForeground(Color.WHITE);
	    gbc.gridx = 0; 
	    gbc.gridy = 0;
	    gbc.gridwidth = 2;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.insets = new Insets(10, 10, 0, 10); 
	    panel.add(nombreLabel,gbc);
	    
	    JFormattedTextField nombre = new JFormattedTextField();
	    Dimension preferredSize = new Dimension(nombre.getPreferredSize().width, 30); 
	    nombre.setPreferredSize(preferredSize);
	    nombre.setForeground(Color.WHITE);
	    nombre.setOpaque(false);
	    nombre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 1;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    panel.add(nombre,gbc);
	    
	    JLabel cantidadLabel = new JLabel("Cantidad de producto:");
	    cantidadLabel.setForeground(Color.WHITE);
	    gbc.gridy = 2;
	    gbc.insets = new Insets(10, 10, 0, 10);
	    panel.add(cantidadLabel,gbc);
	    
	    JTextField cantidad = new JTextField();
	    preferredSize = new Dimension(cantidad.getPreferredSize().width, 30); 
	    cantidad.setPreferredSize(preferredSize);
	    cantidad.setForeground(Color.WHITE);
	    cantidad.setOpaque(false);
	    cantidad.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1)); 
	    gbc.gridy = 3;
	    gbc.insets = new Insets(1, 10, 10, 10);
	    panel.add(cantidad,gbc);
	    
	    JButton cancelar = new JButton("Cancelar");
	    buttonConfig(cancelar);
	    gbc.gridy = 4;
	    gbc.gridwidth = 1;
	    gbc.insets = new Insets(10, 10, 10, 10);
	    panel.add(cancelar,gbc);
	    
	    JButton confirmar = new JButton("Confirmar");
	    buttonConfig(confirmar);
	    gbc.gridx = 1;
	    panel.add(confirmar,gbc);
	    
	    gbc.gridx = 0; 
	    gbc.gridy = 0;
	    gbc.gridwidth = 1;
	    gbc.fill = GridBagConstraints.BOTH; 
	    gbc.insets = new Insets(0, 0, 0, 0); 
	    backgroundPanel.add(panel,gbc);
	    
	    confirmar.addActionListener(e -> {
	    	//agarrar los valores IMP VALIDACION PREVIA TODO
	    	//enviar los valores a metodo en logica que se encargue de armar el objeto
	    	//GestorSucursales.altaSucursal(id,nombre,estado,apertura,cierre)
	    	
	    });
	    
	    cancelar.addActionListener(e -> {
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
