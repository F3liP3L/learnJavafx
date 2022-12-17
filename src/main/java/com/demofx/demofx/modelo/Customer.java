package com.demofx.demofx.modelo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class Customer {

    private String name;
    private String nif;
    private String address;
    private String population;

    public Customer(String name, String nif, String address, String population) {
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.population = population;
    }

    public Customer() {
        this.name = name;
        this.nif = nif;
        this.address = address;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
    @Override
    public String toString() {
        return this.nif;
    }


    public ObservableList<Customer> getCustomer() throws SQLException {
        ObservableList<Customer> observableCustomer = FXCollections.observableArrayList();
        try {
            Statement statement = openConnection().createStatement();
            ResultSet result = statement.executeQuery("select * from clientes");
            while (result.next()) {
                String nif = result.getString("nif");
                String name = result.getString("nombre");
                String address = result.getString("direcion");
                String population = result.getString("poblacion");

                Customer customer = new Customer(name,nif,address,population);
                observableCustomer.add(customer);
            }
            openConnection().close();
        } catch (SQLException exception){
            throw exception;
        }

        return observableCustomer;
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

}
