package Robots;

import robocode.*;

import java.io.FileWriter;
import java.io.IOException;

public class LeanLoggerRobotV2 extends AdvancedRobot {

    private static final String comma = ";";
    ScannedRobotEvent scannedRobot;
    static FileWriter fw;

    static {
        try {
            fw = new FileWriter("Dataset_IA_LeanTeam.csv");
            fw.write("Alvo da Bala" + comma + "Distancia" + comma + "Velocidade do Inimigo" + comma + "Heading do Atirador" + comma + "Bearing do Inimigo" + comma + "Heading do Inimigo" + comma + "Resultado\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean canShoot;

    private class Dados {
        String nome;
        Double distancia;
        Double velocidade;
        Double headingAtirador;
        Double bearingInimigo;
        Double headingInimigo;

        public Dados(String nome, Double distancia, Double velocidade, Double headingAtirador, Double bearingInimigo, Double headingInimigo) {
            this.nome = nome;
            this.distancia = distancia;
            this.velocidade = velocidade;
            this.headingAtirador = headingAtirador;
            this.bearingInimigo = bearingInimigo;
            this.headingInimigo = headingInimigo;
        }
    }

    public LeanLoggerRobotV2() {
        this.canShoot = true;
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
        if (canShoot) {
            scannedRobot = event;
            fire(1);
            canShoot = false;
        }
    }

    @Override
    public void onBulletHit(BulletHitEvent event) {
        super.onBulletHit(event);

        Dados d = new Dados(event.getName(), scannedRobot.getDistance(), scannedRobot.getVelocity(), this.getHeading(), scannedRobot.getBearing(), scannedRobot.getHeading());
        try {
            //testar se acertei em quem era suposto
            if (event.getName().equals(event.getBullet().getVictim())) {
                fw.append(d.nome + comma + d.distancia + comma + d.velocidade + comma + d.headingAtirador + comma + d.bearingInimigo + comma + d.headingInimigo + comma + "disparar\n");
            } else {
                fw.append(d.nome + comma + d.distancia + comma + d.velocidade + comma + d.headingAtirador + comma + d.bearingInimigo + comma + d.headingInimigo + comma + "nao disparar\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        canShoot = true;
    }

    @Override
    public void onBulletMissed(BulletMissedEvent event) {
        super.onBulletMissed(event);
        Dados d = new Dados(scannedRobot.getName(), scannedRobot.getDistance(), scannedRobot.getVelocity(), this.getHeading(), scannedRobot.getBearing(), scannedRobot.getHeading());

        try {
            fw.append(d.nome + comma + d.distancia + comma + d.velocidade + comma + d.headingAtirador + comma + d.bearingInimigo + comma + d.headingInimigo + comma + "nao disparar\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        canShoot = true;
    }

    @Override
    public void onBulletHitBullet(BulletHitBulletEvent event) {
        Dados d = new Dados(scannedRobot.getName(), scannedRobot.getDistance(), scannedRobot.getVelocity(), this.getHeading(), scannedRobot.getBearing(), scannedRobot.getHeading());

        try {
            fw.append(d.nome + comma + d.distancia + comma + d.velocidade + comma + d.headingAtirador + comma + d.bearingInimigo + comma + d.headingInimigo + comma + "nao disparar\n");
        } catch (IOException e) {
            e.printStackTrace();
        }

        canShoot = true;
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
