package com.example.usuarios.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestDBConnection {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/db_usuarios";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(url, username, password)) {
                if (conn != null) {
                    System.out.println("Conexión exitosa a la base de datos db_usuarios.");
                } else {
                    System.out.println("No se pudo establecer la conexión.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL no encontrado:");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}
