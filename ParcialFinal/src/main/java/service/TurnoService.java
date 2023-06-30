package service;

import Repository.ITurnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OdontologoDto;
import dto.PacienteDto;
import dto.TurnoDto;
import entity.Odontologo;
import entity.Paciente;
import entity.Turno;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private final ObjectMapper objectMapper;
    private final ITurnoRepository turnoRepository;
    private final OdontologoService odontologoService;
    private final PacienteService pacienteService;

    @Autowired
    public TurnoService(ObjectMapper objectMapper, ITurnoRepository turnoRepository, OdontologoService odontologoService, PacienteService pacienteService) {
        this.objectMapper = objectMapper;
        this.turnoRepository = turnoRepository;
        this.odontologoService = odontologoService;
        this.pacienteService = pacienteService;
    }

    public TurnoDto guardarTurno(Turno turno) throws BadRequestException {
        TurnoDto turnoDto = null;
        Turno turnoGuardado = null;

        PacienteDto paciente = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
        OdontologoDto odontologo = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());

        if(paciente == null || odontologo == null) {
            if(paciente == null && odontologo == null) {
                LOGGER.error("El paciente y el odontologo no se encuentran en la base de datos");
                throw new BadRequestException("El paciente no se encuentra en la base de datos");
            }
            else if (paciente == null){
                LOGGER.error("El paciente no se encuentra en la base de datos");
                throw new BadRequestException("El paciente no se encuentra en la base de datos");
            } else {
                LOGGER.error("El odontologo no se encuentra en la base de datos");
                throw new BadRequestException("El odontologo no se encuentra en la base de datos");
            }
        } else {
            turnoGuardado = turnoRepository.save(turno);
            turnoDto = objectMapper.convertValue(turnoGuardado, TurnoDto.class);
            LOGGER.info("Turno guardado: {}", turnoDto);
        }
        return turnoDto;
    }

    public TurnoDto actualizarTurno(Turno turno) {

        Turno turnoActualizar = turnoRepository.findById(turno.getId()).orElse(null);
        TurnoDto turnoActualizadoDto = null;

        if(turnoActualizar !=null){
            turnoActualizar = turno;
            turnoRepository.save(turnoActualizar);
            turnoActualizadoDto = objectMapper.convertValue(turnoActualizar, TurnoDto.class);
            LOGGER.info("Turno actualizado: {}", turnoActualizadoDto);
        } else LOGGER.error("No fue posible actualizar los datos ya que el turno no se encuentra registrado");

        return turnoActualizadoDto;
    }

    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if(buscarTurnoPorId(id)!=null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se elimino el turno con id {}", id);
        } else {
            LOGGER.error("No se encontro el turno con id " + id);
            throw new ResourceNotFoundException("No se encontro el turno con id " + id);
        }
    }

    public TurnoDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoDto turnoDto = null;
        if (turnoBuscado != null){
            turnoDto = objectMapper.convertValue(turnoBuscado, TurnoDto.class);
            LOGGER.info("Turno buscado: {}", turnoDto);
        } else LOGGER.info("Turno inexistente en la base de datos");
        return turnoDto;
    }

    public List<TurnoDto> buscarTodosLosTurnos() {
        List<Turno> turnoList = turnoRepository.findAll();
        List<TurnoDto> turnoDtos = turnoList.stream()
                .map(turno -> {
                    Paciente paciente = turno.getPaciente();
                    PacienteDto pacienteDto = objectMapper.convertValue(paciente, PacienteDto.class);
                    Odontologo odontologo = turno.getOdontologo();
                    OdontologoDto odontologoDto = objectMapper.convertValue(odontologo, OdontologoDto.class);
                    return new TurnoDto(turno.getId(), pacienteDto, odontologoDto, turno.getFechaHora());}).toList();
        LOGGER.info("Listado de turnos: {}", turnoDtos);
        return turnoDtos;
    }

    public long buscarTurno(long i) {
        return 0;

    }
}