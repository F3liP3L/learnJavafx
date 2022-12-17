package com.demofx.demofx.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Vehicle {
    private String tuition;
    private String description;
    private String brand;
    private Integer kilometer;
    private Double price;


    public Vehicle(String tuition, String description, String brand, Integer kilometer, Double price) {
        this.tuition = tuition;
        this.description = description;
        this.brand = brand;
        this.kilometer = kilometer;
        this.price = price;
    }

    public Vehicle() {
        this.tuition = tuition;
        this.description = description;
        this.brand = brand;
        this.kilometer = kilometer;
        this.price = price;
    }

    public ObservableList<Vehicle> getVehicle() throws SQLException {
        ObservableList<Vehicle> observableVehicle = FXCollections.observableArrayList();
        try {
            Statement statement = openConnection().createStatement();
            ResultSet result = statement.executeQuery("select * from vehiculos");
            while (result.next()) {
                String tuition = result.getString("matricula");
                String brand = result.getString("marca");
                String description = result.getString("descripcion");
                Integer kilometer = result.getInt("kilometros");
                Double price = (double) result.getInt("precio");


                Vehicle vehicle = new Vehicle(tuition,description,brand,kilometer,price);
                observableVehicle.add(vehicle);
            }
            openConnection().close();
        } catch (SQLException exception){
            throw exception;
        }

        return observableVehicle;
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

    public String getTuition() {
        return tuition;
    }

    public void setTuition(String tuition) {
        this.tuition = tuition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getKilometer() {
        return kilometer;
    }

    public void setKilometer(Integer kilometer) {
        this.kilometer = kilometer;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.tuition;
    }
}
