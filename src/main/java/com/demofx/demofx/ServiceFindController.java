package com.demofx.demofx;

import com.demofx.demofx.modelo.Customer;
import com.demofx.demofx.modelo.Service;
import com.demofx.demofx.modelo.Vehicle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ServiceFindController implements Initializable {
    @FXML
    private TableView<Service> tblService;
    @FXML
    private TableColumn<Service, String> colTuiton;
    @FXML
    private TableColumn<Service, String> colBrand;
    @FXML
    private TableColumn<Service, Double> colPrice;
    @FXML
    private TableColumn<Service, LocalDate> colDateRegister;
    @FXML
    private TableColumn<Service, LocalDate> colDateDelivery;
    @FXML
    private TableColumn<Service, Double> colTotal;
    @FXML
    private DatePicker dateRegister;
    @FXML
    private DatePicker dateDelivery;
    @FXML
    private ComboBox<Customer> listCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

            this.colTuiton.setCellValueFactory(new PropertyValueFactory<>("tuitionVehicle"));
            this.colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
            this.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            this.colDateRegister.setCellValueFactory(new PropertyValueFactory<>("fechaRegister"));
            this.colDateDelivery.setCellValueFactory(new PropertyValueFactory<>("dateDelivery"));
            this.colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        try {
            Service service = new Service();
            ObservableList<Service> services = service.getService();
            this.tblService.setItems(services);
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
        }
        try {
            Customer customer = new Customer();
            ObservableList<Customer> observableListCustomer = customer.getCustomer();
            this.listCustomer.setItems(observableListCustomer);
        } catch (SQLException exception){
            System.out.println(exception.getMessage());
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
