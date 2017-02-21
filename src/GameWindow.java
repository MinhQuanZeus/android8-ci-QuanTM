import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by QuanT on 2/19/2017.
 */
public class GameWindow extends Frame{

    private static final int SCREEN_WIDTH = 400;
    private static final int SCREEN_HIGHT = 600;
    private static final int SPEED = 10;

    Image backgroundImage;
    Image planeImage;
    Image bombImage;
    private int planeX = (400-25)/2;
    private int planeY = 600-44;
    private boolean isKeyUp = false;
    private boolean iskeyDown = false;
    private boolean isKeyLeft = false;
    private boolean isKeyRight = false;

    BufferedImage backBufferImage;
    private Graphics backGraphics;

    Thread thread;



    public GameWindow(){
        setVisible(true);
        setSize(400,600);
        setResizable(false);

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
            }

        });



        //1: load image
        backgroundImage = loadImageFromRes("background.png");
        planeImage = loadImageFromRes("plane4.png");
        bombImage = loadImageFromRes("bomb.png");

        //2: draw image
      //  update(getGraphics());
        repaint();


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
//                switch (e.getKeyCode()){
//                    case KeyEvent.VK_RIGHT:
//                        //TODO: move plane to right
//                        planeX+=SPEED;
//                        break;
//                    case KeyEvent.VK_LEFT:
//                        //TODO: move plane to Left
//                        planeX-=SPEED;
//                        break;
//                    case KeyEvent.VK_UP:
//                        //move plane to up
//                        planeY-=SPEED;
//                        break;
//                    case KeyEvent.VK_DOWN:
//                        planeY+=SPEED;
//                        break;
//
//                }
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        //press right key
                        isKeyRight=true;
                        break;
                    case KeyEvent.VK_LEFT:
                        //press left key
                        isKeyLeft = true;
                        break;
                    case KeyEvent.VK_UP:
                        //press up key
                        isKeyUp = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        //press down key
                        iskeyDown = true;
                        break;
                }

                movePlane();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()){
                    case KeyEvent.VK_RIGHT:
                        //Released Right key
                        isKeyRight=false;
                        break;
                    case KeyEvent.VK_LEFT:
                        //Released Left key
                        isKeyLeft = false;
                        break;
                    case KeyEvent.VK_UP:
                        //Released Up key
                        isKeyUp = false;
                        break;
                    case KeyEvent.VK_DOWN:
                        //Released Down key
                        iskeyDown = false;
                        break;
                }
            }
        });


        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try{
                        Thread.sleep(5);
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    repaint();
                }
            }
        });


        backBufferImage = new BufferedImage(SCREEN_WIDTH, SCREEN_HIGHT, BufferedImage.TYPE_INT_ARGB);
        backGraphics = backBufferImage.getGraphics();
        thread.start();
    }


    private void movePlane(){
        //move plane to right
        if(isKeyRight && (planeX + 10)< (SCREEN_WIDTH - 50)){
            planeX+=SPEED;
        }
        //move plane to left
        if(isKeyLeft && (planeX - 10)> -5){
            planeX-=SPEED;
        }
        //move plane to up
        if(isKeyUp && (planeY - 10)>20){
            planeY-=SPEED;
        }
        //move plane to down
        if(iskeyDown && (planeY + 10)<(SCREEN_HIGHT-35)){
            planeY+=SPEED;
        }

    }


    private Image loadImageFromRes(String url){
        try {
            Image image = ImageIO.read(new File("resources/"+url));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void update(Graphics g) {
        if(backBufferImage!=null) {
            backGraphics.drawImage(backgroundImage, 0, 0, SCREEN_WIDTH, SCREEN_HIGHT, null);
            backGraphics.drawImage(planeImage, planeX, planeY, 50, 44, null);
            //     g.drawImage(bombImage,50,100,10,10,null);
            g.drawImage(backBufferImage, 0, 0, null);
        }
    }
}
