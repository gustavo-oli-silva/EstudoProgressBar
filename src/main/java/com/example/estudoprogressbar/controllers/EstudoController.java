package com.example.estudoprogressbar.controllers;

import com.example.estudoprogressbar.internal.ViewConfiguration;
import com.example.estudoprogressbar.service.ProgressBarService;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EstudoController implements Initializable {
    @FXML
    public ProgressBar progressBar;

    @FXML
    public Label progressLabel;

    @FXML
    public Button button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        progressLabel.setLabelFor(progressBar);
        ProgressBarService p = new ProgressBarService(progressBar, progressLabel);
        p.setMaxValue(100);

        p.onComplete(() -> System.out.println("Analise " + this.progressLabel.getText() + " completa"));

        List<String> mensagens = List.of(
                "FolhaDesligamento.xml",
                "FolhaItem.xml",
                "Desligamento.xml",
                "Ato.xml",
                "Aposentadoria.xml"
        );

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(p.getCurrentValue() <= 100) {
                    p.increment(10);
                } else {
                    p.setText("Teste");
                    p.setCurrentValue(0);
                }
            }
        };

        timer.start();
    }

    @FXML
    public void nextScreen(final ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ViewConfiguration.TESTE.getScreen());
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) button.getScene().getWindow();
            Scene scene = new Scene(parent, 1280, 720);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
