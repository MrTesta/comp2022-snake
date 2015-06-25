import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
public class Comida extends JPanel
{
   Comida(){
       
    }
    public Image getImage(){
       ImageIcon ii = new ImageIcon(this.getClass().getResource("images/fries.png"));
       return ii.getImage();
    }
}