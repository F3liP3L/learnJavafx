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
    private Vehicle vehicle;

    // Perdoname Jehova por tantas malas practicas usadas en este proyecto en general 🙏.
    private Double price;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    private String brand;

    public Service(String tuitionVehicle, String nifCustomer, LocalDate fechaRegister, LocalDate dateDelivery, Double total) {
        this.tuitionVehicle = tuitionVehicle;
        this.nifCustomer = nifCustomer;
        this.fechaRegister = fechaRegister;
        this.dateDelivery = dateDelivery;
        this.total = total;
    }

    // Usado para la tabla
    public Service(String tuitionVehicle, LocalDate dateRegister, LocalDate dateDelivery, Double total, Double price, String brand) {
        this.tuitionVehicle = tuitionVehicle;
        this.fechaRegister = dateRegister;
        this.dateDelivery = dateDelivery;
        this.total = total;
        this.price = price;
        this.brand = brand;
    }

    public Service() {}


    public boolean createService() throws SQLException {
        try {
            String sql = "INSERT INTO public.servicios(id_servicio, matricula_vehiculo, nif_cliente, fecha_alquiler, fecha_entrega, total) VALUES (CAST(ROUND((RANDOM() * (10000 - 100)) + 100) AS integer),?, ?, ?, ?, ?)";
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
    public ObservableList<Service> getService() throws SQLException {
        ObservableList<Service> observableService = FXCollections.observableArrayList();
        try {
            Statement statement = openConnection().createStatement();
            String sql = "select v.matricula, v.marca, v.precio, s.fecha_alquiler, s.fecha_entrega, s.total" +
                    " from servicios s, vehiculos v " +
                    " where s.matricula_vehiculo = v.matricula";
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String tuition = result.getString("matricula");
                String brand = result.getString("marca");
                Double price = (double) result.getInt("precio");
                LocalDate dateRegister = result.getDate("fecha_alquiler").toLocalDate();
                LocalDate dateDelivery = result.getDate("fecha_entrega").toLocalDate();
                Double total = (double) result.getInt("total");

                Service service = new Service(tuition,dateRegister,dateDelivery,total,price,brand);

                observableService.add(service);
            }
            openConnection().close();
        } catch (SQLException exception){
            throw exception;
        }
        return observableService;
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
