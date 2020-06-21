package Robots;

import robocode.HitByBulletEvent;

public class HelloRobot extends robocode.Robot{

    public void run() {
        while(true) {
            this.ahead(100);
            this.turnRight(90);
        }
    }

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        System.out.println("Fui atingido por uma bala de potencia : " + event.getPower() + "\nVinda de " + event.getBearing() + " relativamente a mim."
        +"\n Foi disparada pelo "+ event.getBullet().getName()+ ", \ne antigiu-me com uma velocidade de " + event.getVelocity() + "px/s.");
    }
}
