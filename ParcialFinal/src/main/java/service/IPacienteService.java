package service;


import entity.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> listarPacientes();
    Paciente buscarPacientePorDni(String dni);

    Paciente actualizarPaciente(Paciente paciente);

    Paciente guardarPaciente(Paciente paciente);

    Paciente buscarPacientePorId(int id);

    void eliminarPaciente(int id);
}
