package Robots;

import robocode.*;

import java.io.FileWriter;
import java.io.IOException;

public class LeanRobot extends AdvancedRobot {

    private static final String comma = ";";
    ScannedRobotEvent scannedRobot;
    static FileWriter fw;
    static {
        try {
            fw = new FileWriter("Dataset_IA_LeanTeam.csv");
            fw.write("Alvo da Bala" + comma + "Distancia" + comma + "Velocidade do Inimigo" + comma + "Resutlado\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Dados {
        String nome;
        Double distancia;
        Double velocidade;

        public Dados(String nome, Double distancia, Double velocidade) {
            this.nome = nome;
            this.distancia = distancia;
            this.velocidade = velocidade;
        }
    }

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
        scannedRobot = event;
        fire(1);
    }

    @Override
    public void onBulletHit(BulletHitEvent event) {
        super.onBulletHit(event);

        Dados d = new Dados(event.getName(), scannedRobot.getDistance(), scannedRobot.getVelocity());
        try {
            //testar se acertei em quem era suposto
            if (event.getName().equals(event.getBullet().getVictim())) {
                fw.append(d.nome + comma + d.distancia + comma + d.velocidade + comma + "acertou\n");
            } else {
                fw.append(d.nome + comma + d.distancia + comma + d.velocidade + comma + "falhou\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBulletMissed(BulletMissedEvent event) {
        super.onBulletMissed(event);
        Dados d = new Dados(scannedRobot.getName(), scannedRobot.getDistance(), scannedRobot.getVelocity());
        try {
            fw.append(d.nome + comma + d.distancia + comma + d.velocidade + comma + "falhou\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBulletHitBullet(BulletHitBulletEvent event) {
        Dados d = new Dados(scannedRobot.getName(), scannedRobot.getDistance(), scannedRobot.getVelocity());
        try {
            fw.append(d.nome + comma + d.distancia + comma + d.velocidade + comma + "falhou\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBattleEnded(BattleEndedEvent event) {
        super.onBattleEnded(event);

        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
