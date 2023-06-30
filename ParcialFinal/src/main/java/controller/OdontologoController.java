package controller;

import dto.OdontologoDto;
import entity.Odontologo;
import exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.OdontologoService;
import javax.validation.Valid;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private final OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @PostMapping("/guardar")
    public ResponseEntity<OdontologoDto> guardarOdontologo( @Valid @RequestBody Odontologo odontologo) {


        return new ResponseEntity<>(odontologoService.guardarOdontologo(odontologo), null, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<OdontologoDto> actualizarOdontologo(@Valid @RequestBody Odontologo odontologo) {
        ResponseEntity<OdontologoDto> response;
        OdontologoDto odontologoDto = odontologoService.actualizarOdontologo(odontologo);
        if(odontologoDto != null) response = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
        else response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Odontologo eliminado");
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoDto> buscarOdontologo(@PathVariable Long id) {
        ResponseEntity<OdontologoDto> response;
        OdontologoDto odontologoDto = odontologoService.buscarOdontologoPorId(id);
        if(odontologoDto != null) response = new ResponseEntity<>(odontologoDto, null, HttpStatus.OK);
        else response = ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        return response;
    }

    @GetMapping
    public ResponseEntity<List<OdontologoDto>> buscarTodosLosOdontologos() {
        return ResponseEntity.ok(odontologoService.buscarTodosLosOdontologos());
    }

}