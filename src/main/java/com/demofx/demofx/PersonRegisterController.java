package com.demofx.demofx;

import com.demofx.demofx.modelo.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class PersonRegisterController implements Initializable {

    @FXML
    private TextField txtFind;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnCreate;

    @FXML
    private TableView<Person> tbUser;
    @FXML
    private TableColumn<Person, String> colName;
    @FXML
    private TableColumn<Person, String> colSurname;
    @FXML
    private TableColumn<Person, Integer> colAge;
    private ObservableList<Person> persons;

    private ObservableList<Person> findPersons;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persons = FXCollections.observableArrayList();
        findPersons = FXCollections.observableArrayList();
        this.tbUser.setItems(persons);

        this.colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        this.colSurname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        this.colAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
    }

    @FXML
    public void onCreateUser(ActionEvent actionEvent) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("person-dialog-view.fxml"));
            Parent root = loader.load();
            PersonDialogController controller = loader.getController();
            controller.initAtributtes(persons);
            Scene scene = new Scene(root);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL); // Permite que no pueda devolverme a otra scene.
            stage.setScene(scene);
            stage.setTitle("Registrar Usuario");
            stage.showAndWait();

            Person person = controller.getPerson();
            if(person != null){
                this.persons.add(person);
                if (person.getName().toLowerCase().contains(txtFind.getText().toLowerCase())){
                    this.findPersons.add(person);
                }
                this.tbUser.refresh();
            }

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    @FXML
    public void onUpdateUser(ActionEvent actionEvent) {
            Person person = this.tbUser.getSelectionModel().getSelectedItem();

            if (person == null) {
                Alert alertError = new Alert(Alert.AlertType.ERROR);
                alertError.setHeaderText(null);
                alertError.setTitle("ERROR!!!");
                alertError.setContentText("Porfavor selecciona una persona.");
                alertError.showAndWait();
            }else {
                try {

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("person-dialog-view.fxml"));

                    Parent root = loader.load();
                    PersonDialogController controller = loader.getController();
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    controller.initAtributtes(persons, person);

                    stage.initModality(Modality.APPLICATION_MODAL); // Permite que no pueda devolverme a otra scene.
                    stage.setScene(scene);
                    stage.setTitle("Actualizando Usuario");
                    stage.showAndWait();

                    Person personAux = controller.getPerson();
                    if (personAux != null) {
                        if (!person.getName().toLowerCase().contains(txtFind.getText().toLowerCase())){
                            this.findPersons.remove(person);
                        }
                        this.tbUser.refresh();
                    }
                } catch (IOException exception) {
                    Alert alertError = new Alert(Alert.AlertType.ERROR);
                    alertError.setHeaderText(null);
                    alertError.setTitle("ERROR!!! 🫤🫤🫤");
                    alertError.setContentText(exception.getMessage());
                }
            }
    }
    @FXML
    public void onDeleteUser(ActionEvent actionEvent) {
        Person person = this.tbUser.getSelectionModel().getSelectedItem();
        if (person == null) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setHeaderText(null);
            alertError.setTitle("ERROR!!!");
            alertError.setContentText("Porfavor selecciona una persona.");
            alertError.showAndWait();
        } else {
            this.persons.remove(person);
            this.findPersons.remove(person);
            Alert alertOk = new Alert(Alert.AlertType.CONFIRMATION);
            alertOk.setHeaderText(null);
            alertOk.setTitle("Persona Eliminada");
            alertOk.setContentText("Se ha eliminado la persona correctamente");
            alertOk.showAndWait();
            this.tbUser.refresh();
        }
    }
    @FXML
    public void onFindByName(KeyEvent keyEvent) {

        String findName = this.txtFind.getText();

        if (findName.isEmpty()) {
            this.tbUser.setItems(persons);
        } else {
            this.findPersons.clear();
           this.persons.stream().
                    filter(person -> person.getName().toLowerCase().contains(findName.toLowerCase()))
                    .collect(Collectors.toList()).forEach(elem -> findPersons.add(elem));
            this.tbUser.setItems(findPersons);
        }
    }
}