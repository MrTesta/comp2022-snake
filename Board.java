import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    
    private boolean isPlaying = false;

    private Font font;
    
    private Snake head;
    private Node inicio;
    private Comida food;
    private int xcomida;
    private int ycomida;
    private Random gerador = new Random();
       
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);
        
        head = new Snake();
        add(head);
        food = new Comida();
        add(food);
        
        timer = new Timer(150, this);
        timer.start();
        
        //ramdom comida
        
       
        xcomida = gerador.nextInt(750);
        ycomida = gerador.nextInt(550);
    }
    
    
    
     public Node inserirFinal(Node _node) {
        if (isEmpty()) {
            inicio = _node;
        } else {
            Node aux = inicio;
            while (aux.getProximo() != null) {
                aux = aux.getProximo();
            }
            aux.setProximo(_node);
        }
         return _node;   
        }
    
    
     private boolean isEmpty() {
        if (inicio == null) {
            return true;
        } else {
            return false;
        }
    }


    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        if ((head.getX() >=0 && head.getX() <=800) && (head.getY() >=0 && head.getY() <=600)){
        //condicao de capturar a comida na margem de erro de x e y de 15
        if (head.getX() >= xcomida-15 && head.getX() <= xcomida+15 && head.getY() >= ycomida-15 && head.getY() <= ycomida+15){
            xcomida = gerador.nextInt(750);
            ycomida = gerador.nextInt(550);
            score.addScore(5);
            add(inserirFinal(new Node("corpo")));
        }
        Graphics2D g2d = (Graphics2D)g;
        
        g2d.drawImage(head.getImage(), head.getX(), head.getY(), this);
        g2d.drawImage(food.getImage(), xcomida, ycomida, this);
        
         if (inicio != null){
       Node _node = inicio; 
       ImageIcon ii = new ImageIcon(this.getClass().getResource("images/body.png"));
       Image corpo =  ii.getImage();
       g2d.drawImage(corpo, _node.getX(), _node.getY(), this);
       while(_node.getProximo() != null){
        _node = _node.getProximo();    
        g2d.drawImage(corpo, _node.getX(), _node.getY(), this);
        }
      }
        
     }else{
         if(isPlaying){
            isPlaying = false;
            Graphics2D g2d = (Graphics2D) g;
            try{
                File file = new File("fonts/VT323-Regular.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, file);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                font = font.deriveFont(Font.PLAIN,40);
                g2d.setFont(font);
            }catch (Exception e){
                System.out.println(e.toString());
            }   
            g2d.drawString("Game Over!" + this.score, 300, 300);
        }
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }


    public void paintIntro(Graphics g) {
        if(isPlaying){
            isPlaying = false;
            Graphics2D g2d = (Graphics2D) g;
            try{
                File file = new File("fonts/VT323-Regular.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, file);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                font = font.deriveFont(Font.PLAIN,40);
                g2d.setFont(font);
            }catch (Exception e){
                System.out.println(e.toString());
            }   
            g2d.drawString("S N A K E: " + this.score, 300, 300);
        }
    }
    
    public void actionPerformed(ActionEvent e) { 
        if (inicio != null){
       Node _node = inicio;
       //x,y anterior
       int a2 = _node.getX();
       int b2 = _node.getY();
       int a = head.getX();
       int b = head.getY();
       _node.setX(a);
       _node.setY(b);
       a = a2;
       b = b2;
       while(_node.getProximo() != null){
        _node = _node.getProximo();
        a2 = _node.getX();
        b2 = _node.getY();        
        _node.setX(a);
        _node.setY(b);
        a = a2;
        b = b2;
        }
    }
        head.move();
        repaint();  
    }
    
    
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                    
                    break;
                    
                case KeyEvent.VK_LEFT:
                    head.trocarcabeca("images/head_right.png");
                    head.setdx(-25); head.setdy(0);
                    break;
                    
                case KeyEvent.VK_RIGHT:
                     head.trocarcabeca("images/head_left.png");
                    head.setdx(25); head.setdy(0);
                    break;
                    
                case KeyEvent.VK_UP:
                    head.trocarcabeca("images/head_up.png");
                    head.setdx(0); head.setdy(-25);
                    
                    break;
                    
                case KeyEvent.VK_DOWN:
                     head.trocarcabeca("images/head_down.png");
                    head.setdx(0); head.setdy(25);
                    break;
            }
            
        }
    }
    
}