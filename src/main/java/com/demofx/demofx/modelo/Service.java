package com.demofx.demofx.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

public class Service {

    private String tuitionVehicle;
    private String nifCustomer;
    private LocalDate fechaRegister;
    private LocalDate dateDelivery;
    private Double total;

    public Service(String tuitionVehicle, String nifCustomer, LocalDate fechaRegister, LocalDate dateDelivery, Double total) {
        this.tuitionVehicle = tuitionVehicle;
        this.nifCustomer = nifCustomer;
        this.fechaRegister = fechaRegister;
        this.dateDelivery = dateDelivery;
        this.total = total;
    }

    public boolean createService() throws SQLException {
        try {
            String sql = "INSERT INTO public.servicios(id_servicio, matricula_vehiculo, nif_cliente, fecha_alquiler, fecha_entrega, total) VALUES (CAST(ROUND((RANDOM() * (1000 - 100)) + 100) AS integer),?, ?, ?, ?, ?)";
            PreparedStatement statement = openConnection().prepareStatement(sql);
            statement.setString(1, this.tuitionVehicle);
            statement.setString(2, this.nifCustomer);
            statement.setDate(3, Date.valueOf(this.fechaRegister));
            statement.setDate(4, Date.valueOf(this.dateDelivery));
            statement.setDouble(5, this.total);

            int rows = statement.executeUpdate();

            openConnection().close();

            boolean isExecute = false;
            if (rows > 0) {
                isExecute = true;
            }
            return isExecute;
        } catch (SQLException exception){
            throw exception;
        }
    }


    public Connection openConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost/rental-car";
        String user = "postgres";
        String password = "Nomic230s";
        try {
            Connection connection;
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException exception) {
            throw exception;
        }
    }

    public String getTuitionVehicle() {
        return tuitionVehicle;
    }

    public void setTuitionVehicle(String tuitionVehicle) {
        this.tuitionVehicle = tuitionVehicle;
    }

    public String getNifCustomer() {
        return nifCustomer;
    }

    public void setNifCustomer(String nifCustomer) {
        this.nifCustomer = nifCustomer;
    }

    public LocalDate getFechaRegister() {
        return fechaRegister;
    }

    public void setFechaRegister(LocalDate fechaRegister) {
        this.fechaRegister = fechaRegister;
    }

    public LocalDate getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(LocalDate dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
