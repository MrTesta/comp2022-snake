import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String snake = "images/head_right.png";

    private int dx = 30 ;
    private int dy = 0 ;
    private int x;
    private int y;
    private Image image;
    
    public Snake() {
        // onde a snake nasce
        ImageIcon ii = new ImageIcon(this.getClass().getResource(snake));
        image = ii.getImage();
        x = 300;
        y = 350;
    }
    
    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }
    
    public void setdx(int dx){
        this.dx=dx;
    }
    
    public void setdy(int dy){
        this.dy=dy;
    }
    
    public void trocarcabeca(String img){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(img));
        image = ii.getImage();
    }

}
