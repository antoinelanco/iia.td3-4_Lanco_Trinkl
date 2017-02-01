package iia.jeux.modele;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class PlateauGraph extends JFrame{
	
	public PlateauGraph(String NomJeu){
		super(NomJeu);
		
		WindowListener l = new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};
		
		addWindowListener(l);
		setSize(200,200);
		setVisible(true);
	}

}
