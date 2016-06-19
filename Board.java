import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLayeredPane;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Board extends JComponent{
    private State model;
    private Font bk = new Font("Baby Kruffy", Font.PLAIN, 28);
    private Font bkLarge = new Font("Baby Kruffy", Font.PLAIN, 40);
    private BufferedImage img;

    public JButton[] snakeBody = new JButton[300];
    public JLabel score = new JLabel();
    public JLabel FPS = new JLabel();
    public JLabel speed = new JLabel();
    public JLabel pause = new JLabel("Hold on!");
    public JLabel die = new JLabel("Game Over :(");
    public ImageIcon food = new ImageIcon(Board.class.getResource("Apple-20.png"));
    public ImageIcon head = new ImageIcon(Board.class.getResource("head.png"));
    public ImageIcon body = new ImageIcon(Board.class.getResource("body.png"));
    public JButton foodButton = new JButton(food);
    public ImageIcon poison = new ImageIcon(Board.class.getResource("poison.png"));
    public ImageIcon bomb = new ImageIcon(Board.class.getResource("bomb.png"));
    public JButton poisonButton = new JButton(poison);
    public JButton bombButton = new JButton(bomb);
    public JLayeredPane area = new JLayeredPane();

    public Board(State aModel) {
        super();
        model = aModel;
        area.setLayout(null);
        area.setSize(new Dimension(522,522));
        area.setBorder(BorderFactory.createEmptyBorder());
        area.setBackground(Color.white);
        area.setLocation(new Point(27,27));
        for(int i = 0;i < 300;i++){
            if(i==0) {
                snakeBody[i] = new JButton(head);
                snakeBody[i].setDisabledIcon(head);
            }else{
                snakeBody[i] = new JButton(body);
                snakeBody[i].setDisabledIcon(body);
            }
            snakeBody[i].setEnabled(false);
            snakeBody[i].setSize(20, 20);
            snakeBody[i].setBorder(BorderFactory.createEmptyBorder());
        }
        this.add(area);
        setSize(new Dimension(800,600));
        try {
            img = ImageIO.read(Board.class.getResource("/background.jpg"));
        } catch(IOException e) {
            e.printStackTrace();
        }
        setVisible(true);
        requestFocusInWindow();
        setFocusable(true);
        initViews();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 27, 27, 522, 522, this.area);
    }

    void drawSnakeBody(){
       // System.out.println("drawSnakeBody");
        if(model.snakeBody.isEmpty()) return;
       // System.out.println("size: "+model.snakeBody.size());
        for(int i=0;i<model.snakeBody.size();++i){
            try{
                snakeBody[i].setLocation(enlarge((Point)model.snakeBody.toArray()[i]));
                area.add(snakeBody[i], new Integer(0), 0);
            } catch (Exception e){
              //  System.out.println("Error occurs: "+ i+" , "+e.toString());
            }
        }
    }

    void updateFood(){
        foodButton.setEnabled(false);
//        System.out.println("Foodx:"+model.food.getX());
//        System.out.println("Foody:"+model.food.getY());
        foodButton.setLocation(enlarge(model.food));
        foodButton.setSize(20, 20);
        foodButton.setDisabledIcon(food);
        foodButton.setBorder(BorderFactory.createEmptyBorder());
        area.add(foodButton,new Integer(0), 0);
    }

    void updatePosion(){
        poisonButton.setEnabled(false);
        poisonButton.setLocation(enlarge(model.poison));
        poisonButton.setSize(20, 20);
        poisonButton.setDisabledIcon(poison);
        poisonButton.setBorder(BorderFactory.createEmptyBorder());
        area.add(poisonButton,new Integer(0), 0);
    }
    void updateBomb(){
        bombButton.setEnabled(false);
        bombButton.setLocation(enlarge(model.bomb));
        bombButton.setSize(20, 20);
        bombButton.setDisabledIcon(bomb);
        bombButton.setBorder(BorderFactory.createEmptyBorder());
        area.add(bombButton,new Integer(0), 0);
    }

    void printScore(){
        score.setFont(bk);
        score.setLocation(new Point(600,140));
        score.setForeground(Color.DARK_GRAY);
        score.setSize(200, 30);
        score.setText("Score : "+model.score);
        this.add(score);
        score.repaint();
    }

    void printSpeed(){
        speed.setFont(bk);
        speed.setLocation(new Point(600,100));
        speed.setForeground(Color.DARK_GRAY);
        speed.setSize(200, 30);
        speed.setText("Speed : "+model.speed);
        this.add(speed);
        speed.repaint();
    }

    void printFPS(){
        FPS.setFont(bk);
        FPS.setLocation(new Point(600,60));
        FPS.setForeground(Color.DARK_GRAY);
        FPS.setSize(200, 30);
        FPS.setText("FPS : "+ model.FPS);
        this.add(FPS);
        FPS.repaint();
    }


    void printDie(){
        die.setFont(bkLarge);
        die.setLocation(new Point(150,160));
        die.setForeground(new Color(247,18,60));
        die.setSize(300, 200);
        die.setVisible(false);
        area.add(die, new Integer(1), 0);

    }

    void printPause(){
        pause.setFont(bkLarge);
        pause.setLocation(new Point(150,160));
        pause.setForeground(new Color(17,153,250));
        pause.setSize(300, 200);
        pause.setVisible(false);
        area.add(pause,new Integer(1), 0);
    }

    void initViews(){
        printScore();
        printSpeed();
        printFPS();
        updateFood();
        printPause();
        printDie();
        drawSnakeBody();
        //restart
    }

    Point enlarge (Point p){
//        System.out.println("x:"+p.getX());
//        System.out.println("y:"+p.getY());
        double newX =  p.getX()*20+1;
        double newY =  p.getY()*20+1;
        Point newP = new Point((int)newX,(int)newY);
        return newP;
    }
}
