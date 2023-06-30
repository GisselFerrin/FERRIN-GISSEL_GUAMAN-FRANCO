package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ODONTOLOGOS")
public class Odontologo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 50, message = "El apellido debe tener hasta 50 caracteres")
    @NotNull(message = "El apellido del odotnólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el apellido del odotnólogo")
    private String apellido;

    @Size(max = 50, message = "El nombre debe tener hasta 50 caracteres")
    @NotNull(message = "El nombre del odotnólogo no puede ser nulo")
    @NotBlank(message = "Debe especificarse el nombre del odotnólogo")
    private String nombre;

    @NotNull(message = "La matrícula no puede ser nula")
    @NotBlank(message = "Debe especificarse la matrícula del odotnólogo")
    private String matricula;

    @JsonIgnore
    @OneToMany(mappedBy = "odontologo", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Turno> turnos = new HashSet<>();

    public Odontologo(String asuani, String ana, int i) {
    }

    public Odontologo(String apellido, String nombre, String matricula, Set<Turno> turnos) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.turnos = turnos;
    }

    public Long getId() {
        return id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() { return matricula; }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

}