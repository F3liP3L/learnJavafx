package com.demofx.demofx;
import com.demofx.demofx.modelo.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class PersonController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtAge;
    @FXML
    private Button btnCreatePerson;
    @FXML
    private TableView<Person> tbPerson;
    @FXML
    private TableColumn colName;
    @FXML
    private TableColumn colSurname;
    @FXML
    private TableColumn colAge;

    private ObservableList<Person> persons;

    @FXML
    public void onCreatePerson(ActionEvent actionEvent) {
        try {
            String name = this.txtName.getText();
            String surname = this.txtSurname.getText();
            Integer age = Integer.parseInt(this.txtAge.getText());

            Person person = new Person(name, surname, age);

            if (!this.persons.contains(person)) {
                this.persons.add(person);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(null);
                alert.setTitle("Persona Creada");
                alert.setContentText("La persona se ha registrado correctamente");
                alert.showAndWait();
                this.tbPerson.setItems(persons);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("La persona ya se encuentra registrada en la tabla. 👺");
                alert.showAndWait();
            }
        }
         catch (NumberFormatException exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR!!!");
            alert.setContentText("Formato erroneo, porfavor ingrese solo numeros");
            alert.showAndWait();
        }
    }

    @FXML
    public void onDelete(ActionEvent actionEvent) {
        Person person = this.tbPerson.getSelectionModel().getSelectedItem();
        if (person == null) {
            Alert alertError = new Alert(Alert.AlertType.ERROR);
            alertError.setHeaderText(null);
            alertError.setTitle("ERROR!!!");
            alertError.setContentText("Porfavor selecciona una persona.");
            alertError.showAndWait();
        } else {
            this.persons.remove(person);
            Alert alertOk = new Alert(Alert.AlertType.CONFIRMATION);
            alertOk.setHeaderText(null);
            alertOk.setTitle("Persona Eliminada");
            alertOk.setContentText("Se ha eliminado la persona correctamente");
            alertOk.showAndWait();
            this.tbPerson.refresh();
        }
    }

    @FXML
    public void onUpdate(ActionEvent actionEvent) {
        Person person = this.tbPerson.getSelectionModel().getSelectedItem();
        if (person == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR!!!");
            alert.setContentText("Porfavor selecciona una persona.");
            alert.showAndWait();
        } else {
            try {
                String name = this.txtName.getText();
                String surname = this.txtSurname.getText();
                Integer age = Integer.parseInt(this.txtAge.getText());

                Person personAux = new Person(name, surname, age);

                if (!this.persons.contains(personAux)) {
                    person.setName(personAux.getName());
                    person.setSurname(personAux.getSurname());
                    person.setAge(personAux.getAge());

                    Alert alertOk = new Alert(Alert.AlertType.CONFIRMATION);
                    alertOk.setHeaderText(null);
                    alertOk.setTitle("Persona Actualizada");
                    alertOk.setContentText("Se ha actualizado la persona correctamente");
                    alertOk.showAndWait();

                    this.tbPerson.refresh();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("La persona ya se encuentra registrada en la tabla. 👺");
                    alert.showAndWait();
                }
            }
            catch (NumberFormatException exception){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR!!!");
                alert.setContentText("Formato erroneo, porfavor ingrese solo numeros");
                alert.showAndWait();
            }
        }
    }

    @FXML
    public void onSelect(MouseEvent mouseEvent) {
        Person person = this.tbPerson.getSelectionModel().getSelectedItem();
        if (person != null) {
         this.txtName.setText(person.getName());
         this.txtSurname.setText(person.getSurname());
         this.txtAge.setText(person.getAge().toString());
        }
    }

    @FXML
    public void onDeleteAll(ActionEvent actionEvent) {
       if(persons.isEmpty()){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setHeaderText(null);
           alert.setTitle("ERROR!!!");
           alert.setContentText("No existe ningun dato en la tabla");
           alert.showAndWait();
       } else {
           persons.clear();
           this.tbPerson.refresh();
       }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        persons = FXCollections.observableArrayList();

        this.colName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        this.colSurname.setCellValueFactory(new PropertyValueFactory<Person, String>("surname"));
        this.colAge.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
    }
}
