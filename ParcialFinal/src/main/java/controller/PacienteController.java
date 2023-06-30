package controller;


import dto.PacienteDto;
import entity.Paciente;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PacienteService;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<PacienteDto> guardarPaciente(@Valid @RequestBody Paciente paciente) {
        ResponseEntity<PacienteDto> response;
        PacienteDto pacienteDto = pacienteService.guardarPaciente(paciente);
        if (pacienteDto != null) response = new ResponseEntity<>(pacienteDto, null, HttpStatus.CREATED);
        else response = ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return response;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<PacienteDto> actualizarPaciente(@Valid @RequestBody Paciente paciente) {
        ResponseEntity<PacienteDto> response;
        PacienteDto pacienteDto = pacienteService.actualizarPaciente(paciente);
        if(pacienteDto != null) response = new ResponseEntity<>(pacienteDto, null, HttpStatus.OK);
        else response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Paciente eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> buscarPaciente(@PathVariable Long id) {
        ResponseEntity<PacienteDto> response;
        PacienteDto pacienteDto = pacienteService.buscarPacientePorId(id);
        if(pacienteDto != null) response = new ResponseEntity<>(pacienteDto,null, HttpStatus.OK);
        else response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @GetMapping
    public ResponseEntity<List<PacienteDto>> buscarTodosLosPacientes() {
        return ResponseEntity.ok(pacienteService.buscarTodosLosPacientes());
    }


}