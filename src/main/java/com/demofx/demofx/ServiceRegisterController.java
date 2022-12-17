package com.demofx.demofx;

import com.demofx.demofx.modelo.Customer;
import com.demofx.demofx.modelo.Service;
import com.demofx.demofx.modelo.Vehicle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ServiceRegisterController implements Initializable {
    @FXML
    private DatePicker dateDelivery;
    @FXML
    private TextField txtBrand;
    @FXML
    private TextField txtKilometres;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtPopulation;
    @FXML
    private TextField txtNif;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtDescription;
    @FXML
    private ChoiceBox<Customer> listCustomer;
    @FXML
    private ChoiceBox<Vehicle> listVehicle;
    @FXML
    private DatePicker dateRental;
    @FXML
    private Button btnSave;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            initCombox();
    }

    public void initCombox() {
        Customer customer = new Customer();
        try {
            ObservableList<Customer> observableListCustomer = customer.getCustomer();
            this.listCustomer.setItems(observableListCustomer);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        Vehicle vehicle = new Vehicle();
        try {
            ObservableList<Vehicle>observableListVehicle = vehicle.getVehicle();
            this.listVehicle.setItems(observableListVehicle);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @FXML
    public void onSaveService(ActionEvent actionEvent) throws SQLException {
        Vehicle vehicle = this.listVehicle.getValue();
        Customer customer = this.listCustomer.getValue();
        LocalDate dateRegister = this.dateRental.getValue();
        LocalDate dateDelivery = this.dateDelivery.getValue();
        System.out.println(dateDelivery + " -- " + dateRegister);
        double total = Double.parseDouble(this.txtTotal.getText());

        String errores = "";
        if (ifNull(customer)){
            errores += "Debes seleccionar un cliente.\n";
        }
        if(ifNull(vehicle)){
            errores += "Debes seleccionar un vehiculo.\n";
        }
        if(ifNull(dateRegister)){
            errores += "Debes seleccionar una fecha de alquiler.\n";
        }
        if(ifNull(dateDelivery)){
            errores += "Debes seleccionar una fecha de entrega.\n";
        }
        if(dateRegister != null && dateDelivery != null && dateRegister.isAfter(dateDelivery)){
            errores += "La fecha de alquiler no es coherente con la fecha de entrega.\n";
        }
        if (total == 0){
            errores += "El total no puede ser 0.\n";
        }

        if(errores.isEmpty()){

            Service service = new Service(vehicle.getTuition(), customer.getNif(), dateRegister, dateDelivery, total);
            if(service.createService()){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Servicio Registrado!");
                alert.setContentText("El servicio se ha registrado con exito 😁.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR!!!");
                alert.setContentText("No se ha podido registrar el servicio. 🫤");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR!!!");
            alert.setContentText(errores);
            alert.showAndWait();
        }
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

            Stage myStage = (Stage) this.btnSave.getScene().getWindow();
            myStage.close();

        } catch (Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public static boolean ifNull(Object object){
        boolean isNull = false;
        if(object == null){
            isNull = true;
        }
        return isNull;
    }
}
