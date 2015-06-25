import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
/**
 * Write a description of class Node here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Node extends JPanel
{
    // instance variables - replace the example below with your own
    private Object x;
    private Node proximo;
    private int xx;
    private int y;

    /**
     * Constructor for objects of class Node
     */
    public Node()
    {

    }
    
    public Node(String args)
    {
        this.x = args;
    }

    public void setX(Object _x){
        this.x = _x;
    }
    
    public int getX(){
        return xx;
    }
    
    public int getY(){
        return y;
    }
    
    public void setX(int xx){
        this.xx = xx;
    }
    
    public void setY(int y){
        this.y = y;
    }
    public void setProximo(Node _proximo){
        this.proximo = _proximo;
    }
    
    public Node getProximo(){
        return this.proximo;
    }
}