module com.demofx.demofx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires java.sql;


    opens com.demofx.demofx to javafx.base, javafx.fxml;
    opens com.demofx.demofx.modelo to javafx.fxml;
    exports com.demofx.demofx;
    exports com.demofx.demofx.modelo;

}