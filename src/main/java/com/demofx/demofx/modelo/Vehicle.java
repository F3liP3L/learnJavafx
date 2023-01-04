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
    private boolean state;

    public void setState(boolean state) {
        this.state = state;
    }

    public Vehicle(String tuition, String description, String brand, Integer kilometer, Double price, boolean state) {
        this.tuition = tuition;
        this.description = description;
        this.brand = brand;
        this.kilometer = kilometer;
        this.price = price;
        this.state = state;
    }

    public Vehicle() {
    }

    public ObservableList<Vehicle> getVehicle() throws SQLException {
        ObservableList<Vehicle> observableVehicle = FXCollections.observableArrayList();
        try {
            Statement statement = openConnection().createStatement();
            ResultSet result = statement.executeQuery("select * from vehiculos where estado = false");
            while (result.next()) {
                String tuition = result.getString("matricula");
                String brand = result.getString("marca");
                String description = result.getString("descripcion");
                Integer kilometer = result.getInt("kilometros");
                Double price = (double) result.getInt("precio");
                boolean state = result.getBoolean("estado");


                Vehicle vehicle = new Vehicle(tuition,description,brand,kilometer,price, state);
                observableVehicle.add(vehicle);
            }
            openConnection().close();
        } catch (SQLException exception){
            throw exception;
        }

        return observableVehicle;
    }

    public boolean updateState() throws SQLException {
        boolean itUpdate = false;
            Statement statement = openConnection().createStatement();
            int result = statement.executeUpdate("UPDATE vehiculos SET estado = " + state + " WHERE  matricula = '"+tuition+"'");
            openConnection().close();
            if (result > 0) {
                itUpdate = true;
            }
        return itUpdate;
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

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public Integer getKilometer() {
        return kilometer;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.tuition;
    }
}
