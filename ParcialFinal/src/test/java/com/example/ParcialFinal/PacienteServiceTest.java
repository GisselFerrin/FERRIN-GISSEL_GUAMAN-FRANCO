package com.example.ParcialFinal;

import dto.PacienteDto;
import entity.Domicilio;
import entity.Paciente;
import exceptions.ResourceNotFoundException;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.PacienteService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;

    @BeforeClass
    public static void cargarDataSet(){
        Domicilio d1 = new  Domicilio("Alsina", 1200, "Quilmes", "Buenos Aires");
        Domicilio d2 = new  Domicilio("Mitre", 2100, "Bariloche", "Rio Negro");
        Paciente p1 = new Paciente("Garcia", "Gabriela", "22.333.444", LocalDate.of(2023, 12, 30), d1);
        Paciente p2 = new Paciente("Sosa", "Sandro", "93.333.444", LocalDate.of(2023, 11, 30), d2);
    }

    @Test
    public void deberiaAgregarUnPaciente() {

        Paciente pacienteTest = new Paciente("Continini", "Alfredo", "12.000.345", LocalDate.of(2023, 5, 2), new Domicilio("Av. Santa Fe", 334, "CABA", "Buenos Aires"));

        PacienteDto pacienteResult = pacienteService.guardarPaciente(pacienteTest);
        assertNotNull(pacienteResult);
        assertEquals("12.000.345", pacienteResult.getDni());
    }

    @Test
    public void deberiaModificarUnPaciente(){
        Paciente pacienteTest = new Paciente("Centeno", "Jose", "10.000.222", LocalDate.of(2023, 5, 2), new Domicilio("Combate de los pozos", 334, "Cholila", "Chubut"));

        Domicilio nuevoDomicilio = new Domicilio("Mitre", 1100, "Quilmes", "Buenos Aires");
        pacienteTest.setDomicilio(nuevoDomicilio);

        pacienteService.actualizarPaciente(pacienteTest);
        assertEquals(nuevoDomicilio, pacienteTest.getDomicilio());
    }

    @Test
    public void deberiaEliminarElPacienteConId2() throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(2L);
        assertNull(pacienteService.buscarPaciente(2L));
    }


    @Test
    public void deberiaBuscarUnPacientePorId() {
        Paciente pacienteResult = (Paciente) pacienteService.buscarPaciente(1L);
        assertNotNull(pacienteResult);
        assertEquals("32.322.223", pacienteResult.getDni());
    }

    @Test
    public void listarTodosLosPacientes() {
        List<PacienteDto> pacientesTest = pacienteService.buscarTodosLosPacientes();
        assertFalse(pacientesTest.isEmpty());
        assertTrue(pacientesTest.size() >= 2);
    }

}