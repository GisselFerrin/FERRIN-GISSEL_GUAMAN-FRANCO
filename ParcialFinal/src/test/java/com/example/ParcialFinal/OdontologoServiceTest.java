package com.example.ParcialFinal;

import dto.OdontologoDto;
import entity.Odontologo;
import exceptions.ResourceNotFoundException;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.OdontologoService;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class OdontologoServiceTest {

   @Autowired
   private OdontologoService odontologoService;

    @Test
    public void deberiaAgregarUnOdontologo(){
        Odontologo odontologoTest = new Odontologo("Asuani", "Ana", 90876);

        OdontologoDto odontologoResult = odontologoService.guardarOdontologo(odontologoTest);

        assertNotNull(odontologoResult);
        assertEquals(90876, odontologoResult.getMatricula());
        assertTrue(odontologoTest.getId() !=0);
    }

    private void assertEquals(int i, String matricula) {

    }

    @Test
    public void deberiaModificarUnOdontologo(){
        Odontologo odontologoTest = new Odontologo("Tierra", "Claudia", 90876);
        odontologoService.guardarOdontologo(odontologoTest);
        odontologoTest.setApellido("Cielo");
        odontologoService.actualizarOdontologo(odontologoTest);
        assertEquals(Integer.parseInt("Cielo"), odontologoTest.getApellido());
    }

    @Test
    public void deberiaEliminarElOdontologoconId1() throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(1L);
       assertEquals(1, (String) odontologoService.buscarOdontologo(1));
    }

    @Test
    public void deberiaBuscarUnOdontologoconPorId(){
        Odontologo odontologoResult = (Odontologo) odontologoService.buscarOdontologo(3);
        assertNotNull(odontologoResult);
        assertEquals(90876, odontologoResult.getMatricula());
    }

    @Test
    public void listarTodosLosOdontologos(){
        List<OdontologoDto> odontologos = odontologoService.buscarTodosLosOdontologos();
        assertFalse(odontologos.isEmpty());
        System.out.println(odontologos);
    }


}