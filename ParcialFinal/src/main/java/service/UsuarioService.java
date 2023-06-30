package service;

import Repository.IUsuarioRepository;
import entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioService.class);
    private final IUsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario guardarUsuario(Usuario usuario) {
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        LOGGER.info("Usuario guardado: {}", usuarioGuardado);
        return usuarioGuardado;
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
        LOGGER.warn("Se elimino al usuario con id " + id);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuarioBuscado = usuarioRepository.findById(id).orElse(null);
        if (usuarioBuscado != null) LOGGER.info("Usuario buscado: {}", usuarioBuscado);
        else LOGGER.info("Usuario inexistente en la base de datos");
        return usuarioBuscado;
    }

    public List<Usuario> buscarTodosLosUsuarios() {
        List<Usuario> usuarioList = usuarioRepository.findAll();
        LOGGER.info("Listado de usuarios: {}", usuarioList);
        return usuarioList;
    }


}
