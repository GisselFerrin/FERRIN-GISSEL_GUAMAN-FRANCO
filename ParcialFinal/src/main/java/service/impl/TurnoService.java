package service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import Repository.IDao;
import Repository.impl.dto.TurnoDto;
import entity.Turno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.ITurnoService;

import java.util.List;


@Service
public class TurnoService implements ITurnoService {
  private final ObjectMapper objectMapper;
  private IDao<Turno> turnoIDao;
  private ObjectMapper ojjectMapper;


  @Autowired
  public TurnoService(IDao<Turno> turnoIDao, ObjectMapper objectMapper) {
    this.turnoIDao = turnoIDao;
    this.objectMapper = ojjectMapper;
  }

  @Override
  public TurnoDto guardarTurno(Turno turno) {
    return TurnoDto.fromTurno(turnoIDao.guardar(turno));
  }

  @Override
  public List<TurnoDto> listarTodos() {
    return turnoIDao.listarTodos().stream().map(TurnoDto::fromTurno).toList();
  }

  @Override
  public TurnoDto buscarTurno(int id) {
    return null;
  }

  @Override
  public TurnoDto actualizarTurno() {
    return null;
  }

  @Override
  public TurnoDto buscarTurnoPorId(int id) {
    return TurnoDto.fromTurno(turnoIDao.buscarPorId(id));
  }

  @Override
  public TurnoDto actualizarTurno(int id) {
    return null;
  }

  @Override
  public TurnoDto actualizarTurno(Turno turno) {
    return TurnoDto.fromTurno(turnoIDao.actualizar(turno));
  }

  @Override
  public void eliminarTurno(int id) {
    turnoIDao.eliminar(id);
  }
}


