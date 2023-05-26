package com.backend.service;

import com.backend.dao.IDao;
import com.backend.dao.impl.OdontologoH2;
import com.backend.dao.impl.OdontologoMemoria;
import com.backend.entity.Odontologo;
import org.junit.Test;

import java.util.ArrayList;

public class OdontologoServiceTest {

    private IDao<Odontologo> OdontologoMemoria = new OdontologoMemoria(new ArrayList<>());
    private IDao<Odontologo> OdontologoH2 = new OdontologoH2();
    private OdontologoService OdontologoService = new OdontologoService();



    @Test
    void getOdontologoIDao() {
    } private IDao<Odontologo> odontologoMemoria = new OdontologoMemoria(new ArrayList<>());
    private IDao<Odontologo> odontologoH2 = new OdontologoH2();
    private OdontologoService odontologoService = new OdontologoService();


    @Test
    public void deberiaGuardarOdontologosYBuscarTodosEnH2(){
        OdontologoService.setOdontologoIDao(OdontologoH2);
        OdontologoService.guardarOdontologo(new Odontologo(123, "Gissel", "Ferrin"));
        OdontologoService.guardarOdontologo(new Odontologo(456, "Franco", "Guaman"));
        OdontologoService.buscarTodos();
    }

    @Test
    public void deberiaGuardarOdontologosYBuscarTodosEnMemoria(){
        OdontologoService.setOdontologoIDao(OdontologoMemoria);
        OdontologoService.guardarOdontologo(new Odontologo(123, "Gonzalo", "Heredia"));
        OdontologoService.guardarOdontologo(new Odontologo(456, "Leonel", "Messi"));
        OdontologoService.buscarTodos();
    }
}
