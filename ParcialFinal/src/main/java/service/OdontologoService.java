package service;

import Repository.IOdontologoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.OdontologoDto;
import entity.Odontologo;
import exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class OdontologoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);
    private final ObjectMapper objectMapper;
    private final IOdontologoRepository odontologoRepository;

    @Autowired
    public OdontologoService(ObjectMapper objectMapper, IOdontologoRepository odontologoRepository) {
        this.objectMapper = objectMapper;
        this.odontologoRepository = odontologoRepository;
    }

    public OdontologoDto guardarOdontologo(Odontologo odontologo) {
        Odontologo odontologoNuevo = odontologoRepository.save(odontologo);
        OdontologoDto odontologoDto = objectMapper.convertValue(odontologoNuevo, OdontologoDto.class);
        LOGGER.info("Odontologo guardado: {}", odontologoDto);
        return odontologoDto;
    }

    public OdontologoDto actualizarOdontologo(Odontologo odontologo) {

        Odontologo odontologoActualizar = odontologoRepository.findById(odontologo.getId()).orElse(null);
        OdontologoDto odontologoActualizadDto = null;

        if (odontologoActualizar != null) {
            odontologoActualizar = odontologo;
            odontologoRepository.save(odontologoActualizar);

            odontologoActualizadDto = objectMapper.convertValue(odontologoActualizar, OdontologoDto.class);
            LOGGER.info("Odontologo actualizado: {}", odontologoActualizadDto);

        } else LOGGER.error("No fue posible actualizar los datos ya que el odontologo no se encuentra registrado");

        return odontologoActualizadDto;
    }


    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        if(buscarOdontologoPorId(id)!=null){
            odontologoRepository.deleteById(id);
            LOGGER.warn("Se elimino al odontologo con id {}", id);
        } else {
            LOGGER.error("No se encontro el odontologo con id " + id);
            throw new ResourceNotFoundException("No se encontro el odontologo con id " + id);
        }
    }

    public OdontologoDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoDto odontologoDto = null;
        if (odontologoBuscado != null){
            odontologoDto = objectMapper.convertValue(odontologoBuscado, OdontologoDto.class);
            LOGGER.info("Odontologo buscado: {}", odontologoDto);
        } else LOGGER.info("Id de odontologo inexistente en la base de datos");

        return odontologoDto;
    }

    public List<OdontologoDto> buscarTodosLosOdontologos() {
        List<Odontologo> odontologoList = odontologoRepository.findAll();
        List<OdontologoDto> odontologoDtos = odontologoList.stream()
                .map(odontologo -> objectMapper.convertValue(odontologo, OdontologoDto.class)).toList();
        LOGGER.info("Listado de odontologos: {}", odontologoDtos);
        return odontologoDtos;
    }

    public Object buscarOdontologo(int i) {
        return null;
    }
}

