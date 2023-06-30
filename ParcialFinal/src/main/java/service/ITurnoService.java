package service;

import dto.TurnoDto;
import entity.Turno;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno);
    //se transforma
    List<TurnoDto> listarTodos();
    TurnoDto buscarTurnoPorId(int id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(int id);

}
