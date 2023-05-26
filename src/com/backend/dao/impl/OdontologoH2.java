package com.backend.dao.impl;

import com.backend.dao.H2Connection;
import com.backend.dao.IDao;
import com.backend.entity.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoH2 implements IDao<Odontologo> {

    final static Logger LOGGER = Logger.getLogger(OdontologoH2.class);

    @Override
    public Odontologo guardar(Odontologo odontologo) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try{
            connection = H2Connection.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)");
            preparedStatement.setInt(1, odontologo.getMatricula());
            preparedStatement.setString(2,odontologo.getNombre());
            preparedStatement.setString(3,odontologo.getApellido());
            preparedStatement.execute();

            ResultSet id = preparedStatement.getGeneratedKeys();
            while (id.next()){
                odontologo.setId(id.getInt(1));
            }

        } catch(Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();

            if(connection!=null){
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema.");
                    e.printStackTrace();
                } catch (SQLException exception){
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        LOGGER.info("Odontologo guardado: " + odontologo);
        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {

        Connection connection = null;
        List<Odontologo> listaOdontologos = new ArrayList<>();
        Odontologo odontologo;

        try {
            connection = H2Connection.getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM ODONTOLOGOS");

            while (resultSet.next()){
                int id = resultSet.getInt("ID");
                int matricula = resultSet.getInt("MATRICULA");
                String nombre = resultSet.getNString("NOMBRE");
                String apellido = resultSet.getNString("APELLIDO");
                odontologo = new Odontologo(id,matricula,nombre,apellido);
                listaOdontologos.add(odontologo);
            }

        } catch(Exception e){
            LOGGER.error(e.getMessage());
            e.printStackTrace();

            if(connection!=null){
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema.");
                    e.printStackTrace();
                } catch (SQLException exception){
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
            }
        }
        LOGGER.info("Mostrando todos los odontologos" + listaOdontologos);
        return listaOdontologos;
    }
}