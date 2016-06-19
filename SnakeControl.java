
public class SnakeControl implements Runnable {
    State model;
    public SnakeControl(State inModel){
        model = inModel;
    }
    public void run(){
        while(!model.running){
            pause(1);

            }
        long fpsTimer = System.nanoTime();
        long fpsTimer1 = System.nanoTime();
        //System.out.println("fpsTimer:"+fpsTimer);
        long nanoFPS = fpsTimer + 1000000000/model.FPS;
        //System.out.println("nanoFPS:"+nanoFPS/1000000000);
        long nanoPause = fpsTimer1 + 900/model.speed * 1000000;
        //System.out.println("nanoPause:"+nanoPause/1000000000);
        while(model.running){
            while (model.pause) {
                pause((long) model.speed);
            }
            while(model.die){
               // System.out.println("stop");
                pause((long) model.speed);
            }

            if(System.nanoTime()> nanoFPS) {
                model.updateView();
                fpsTimer = System.nanoTime();
                nanoFPS = fpsTimer + 1000000000 / model.FPS;
            }

            if(System.nanoTime() > nanoPause) {
                model.moveForward(model.headDir);//move
                fpsTimer = System.nanoTime();
                //System.out.println("nanoFPS:"+nanoFPS);
                nanoPause = fpsTimer + 900/model.speed * 1000000;
            }

        }

    }
    void  pause(long time){
            try {
               //System.out.println("pause: "+time);
                Thread.sleep(time);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
    }

}

