package com.demofx.demofx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ServiceFindController implements Initializable {
    @FXML
    private TableView tblService;
    @FXML
    private TableColumn colTuiton;
    @FXML
    private TableColumn colBrand;
    @FXML
    private TableColumn colPrice;
    @FXML
    private TableColumn colDateRegister;
    @FXML
    private TableColumn colDateDelivery;
    @FXML
    private TableColumn colTotal;
    @FXML
    private DatePicker dateRegister;
    @FXML
    private DatePicker dateDelivery;
    @FXML
    private ComboBox listCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void closeWindows() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("main-rental-car.fxml"));

            Parent root = loader.load();
            MainController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.setScene(scene);
            stage.show();

            Stage myStage = (Stage) this.listCustomer.getScene().getWindow();
            myStage.close();

        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void setTblService(TableView tblService) {
        this.tblService = tblService;
    }

    public void setColTuiton(TableColumn colTuiton) {
        this.colTuiton = colTuiton;
    }

    public void setColBrand(TableColumn colBrand) {
        this.colBrand = colBrand;
    }

    public void setColPrice(TableColumn colPrice) {
        this.colPrice = colPrice;
    }

    public void setColDateRegister(TableColumn colDateRegister) {
        this.colDateRegister = colDateRegister;
    }

    public void setColDateDelivery(TableColumn colDateDelivery) {
        this.colDateDelivery = colDateDelivery;
    }

    public void setColTotal(TableColumn colTotal) {
        this.colTotal = colTotal;
    }

    public void setDateRegister(DatePicker dateRegister) {
        this.dateRegister = dateRegister;
    }

    public void setDateDelivery(DatePicker dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public void setListCustomer(ComboBox listCustomer) {
        this.listCustomer = listCustomer;
    }
}
