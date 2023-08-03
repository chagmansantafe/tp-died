package UI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class App extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	App(){

	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setTitle("Menú 2");
    this.setSize(1000, 720); // Set your preferred size
    this.setLocationRelativeTo(null); // Center the frame on the screen
 
    //cambio icono ventana
    ImageIcon customIcon = new ImageIcon("C:/Users/valec/OneDrive/Desktop/UI_resources/LogoApp.png");
    this.setIconImage(customIcon.getImage());
	
    
    MenuPrincipal menu = new MenuPrincipal();
	this.setContentPane(menu.getContentPane());
    
	this.setVisible(true);
    
	}
}