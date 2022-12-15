package com.demofx.demofx;

import com.demofx.demofx.modelo.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PersonDialogController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtAge;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnExit;

    private Person person;

    private ObservableList<Person> persons;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    private void onSaveUser(ActionEvent actionEvent) {
        try {
            String name = this.txtName.getText();
            String surname = this.txtSurname.getText();
            Integer age = Integer.parseInt(this.txtAge.getText());

            Person person = new Person(name, surname, age);

            if (!this.persons.contains(person)) {

                if (this.person != null){
                   this.person.setName(name);
                   this.person.setSurname(surname);
                   this.person.setAge(age);
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Datos Actualizados 😶‍🌫️");
                    alert.setContentText("La persona se ha actualizado correctamente");
                    alert.showAndWait();
                } else {
                    this.person = person;
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Persona Creada");
                    alert.setContentText("La persona se ha registrado correctamente");
                    alert.showAndWait();
                }
                Stage stage = (Stage) this.btnSave.getScene().getWindow();
                stage.close();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona ya se encuentra registrada en la tabla. 👺");
                alert.showAndWait();
            }
        } catch (NumberFormatException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR!!!");
            alert.setContentText("Formato erroneo, porfavor ingrese solo numeros");
            alert.showAndWait();
        }
    }

    @FXML
    private void onExit(ActionEvent actionEvent) {
        this.persons = null;
        Stage stage = (Stage) this.btnExit.getScene().getWindow();
        stage.close();
    }

    public ObservableList<Person> getPersons() {
        return persons;
    }

    public void setPersons(ObservableList<Person> persons) {
        this.persons = persons;
    }

    public void initAtributtes(ObservableList<Person> persons, Person person){
        this.persons = persons;
        this.person = person;
        this.txtName.setText(person.getName());
        this.txtSurname.setText(person.getSurname());
        this.txtAge.setText(person.getAge().toString());
    }

    public void initAtributtes(ObservableList<Person> persons) {
        this.persons = persons;
    }
}
