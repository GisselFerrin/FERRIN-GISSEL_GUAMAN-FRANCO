package com.backend.dao.impl;

import com.backend.dao.IDao;
import com.backend.entity.Odontologo;

import java.util.List;
import java.util.logging.Logger;

public class OdontologoMemoria implements IDao<Odontologo> {
//Acá se agrego un VALUEOF
    final static Logger LOGGER = Logger.getLogger(String.valueOf(OdontologoMemoria.class));
    private List<Odontologo> listaOdontologos;
    private int idNuevo=0;
    public OdontologoMemoria(List<Odontologo> listaOdontologos){
        this.listaOdontologos=listaOdontologos;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        idNuevo++;
        odontologo.setId(idNuevo);
        listaOdontologos.add(odontologo);
        LOGGER.info("Odontologo guardado: "+ odontologo);

        return odontologo;
    }

    @Override
    public List<Odontologo> buscarTodos() {
        LOGGER.info("Mostrando todos los odontologos" + listaOdontologos);

        return listaOdontologos;
    }
}
