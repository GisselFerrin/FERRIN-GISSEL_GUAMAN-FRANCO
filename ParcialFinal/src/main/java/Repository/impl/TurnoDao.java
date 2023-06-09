package Repository.impl;

import Repository.IDao;
import entity.Odontologo;
import entity.Paciente;
import entity.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class TurnoDao implements IDao<Turno> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TurnoDao.class);
    private PacienteDaoH2 pacienteDaoH2;
    private OdontologoDaoH2 odontologoDaoH2;
    private  List<Turno> turnosRepository = new ArrayList<>();

    @Autowired
    public TurnoDao(PacienteDaoH2 pacienteDaoH2, OdontologoDaoH2 odontologoDaoH2) {
        this.pacienteDaoH2 = pacienteDaoH2;
        this.odontologoDaoH2 = odontologoDaoH2;
    }


    @Override
    public Turno guardar(Turno turno) {
        Turno guardado = null;
        Paciente paciente = pacienteDaoH2.buscarPorId(turno.getPaciente().getId());
        Odontologo odontologo = odontologoDaoH2.buscarPorId(turno.getOdontologo().getId());
        if (paciente != null && odontologo != null) {
            guardado = turno;
            turnosRepository.add(guardado);
            LOGGER.info("Turno guardado correctamente: {}", guardado);
        } else LOGGER.error("No puede registrarse el turno");
        return guardado;
    }

    @Override
    public List<Turno> listarTodos() {
        LOGGER.info("Listado de todos los turnos: {}", turnosRepository);
        return turnosRepository;
    }

    @Override
    public void eliminar(int id) {
        Turno turno = buscarPorId(id);
        turnosRepository.remove(turno);
        LOGGER.warn("Se ha eliminado el turno con id {}", id);
    }

    @Override
    public Turno buscarPorId(int id) {
        return (Turno) turnosRepository
                .stream()
                .filter(t -> t.getId() == id);
    }

    @Override
    public Turno buscarPorCriterio(String criterio) {
        return null;
    }

    @Override
    public Turno actualizar(Turno turno) {
        //turnosRepository.set(turnosRepository.indexOf(buscarPorId(turno.getId())), turno);
        //return turno;

        eliminar(turno.getId());
        guardar(turno);
        return turno;
    }
}
