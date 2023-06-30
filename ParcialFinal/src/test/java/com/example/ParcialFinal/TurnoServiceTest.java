package com.example.ParcialFinal;

import dto.OdontologoDto;
import dto.PacienteDto;
import dto.TurnoDto;
import entity.Odontologo;
import entity.Paciente;
import entity.Turno;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import service.OdontologoService;
import service.PacienteService;
import service.TurnoService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class TurnoServiceTest{
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    private static Paciente paciente;
    private static Odontologo odontologo;

    @BeforeAll
    public static void init(){
        paciente =new Paciente ("Gissel","Ferrin","34536",5666);
        Domiclio("Larravide",12,"Montevideo","Provincia");
        odontologo = new Odontologo("SA-12213213212","Franco",12);

    }

    private static void Domiclio(String larravide, int i, String montevideo, String provincia) {

    }

    @Test
    @Order(1)
    void deberiaInsertarUnTurno() throws BadRequestException{
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        OdontologoDto odontologoDto = odontologoService.guardarOdontologo(odontologo);

        TurnoDto turnoDto = turnoService.guardarTurno(new Turno(paciente, odontologo,LocalDateTime.of(LocalDate.of(2024,
                10,01), LocalTime.of(12,10))));
        Assertions.assertNotNull(turnoDto);
        Assertions.assertNotNull(turnoDto.getId());
        Assertions.assertEquals(turnoDto.getPaciente(),pacienteDto.getNombre() + "" + pacienteDto.getApellido());
        Assertions.assertEquals(turnoDto.getOdontologo(),odontologoDto.getNombre() + "" + odontologoDto.getApellido());

    }


    @Test
    @Order(5)

    public void deberiaEliminarElTurnoConId5() throws ResourceNotFoundException {
        turnoService.eliminarTurno(5L);
        assertEquals((Long) null, turnoService.buscarTurno(5L));
    }

    @Test
    @Order(8)
    public void deberiaBuscarUnTurnoPorId() throws BadRequestException{
        long turnoResult = turnoService.buscarTurno(8L);
        assertNotNull(turnoResult);

    }

    @Test
    @Order(1)
    public void listarTodosLosTurnos() throws BadRequestException{
        List<TurnoDto> turnos = turnoService.buscarTodosLosTurnos();
        assertFalse(turnos.isEmpty());
        System.out.println(turnos);

    }


}