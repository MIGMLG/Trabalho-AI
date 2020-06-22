package Robots;

import hex.genmodel.easy.RowData;
import hex.genmodel.MojoModel;
import hex.genmodel.easy.EasyPredictModelWrapper;
import hex.genmodel.easy.exception.PredictException;
import hex.genmodel.easy.prediction.BinomialModelPrediction;
import robocode.*;

import java.io.*;
import java.util.Arrays;

public class LeanRobot extends AdvancedRobot {

    ScannedRobotEvent scannedRobot;
    EasyPredictModelWrapper model;

    public LeanRobot() throws IOException {
        model = new EasyPredictModelWrapper(MojoModel.load("H2O Models/DRF_ROBOCODE_36.zip"));
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

        try {

            scannedRobot = event;

            RowData row = new RowData();
            row.put("Alvo da Bala", scannedRobot.getName());
            row.put("Distancia", scannedRobot.getDistance());
            row.put("Velocidade do Inimigo", scannedRobot.getVelocity());

            BinomialModelPrediction p = model.predictBinomial(row);

            System.out.println("");
            System.out.println("Probabilidade de disparar ou nao disparar:" + Arrays.toString(p.classProbabilities));
            System.out.println("\n\nPrevisão: " + p.label);

            if(p.label == "disparar"){
                fire(1);
            }

        } catch (PredictException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBattleEnded(BattleEndedEvent event) {
        super.onBattleEnded(event);
    }

}
