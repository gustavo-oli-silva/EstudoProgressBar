package com.example.estudoprogressbar.controllers;

import com.example.estudoprogressbar.internal.ViewConfiguration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TesteController implements Initializable {
    @FXML
    public DialogPane dialogPane;

    @FXML
    public Button turnBackButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void previousScreen(final ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ViewConfiguration.ESTUDO.getScreen());
            Parent parent = fxmlLoader.load();
            Stage stage = (Stage) turnBackButton.getScene().getWindow();
            Scene scene = new Scene(parent, 1280, 720);
            stage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
