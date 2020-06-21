package Robots;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;

public class BasicAdvancedRobot extends AdvancedRobot {

    @Override
    public void run() {
        while (true) {
            this.setAhead(100);
            this.setTurnRight(100);
            this.scan();
            execute();
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        fire(3);
    }
}
