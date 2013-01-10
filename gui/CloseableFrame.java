import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import javax.swing.event.*;

class CloseableFrame extends JFrame{
    public CloseableFrame(){
	initialize();
    }
    
    
    public CloseableFrame(String title){
	super(title);
	initialize();
    }
    
    public void initialize(){
	addWindowListener( new WindowAdapter(){
		public void windowClosing(WindowEvent event){
		    System.exit(0);
		}          
		
            });
	    
    }
}