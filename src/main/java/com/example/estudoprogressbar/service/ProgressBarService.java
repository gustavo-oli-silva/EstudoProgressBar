package com.example.estudoprogressbar.service;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

/**
 * Essa classe é uma abstração para facilitar o uso sinérgico da ProgressBar com uma Label
 */
public class ProgressBarService {
    private final Label label;
    private final ProgressBar progressBar;
    private final DoubleProperty property;
    private Runnable runnable;
    private double maxValue;
    private double currentValue;

    public ProgressBarService(ProgressBar progressBar, Label label) {
        this.label = label;
        this.progressBar = progressBar;
        this.property = new SimpleDoubleProperty();
        this.maxValue = 1;
        this.currentValue = 0;

        this.progressBar.progressProperty().bind(property);

        setupListener();
    }

    public ProgressBarService(ProgressBarService progressBarService) {
        this.progressBar = progressBarService.progressBar;
        this.label = progressBarService.label;
        this.property = progressBarService.property;
        this.maxValue = progressBarService.maxValue;
        this.currentValue = progressBarService.currentValue;

        this.progressBar.progressProperty().bind(property);

        setupListener();
    }

    /**
     * Esse método é usado para definir a lógica de acionamento de evento
     */
    private void setupListener() {
        this.property.addListener((observable, oldValue, newValue) -> {
            if (runnable == null) {
                return;
            }

            if (oldValue.doubleValue() >= 1.0) {
                return;
            }

            if (newValue.doubleValue() >= 1.0) {
                runnable.run();
            }
        });
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        if (maxValue < 0.0) {
            this.maxValue = 0;
            return;
        }

        this.maxValue = maxValue;
    }

    public double getCurrentValue() {
        return currentValue;
    }

    /**
     * Esse método irá definir o texto na Label
     *
     * @param text
     */
    public void setText(String text) {
        if (text == null) {
            label.setText("");
            return;
        }

        label.setText(text);
    }

    /**
     * Ele irá definir o progresso atual baseado no valor máximo
     *
     * @param currentValue
     */
    public void setCurrentValue(double currentValue) {
        if (currentValue < 0.0) {
            this.currentValue = 0;
            return;
        }

        this.currentValue = currentValue;
        this.property.set(this.currentValue / maxValue);
    }

    /**
     * Inspirado no método updateProgess da classe Task, esse método recebe o valor atual junto ao valor máximo
     * pode ser útil em cenários onde há uma incerteza do processo
     *
     * @param currentValue
     * @param maxValue
     */
    public void setPorcentage(double currentValue, double maxValue) {
        this.currentValue = currentValue;
        this.maxValue = maxValue;

        this.property.set(this.currentValue / maxValue);
    }

    /**
     * Esse método ira pegar o valor do progresso atual e somar com o que foi definido.
     * E ele irá estagnar no 100% caso o valor atual + o incremento for igual ou superior ao valor máximo.
     *
     * @param increment
     */
    public void increment(double increment) {
        this.currentValue = currentValue + increment;

        this.property.set(this.currentValue / maxValue);
    }

    public void setVisible(boolean visible) {
        this.progressBar.setVisible(visible);
        this.label.setVisible(visible);
    }

    /**
     * Inserir uma função que será acionada quando a barra de progresso atingir 100%
     *
     * @param runnable
     */
    public void onComplete(Runnable runnable) {
        this.runnable = runnable;
    }

    /**
     * Esse método reinicia o status da barra para zero, e elimina o texto da label
     * mas ainda permanece o valor total
     */
    public void reset() {
        this.currentValue = 0;
        this.property.set(currentValue);
        this.label.setText("");
    }

    /**
     * Esse método reseta o service para o seu estado de inicio, como se tivesse sido acabado de
     * ser instanciado
     */
    public void factoryReset() {
        this.reset();
        this.maxValue = 1;
        this.runnable = null;
    }
}
