package service;

import Repository.IPacienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.DomicilioDto;
import dto.PacienteDto;
import entity.Domicilio;
import entity.Paciente;
import exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PacienteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
    private final IPacienteRepository pacienteRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public PacienteService(IPacienteRepository pacienteRepository, ObjectMapper objectMapper) {
        this.pacienteRepository = pacienteRepository;
        this.objectMapper = objectMapper;
    }

    public PacienteDto guardarPaciente(Paciente paciente) {
        Paciente pacienteNuevo = pacienteRepository.save(paciente);
        DomicilioDto domicilioDto = objectMapper.convertValue(pacienteNuevo.getDomicilio(), DomicilioDto.class);
        PacienteDto pacienteDto = objectMapper.convertValue(pacienteNuevo, PacienteDto.class);
        pacienteDto.setDomicilio(domicilioDto);
        LOGGER.info("Paciente guardado: {}", pacienteDto);
        return pacienteDto;
    }

    public PacienteDto actualizarPaciente(Paciente paciente) {

        Paciente pacienteActualizar = pacienteRepository.findById(paciente.getId()).orElse(null);
        PacienteDto pacienteActualizadoDto = null;

        if (pacienteActualizar != null) {
            pacienteActualizar = paciente;
            pacienteRepository.save(pacienteActualizar);

            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteActualizar.getDomicilio(), DomicilioDto.class);
            pacienteActualizadoDto = objectMapper.convertValue(pacienteActualizar, PacienteDto.class);
            pacienteActualizadoDto.setDomicilio(domicilioDto);
            LOGGER.info("Paciente actualizado: {}", pacienteActualizadoDto);

        } else LOGGER.error("No fue posible actualizar los datos ya que el paciente no se encuentra registrado");

        return pacienteActualizadoDto;
    }

    public void eliminarPaciente(Long id) throws ResourceNotFoundException {
        if(buscarPacientePorId(id)!=null){
            pacienteRepository.deleteById(id);
            LOGGER.warn("Se elimino al paciente con id {}", id);
        } else {
            LOGGER.error("No se encontro el paciente con id " + id);
            throw new ResourceNotFoundException("No se encontro el paciente con id " + id);
        }

    }

    public PacienteDto buscarPacientePorId(Long id) {
        Paciente pacienteBuscado = pacienteRepository.findById(id).orElse(null);//el findById retorna un Optional
        PacienteDto pacienteDto = null;
        if (pacienteBuscado != null) {
            DomicilioDto domicilioDto = objectMapper.convertValue(pacienteBuscado.getDomicilio(), DomicilioDto.class);
            pacienteDto = objectMapper.convertValue(pacienteBuscado, PacienteDto.class);
            pacienteDto.setDomicilio(domicilioDto);
            LOGGER.info("Paciente buscado: {}", pacienteDto);
        } else LOGGER.info("Id de paciente inexistente en la base de datos");

        return pacienteDto;
    }

    public List<PacienteDto> buscarTodosLosPacientes() {
        List<Paciente> pacienteList = pacienteRepository.findAll();
        List<PacienteDto> pacienteDtos = pacienteList.stream() //stream es una iteracion
                .map(paciente -> {
                    Domicilio domicilio = paciente.getDomicilio();
                    DomicilioDto domicilioDto = objectMapper.convertValue(domicilio, DomicilioDto.class);
                    return new PacienteDto(paciente.getId(), paciente.getApellido(), paciente.getNombre(), paciente.getDni(), paciente.getFechaIngreso(), domicilioDto);
                }).toList();

        LOGGER.info("Listado de pacientes: {}", pacienteDtos);
        return pacienteDtos;
    }

    public Object buscarPaciente(long l) {
        return null;
    }
}