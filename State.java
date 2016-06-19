import java.util.*;
import java.awt.*;
import javax.sound.sampled.*;


public class State {
    //model
    public int score;
    public int FPS;
    public int speed;
    public int headDir;
    public boolean running;
    public boolean die;
    public boolean pause;
    private boolean posion;
    public Point food;
    public Point poison = new Point(1000,1000);
    public Point bomb = new Point(1000,1000);
    public Board view;
    public Deque <Point> snakeBody;
    private  AudioInputStream ais;
    private Clip clip;
    public State(int inFPS, int inSpeed){
        init(inFPS, inSpeed);
    }
    void addView(Board bView){
        view = bView;
        view.initViews();
    }
    void updateView(){
        view.repaint();

    }
    void init(int inFPS, int inSpeed){
        score = 0;
        FPS = inFPS;
        speed = inSpeed;
        running = true;
        die = false;
        pause = false;
        posion = false;
        setSnakeBody();
        initFood();
    }
    void setSnakeBody(){
        headDir = radDir(System.nanoTime());//random
        Point pHead = radP(System.nanoTime(),System.nanoTime());
        snakeBody = new ArrayDeque();

        switch(headDir){
            case 0:
                while(pHead.getY() + 2 > 25) {
                    pHead = radP(System.nanoTime(), System.nanoTime());
                }
                snakeBody.add(pHead);
                snakeBody.add(new Point(pHead.x,pHead.y+1));
                snakeBody.add(new Point(pHead.x,pHead.y+2));
                break;
            case 1://down
                while(pHead.getY()  - 2 <0) {
                    pHead = radP(System.nanoTime(), System.nanoTime());
                }
                snakeBody.add(pHead);
                snakeBody.add(new Point(pHead.x,pHead.y-1));
                snakeBody.add(new Point(pHead.x,pHead.y-2));
                break;
            case 2:
                while(pHead.getX() + 2 > 25) {
                    pHead = radP(System.nanoTime(), System.nanoTime());
                }
                snakeBody.add(pHead);
                snakeBody.add(new Point(pHead.x+1,pHead.y));
                snakeBody.add(new Point(pHead.x+2,pHead.y));
                break;

            case 3:
                while(pHead.getX() - 2 < 0) {
                    pHead = radP(System.nanoTime(), System.nanoTime());
                }
                snakeBody.add(pHead);
                snakeBody.add(new Point(pHead.x-1,pHead.y));
                snakeBody.add(new Point(pHead.x-2,pHead.y));
                break;
        }
    }

    void feedFood() { //random put food
        initFood();
        view.updateFood();
    }

    void initFood(){
        food = radP(System.nanoTime(),System.nanoTime());
        while(snakeBody.contains(food)||food.equals(poison)||food.equals(bomb)){
            food = radP(System.nanoTime(),System.nanoTime());
        }

    }

    void generatePoison() {
        poison = radP(System.nanoTime(),System.nanoTime());
        while(snakeBody.contains(poison)|| poison.equals(food) || poison.equals(bomb) ){
            poison = radP(System.nanoTime(),System.nanoTime());
        }
        view.updatePosion();

    }
    void generateBomb() {

        bomb = radP(System.nanoTime(),System.nanoTime());
        while(snakeBody.contains(bomb)|| bomb.equals(food)||bomb.equals(poison)){
            bomb = radP(System.nanoTime(),System.nanoTime());
        }
        view.updateBomb();

    }
    void tigglePause(){
        if(die == false) {
            pause = !pause;
            if (pause) {
                view.pause.setVisible(true);
            } else {
                view.pause.setVisible(false);
            }
        }
    }

    void setDie(){
        die = true;
        view.die.setVisible(true);
    }
    void increaseScore(){
        if(score > 1000){
            generateBomb();
            score+=100;
        }else if(score > 100){
            if(posion == false) {
                generatePoison();
                posion = true;
            }
            score+=50;
        }else{
            score+=10;
        }
    }
     void moveForward(int dir){
         //0 up
         //1 down
         //2 left
         //3 right
         //System.out.println("Move forward");
         switch(dir){
             case 0:
                 double xNew = snakeBody.getFirst().getX();
                 double yNew = snakeBody.getFirst().getY()-1;
                 Point upper = new Point((int)xNew,(int)yNew);
                 if(yNew < 0||snakeBody.contains(upper)||upper.equals(poison)||upper.equals(bomb)){
                     setDie();
                     playClip("goodbye.wav");
                 }else{
                     snakeBody.addFirst(upper);
                     headDir = 0;
                     if(food.equals(upper)){
                         playClip("eat.wav");
                         feedFood();
                         increaseScore();
                     }else{
                         snakeBody.removeLast();
                     }
                     view.drawSnakeBody();
                     view.printScore();
                 }
                 break;
             case 1:
                 xNew = snakeBody.getFirst().getX();
                 yNew = snakeBody.getFirst().getY()+1;
                 Point down = new Point((int)xNew,(int)yNew);
                 if(yNew > 25||snakeBody.contains(down)||down.equals(poison)||down.equals(bomb)){
                     setDie();
                     playClip("goodbye.wav");
                 }else{
                     snakeBody.addFirst(down);
                     headDir = 1;
                     if(food.equals(down)){
                         playClip("eat.wav");
                         feedFood();
                         increaseScore();
                     }else{
                         snakeBody.removeLast();
                     }
                     view.drawSnakeBody();
                     view.printScore();
                 }
                 break;
             case 2:
                 xNew = snakeBody.getFirst().getX()-1;
                 yNew = snakeBody.getFirst().getY();
                 Point left = new Point((int)xNew,(int)yNew);
                 if(xNew<0||snakeBody.contains(left)||left.equals(poison)||left.equals(bomb)){
                     setDie();
                     playClip("goodbye.wav");
                 }else{
                     snakeBody.addFirst(left);
                     headDir = 2;
                     if(food.equals(left)){
                         playClip("eat.wav");
                         feedFood();
                         increaseScore();
                     }else{
                         snakeBody.removeLast();
                     }
                     view.drawSnakeBody();
                     view.printScore();
                 }
                 break;
             case 3:
                 xNew = snakeBody.getFirst().getX()+1;
                 yNew = snakeBody.getFirst().getY();
                 Point right = new Point((int)xNew,(int)yNew);
                 if(xNew > 25 || snakeBody.contains(right)||right.equals(poison)||right.equals(bomb)){
                     setDie();
                     playClip("goodbye.wav");
                 }else{
                     snakeBody.addFirst(right);
                     headDir = 3;
                     if(food.equals(right)){
                         playClip("eat.wav");
                         feedFood();
                         increaseScore();
                     }else{
                         snakeBody.removeLast();
                     }
                     view.drawSnakeBody();
                     view.printScore();
                 }
                 break;
         }

     }

    int rad(long seed){
        Random r = new Random(seed);
        return r.nextInt(26)+0;
    }

    Point radP(long s1,long s2){
        int rX = rad(s1);
        int rY = rad(s2);
        Point p = new Point(rX,rY);
        return p;
    }

    int radDir(long seed){
        Random r = new Random(seed);
        return r.nextInt(4)+0;

    }
    void playClip(String name){
        try {
            ais = AudioSystem.getAudioInputStream(State.class.getResource(name));
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
        }catch(Exception e){e.printStackTrace();}
    }

}