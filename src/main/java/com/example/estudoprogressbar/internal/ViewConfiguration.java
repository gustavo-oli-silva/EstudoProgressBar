package com.example.estudoprogressbar.internal;

import com.example.estudoprogressbar.HelloApplication;

import java.net.URL;

public enum ViewConfiguration {
    ESTUDO(1, HelloApplication.class.getResource("estudo.fxml")),
    TESTE(2, HelloApplication.class.getResource("teste.fxml"));

    private final int id;
    private final URL screen;

    ViewConfiguration(int id, URL screen) {
        this.id = id;
        this.screen = screen;
    }

    public int getId() {
        return id;
    }

    public URL getScreen() {
        return screen;
    }

    public static ViewConfiguration valueOf(int id) {
        for (ViewConfiguration config : ViewConfiguration.values()) {
            if (config.getId() == id) {
                return config;
            }
        }

        throw new IllegalArgumentException("Invalid view configuration id: " + id);
    }
}
