package Robots;

public class HelloRobot extends robocode.Robot{

    public void run() {
        while(true) {
            this.ahead(100);
            this.turnRight(90);
        }
    }
}
