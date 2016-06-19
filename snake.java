import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;


public class snake {


    public static void main(String []args) {
        //add splash screen first
        int speed = 0;
        int FPS = 0;
        FPS = Integer.parseInt(args[0]);
        speed = Integer.parseInt(args[1]);
        Font aa = new Font("LesliesHand", Font.PLAIN, 30);
        Font adSmall = new Font("Addis Ababa", Font.PLAIN, 20);
        Font aaSS = new Font("LesliesHand", Font.PLAIN, 18);
        Font gg = new Font("Give You Glory", Font.PLAIN, 150);
        Color c = new Color(37,90,163);
        Color c1 = new Color(37,121,203);
        Point p = new Point(0,0);
        JFrame frame = new JFrame("Snake");
        JFrame splash = new JFrame("Snake");
        frame.setLayout(null);

        splash.setLayout(null);
        JButton restartButton = new JButton();
        JButton startButton = new JButton();
        JLabel game = new JLabel("Snake");
        JLabel name = new JLabel("Ling Meng");
        JLabel userID = new JLabel("l9meng");
        JLabel start = new JLabel("Press 's' to start.");
        JLabel restart = new JLabel("Press 'r' to restart.");
        JLabel pause = new JLabel("Press 'space' to press 'space'.");
        JLabel keys = new JLabel("Use arrow keys to play the game.");
        JLabel food = new JLabel("Eat apples to grow.");
        JLabel poison = new JLabel("Avoid poison and bomb to stay alive.");

        State model = new State(FPS,speed);
        Board view = new Board(model);
        model.addView(view);
        SnakeControl controller = new SnakeControl(model);

        restartButton.setLocation(new Point(605,480));
        restartButton.setSize(150, 40);
        restartButton.setText("Restart");
        restartButton.setFont(adSmall);

        startButton.setLocation(new Point(350,450));
        startButton.setSize(150, 40);
        startButton.setText("Start");
        startButton.setFont(adSmall);

        game.setLocation(new Point(190,20));
        game.setSize(500, 200);
        game.setFont(gg);
        game.setForeground(new Color(37,62,163));

        name.setLocation(new Point(350,100));
        name.setSize(500, 200);
        name.setFont(aa);
        name.setForeground(c);

        userID.setLocation(new Point(350,140));
        userID.setSize(300, 200);
        userID.setFont(aa);
        userID.setForeground(c);

        start.setLocation(new Point(190,200));
        start.setSize(300, 200);
        start.setFont(aaSS);
        start.setForeground(c1);

        restart.setLocation(new Point(190,225));
        restart.setSize(300, 200);
        restart.setFont(aaSS);
        restart.setForeground(c1);

        pause.setLocation(new Point(190,250));
        pause.setSize(300, 200);
        pause.setFont(aaSS);
        pause.setForeground(c1);

        keys.setLocation(new Point(190,275));
        keys.setSize(500, 200);
        keys.setFont(aaSS);
        keys.setForeground(c1);


        food.setLocation(new Point(190,300));
        food.setSize(500, 200);
        food.setFont(aaSS);
        food.setForeground(c1);

        poison.setLocation(new Point(190,325));
        poison.setSize(500, 200);
        poison.setFont(aaSS);
        poison.setForeground(c1);


        splash.getContentPane().add(game);
        splash.getContentPane().add(name);
        splash.getContentPane().add(userID);
        splash.getContentPane().add(start);
        splash.getContentPane().add(restart);
        splash.getContentPane().add(pause);
        splash.getContentPane().add(poison);
        splash.getContentPane().add(food);
        splash.getContentPane().add(keys);
        splash.getContentPane().add(startButton);
        splash.setSize(new Dimension(800,600));
        splash.setResizable(false);
        splash.setVisible(true);
        splash.requestFocusInWindow();
        splash.setFocusable(true);
        model.running=false;
        try {
            AudioInputStream loading = AudioSystem.getAudioInputStream(State.class.getResource("party_time.wav"));
            Clip clipLoading = AudioSystem.getClip();
            clipLoading.open(loading);
            clipLoading.start();
        }catch(Exception ex){
            ex.printStackTrace();
        }

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println("Key Code:"+e.getKeyCode());
                switch(e.getKeyCode()){
                    case 39:	// -> Right
                        if(model.pause || model.die) return;
                        //System.out.println("right");
                        //if it's not the opposite direction
                        if(model.headDir!=2)
                            model.moveForward(3);
                        break;
                    case 38:	// -> Top
                        if(model.pause || model.die) return;
                        // System.out.println("Top");
                        if(model.headDir!=1)
                            model.moveForward(0);
                        break;

                    case 37:// -> Left
                        if(model.pause || model.die) return;
                        //System.out.println("Left");
                        if(model.headDir!=3)
                            model.moveForward(2);
                        break;

                    case 40:// -> Bottom
                        if(model.pause || model.die) return;
                        //System.out.println("Bottom");
                        if(model.headDir!=0)
                            model.moveForward(1);
                        break;
                    case 32://-> space for pause
                        model.tigglePause();
                        try {
                            AudioInputStream ok = AudioSystem.getAudioInputStream(State.class.getResource("ok.wav"));
                            Clip clipOK = AudioSystem.getClip();
                            clipOK.open(ok);
                            clipOK.start();
                        }catch(Exception ex){ex.printStackTrace();}
                        break;
                    case 82://->restart
                        //System.out.println("Restart");
                        try {
                            AudioInputStream hello = AudioSystem.getAudioInputStream(State.class.getResource("girlhello.wav"));
                            Clip clipHello = AudioSystem.getClip();
                            clipHello.open(hello);
                            clipHello.start();
                        }catch(Exception ex){ex.printStackTrace();}
                        view.area.removeAll();
                        view.area.revalidate();
                        view.area.repaint();
                        model.init(model.FPS,model.speed);
                        view.initViews();
                        break;
                    default: 	break;
            }
        }});

        restartButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("Restart");
                try {
                    AudioInputStream hello1 = AudioSystem.getAudioInputStream(State.class.getResource("girlhello.wav"));
                    Clip clipHello1 = AudioSystem.getClip();
                    clipHello1.open(hello1);
                    clipHello1.start();
                }catch(Exception ex){ex.printStackTrace();}
                view.area.removeAll();
                view.area.revalidate();
                view.area.repaint();
                model.init(model.FPS,model.speed);
                view.initViews();
                frame.requestFocusInWindow();
            }
        });

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //System.out.println("Start");
                try {
                    AudioInputStream start1 = AudioSystem.getAudioInputStream(State.class.getResource("welcome.wav"));
                    Clip clipStart1 = AudioSystem.getClip();
                    clipStart1.open(start1);
                    clipStart1.start();
                }catch(Exception ex){ex.printStackTrace();}
                splash.setVisible(false);
                view.setLocation(p);
                frame.getContentPane().add(view);
                frame.getContentPane().add(restartButton);
                frame.setSize(new Dimension(800,600));
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.requestFocusInWindow();
                frame.setFocusable(true);
                model.running=!model.running;

            }
        });

        splash.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("Start");
                switch(e.getKeyCode()) {
                    case 83:
                        try {
                            AudioInputStream start = AudioSystem.getAudioInputStream(State.class.getResource("welcome.wav"));
                            Clip clipStart = AudioSystem.getClip();
                            clipStart.open(start);
                            clipStart.start();
                        }catch(Exception ex){ex.printStackTrace();}
                        splash.setVisible(false);
                        view.setLocation(p);
                        frame.getContentPane().add(view);
                        frame.getContentPane().add(restartButton);
                        frame.setSize(new Dimension(800,600));
                        frame.setResizable(false);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                        frame.requestFocusInWindow();
                        frame.setFocusable(true);
                        model.running = !model.running;
                        break;
                    default:
                        break;
                }
            }
        });

        controller.run();
    }
}
