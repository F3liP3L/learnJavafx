package com.demofx.demofx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    @Override
    public void init(){
        // Aqui cargan las configuraciones iniciales. Primero corre este metodo antes de cargar los componentes referentes a JavaFX.
    }

    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main-rental-car.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Tabla de Usuarios");
            stage.setScene(scene);
            stage.show();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public void stop(){
        // Este metodo se ejecuta despues de cerrar la aplicacion. Para poder cerrar la aplicacion se usa la clase Platform y el metodo exit().
    }

    public static void main(String[] args) {
        launch();
    }
}