package com.demofx.demofx;

import com.demofx.demofx.modelo.Calculator;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    @FXML
    private TextField txtResult;
    @FXML
    private Button btnOperar;
    @FXML
    public RadioButton rdbSumar;
    @FXML
    public RadioButton rdbRestar;
    @FXML
    public RadioButton rdbMultiplicar;
    @FXML
    public RadioButton rdbDividir;
    @FXML
    private TextField txtOper1;
    @FXML
    private TextField textOpr2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup toggleGroup = new ToggleGroup();
        this.rdbSumar.setToggleGroup(toggleGroup);
        this.rdbRestar.setToggleGroup(toggleGroup);
        this.rdbMultiplicar.setToggleGroup(toggleGroup);
        this.rdbDividir.setToggleGroup(toggleGroup);
    }

    @FXML
    private void onOperate(ActionEvent actionEvent) {
        try {
            learnNumber(txtOper1);
            learnNumber(textOpr2);
            int oper1 = Integer.parseInt(this.txtOper1.getText());
            int oper2 = Integer.parseInt(this.textOpr2.getText());
            Calculator calculator = new Calculator(oper1, oper2);
            if (this.rdbSumar.isSelected()){
                this.txtResult.setText(String.valueOf(calculator.sumar()));
            } else if (this.rdbRestar.isSelected()){
                this.txtResult.setText(String.valueOf(calculator.restar()));
            } else if (this.rdbMultiplicar.isSelected()) {
                this.txtResult.setText(String.valueOf(calculator.multiplicar()));
            } else if (this.rdbDividir.isSelected()){
                if(oper2 != 0){
                    this.txtResult.setText(String.valueOf(calculator.dividir()));
                } else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("No es posible dividir por zero");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Porfavor seleccione una operacion");
                alert.showAndWait();
            }
        } catch (NumberFormatException exception){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR!!!");
            alert.setContentText("Formato erroneo, porfavor ingrese solo numeros");
            alert.showAndWait();
        }
    }
    @FXML
    private void onClear(){
        this.txtOper1.setText("");
        this.textOpr2.setText("");
        this.txtResult.setText("");
    }

    private static void learnNumber(TextField textField){
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                textField.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}
