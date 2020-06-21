package Robots;

import robocode.AdvancedRobot;
import robocode.BulletHitEvent;
import robocode.ScannedRobotEvent;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class LeanRobot extends AdvancedRobot {

    PrintWriter writer;

    private class Dados{
        String nome;
        String vitima;

        public Dados(String nome, String vitima) {
            this.nome = nome;
            this.vitima = vitima;
        }
    }

    @Override
    public void run() {

        try {
            writer = new PrintWriter(new File("Dataset_IA_LeanTeam.csv"));
            writer.write("Nome, Alvo\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            this.setAhead(100);
            this.setTurnRight(100);
            this.scan();
            execute();
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        fire(1);
    }

    @Override
    public void onBulletHit(BulletHitEvent event) {
        super.onBulletHit(event);
        Dados d = new Dados(event.getBullet().getName(), event.getBullet().getVictim());
        try
        {
            //testar se acertei em quem era suposto
            if (event.getName().equals(event.getBullet().getVictim())){
                //fw.write(d.nome+","+d.vitima+",acertei\n");
                System.out.println(d.nome+","+d.vitima+",acertei\n");
            }
            else {
                //fw.write(d.nome + "," + d.vitima + ",falhei\n");
                System.out.println(d.nome + "," + d.vitima + ",falhei\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
