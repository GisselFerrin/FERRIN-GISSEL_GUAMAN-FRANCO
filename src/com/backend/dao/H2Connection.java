package com.backend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class H2Connection {
    private static final String CREATE_TABLE = "DROP TABLE IF EXISTS ODONTOLOGOS; " +
            "CREATE TABLE ODONTOLOGOS (ID INT AUTO_INCREMENT PRIMARY KEY," +
            "MATRICULA INT NOT NULL, NOMBRE VARCHAR(255) NOT NULL, APELLIDO VARCHAR(255) NOT NULL)";

    public static void crearTabla() {
        Connection connection = null;
        try {
            connection = getConnection();
            Statement st = connection.createStatement();
            st.execute(CREATE_TABLE);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:/data/db/test", "sa", "sa");
    }
}





