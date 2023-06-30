package controller;


import dto.TurnoDto;
import entity.Turno;
import exceptions.BadRequestException;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TurnoService;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private final TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<TurnoDto> guardarTurno( @Valid @RequestBody Turno turno) throws BadRequestException {
        return new ResponseEntity<>(turnoService.guardarTurno(turno), null, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<TurnoDto> actualizarTurno(@Valid @RequestBody Turno turno) {
        ResponseEntity<TurnoDto> response;
        TurnoDto turnoDto = turnoService.actualizarTurno(turno);
        if(turnoDto!=null) response = new ResponseEntity<>(turnoDto, null, HttpStatus.OK);
        else response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Turno eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoDto> buscarTurno(@PathVariable Long id) {
        ResponseEntity<TurnoDto> respuesta;
        TurnoDto turnoDto = turnoService.buscarTurnoPorId(id);
        if (turnoDto != null) respuesta = new ResponseEntity<>(turnoDto, null, HttpStatus.OK);
        else respuesta = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return respuesta;
    }



    @GetMapping()
    public List<TurnoDto> listarTurnos() {
        return turnoService.buscarTodosLosTurnos();
    }


}
