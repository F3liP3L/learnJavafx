package com.demofx.demofx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private Button btnCreateService;
    @FXML
    private Button btnFindService;

    @FXML
    public void onCreateService(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("service-register-view.fxml"));

            Parent root = loader.load();
            ServiceRegisterController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Registrar Servicio");
            stage.show();

            stage.setOnCloseRequest(elem -> controller.closeWindows());

            Stage myStage = (Stage) this.btnCreateService.getScene().getWindow();
            myStage.close();

        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void onFindService(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("service-find-view.fxml"));

            Parent root = loader.load();
            ServiceFindController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.setTitle("Consultar Servicio");
            stage.show();

            stage.setOnCloseRequest(elem -> controller.closeWindows());

            Stage myStage = (Stage) this.btnCreateService.getScene().getWindow();
            myStage.close();

        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
