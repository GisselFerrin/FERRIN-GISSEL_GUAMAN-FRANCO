package dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import entity.Turno;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TurnoDto {

    private Long id;
    private PacienteDto paciente;
    private OdontologoDto odontologo;
    private LocalDateTime fechaHora;

    public TurnoDto() {
    }

    public TurnoDto(Long id, PacienteDto paciente, OdontologoDto odontologo, LocalDateTime fechaHora) {
        this.id = id;
        this.paciente = paciente;
        this.odontologo = odontologo;
        this.fechaHora = fechaHora;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteDto paciente) {
        this.paciente = paciente;
    }

    public OdontologoDto getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(OdontologoDto odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "TURNO {id: " + id + " - Paciente: " + paciente.getNombre() + " " +  paciente.getApellido()+ " - Odontologo: " + odontologo.getNombre() + " " + odontologo.getApellido() + " - Turno: " + fechaHora + "}";
    }

}